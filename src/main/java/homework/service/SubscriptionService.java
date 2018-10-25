package homework.service;

import homework.entity.Scheme;
import homework.entity.Subscription;
import homework.entity.User;
import homework.repository.SchemeRepository;
import homework.repository.SubscriptionRepository;
import homework.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final UserRepository userRepository;

    private final SchemeRepository schemeRepository;

    private final SubscriptionRepository subscriptionRepository;

    private static Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    @Autowired
    public SubscriptionService(UserRepository userRepository, SchemeRepository schemeRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.schemeRepository = schemeRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public void searchSubscriptionHistory(Integer userId) {
        logger.info("查询id为 " + userId + " 的用户的历史订单列表");
        userRepository
                .findById(userId)
                .ifPresent(user -> {
                    List<Subscription> subscriptions = Optional.ofNullable(user.getSubscriptionList()).orElse(new ArrayList<>());
                    subscriptions.forEach(item ->
                            System.out.println("订单id: " + item.getId() + "\t"
                                    + "是否有效: " + item.getActive() + "\t"
                                    + "开始时间: " + item.getStartAt() + "\t"
                                    + "订阅套餐id: " + item.getScheme().getId() + "\t"
                                    + "订阅套餐名: " + item.getScheme().getName()
                            )
                    );
                });
        logger.info("查询结束");
        System.out.println();
    }

    public void subscribeScheme(Integer userId, Integer schemeId, Boolean isActiveNow) {
        logger.info("id为 " + userId + " 的用户订阅id为 " + schemeId + " 的套餐并" + (isActiveNow ? "立即生效" : "下月生效"));
        userRepository.findById(userId).ifPresent(user ->
                schemeRepository.findById(schemeId).ifPresent(scheme -> {
                    Subscription subscription = new Subscription();
                    subscription.setActive(true);
                    subscription.setScheme(scheme);
                    java.sql.Date startAt;
                    if (isActiveNow) {
                        startAt = new java.sql.Date(System.currentTimeMillis());
                    } else {
                        LocalDate localDate = LocalDate.now();
                        startAt = Date.valueOf(
                                localDate
                                        .plusMonths(1)
                                        .minusDays(localDate.getDayOfMonth()-1)
                        );
                    }
                    subscription.setStartAt(startAt);
                    List<Subscription> subscriptions = Optional.ofNullable(user.getSubscriptionList()).orElse(new ArrayList<>());
                    subscriptions.add(subscription);
                    user.setSubscriptionList(subscriptions);
                    userRepository.save(user);
                    System.out.println("开始时间: " + startAt + "\t"
                            + "订阅套餐id: " + schemeId + "\t"
                            + "订阅套餐名: " + scheme.getName()
                    );
                })
        );
        logger.info("查询结束");
        System.out.println();
    }

    public void unsubscribeScheme(Integer subscriptionId) {
        logger.info("退订id为: " + subscriptionId + " 的订单");
        subscriptionRepository.findById(subscriptionId).ifPresent(subscription -> {
            subscription.setActive(false);
            subscriptionRepository.save(subscription);
            System.out.println("订单id: " + subscriptionId + "\t"
                    + "是否有效: " + subscription.getActive() + "\t"
                    + "开始时间: " + subscription.getStartAt() + "\t"
                    + "订阅套餐id: " + subscription.getScheme().getId() + "\t"
                    + "订阅套餐名: " + subscription.getScheme().getName()
            );
        });
        logger.info("查询结束");
        System.out.println();
    }

    public void callPay(Integer userId, Integer thisCallTime) {
        logger.info("id为 " + userId + " 的用户拨打了 " + thisCallTime + " 分钟的时间");
        userRepository.findById(userId).ifPresent(user -> {
            Double payBefore = calculateUserPay(user);
            Integer userCallTime = user.getCallTime();
            user.setCallTime(userCallTime + thisCallTime);
            userRepository.save(user);
            Double payAfter = calculateUserPay(user);
            System.out.println("本次通话花费: " + (payAfter - payBefore));
        });
        logger.info("查询结束");
        System.out.println();
    }

    public void onlinePay(Integer userId, Integer thisOnlineData, Integer thisOnlineLocalData) {
        logger.info("id为 " + userId + " 的用户使用国内流量 " + thisOnlineData + "M ，使用本地流量 " + thisOnlineLocalData + "M");
        userRepository.findById(userId).ifPresent(user -> {
            Double payBefore = calculateUserPay(user);

            Integer userOnlineData = user.getOnlineData();
            Integer userOnlineLocalData = user.getLocalOnlineData();
            user.setOnlineData(userOnlineData + thisOnlineData);
            user.setLocalOnlineData(userOnlineLocalData + thisOnlineLocalData);

            userRepository.save(user);
            Double payAfter = calculateUserPay(user);
            System.out.println("本次网络花费: " + (payAfter - payBefore));
        });
        logger.info("查询结束");
        System.out.println();
    }

    public void monthPay(Integer userId) {
        logger.info("查id为 " + userId + " 的用户月话费");
        userRepository.findById(userId).ifPresent(user -> {
            Double pay = calculateUserPay(user);
            System.out.println("用户月消费: " + pay + " 元");
        });
        logger.info("查询结束");
        System.out.println();
    }


    private Double calculateUserPay(User user) {
        List<Subscription> subscriptions = user.getSubscriptionList();
        Integer sumFreeCallTime = 0;
        Integer sumFreeMessageCount = 0;
        Integer sumFreeOnlineData = 0;
        Integer sumFreeOnlineLocalData = 0;
        Float sumPrice = 0f;
        for (Subscription item : subscriptions) {
            LocalDate nowTime = LocalDate.now();
            LocalDate itemStartAt = item.getStartAt().toLocalDate();
            if (item.getActive() && nowTime.isAfter(itemStartAt)) {
                Scheme scheme = item.getScheme();
                sumFreeCallTime += scheme.getFreeCallTime();
                sumFreeMessageCount += scheme.getFreeMessageCount();
                sumFreeOnlineData += scheme.getFreeOnlineData();
                sumFreeOnlineLocalData += scheme.getFreeOnlineLocalData();
                sumPrice += scheme.getPrice();
            }
        }

        Integer payCallTime = user.getCallTime() - sumFreeCallTime;
        Integer payMessageCount = user.getMessageCount() - sumFreeMessageCount;
        Integer payOnlineData = user.getOnlineData() - sumFreeOnlineData;
        Integer payOnlineLocalData = user.getLocalOnlineData() - sumFreeOnlineLocalData;

        double callPay = payCallTime > 0 ? payCallTime * 0.5 : 0;
        double messagePay = payMessageCount > 0 ? payMessageCount * 0.1 : 0;
        double onlineDataPay = payOnlineData > 0 ? payOnlineData * 5.0 / 1024 : 0;
        double onlineLocalDataPay = payOnlineLocalData > 0 ? payOnlineLocalData * 2.0 / 1024 : 0;

        return sumPrice + callPay + messagePay + onlineDataPay + onlineLocalDataPay;

    }

}