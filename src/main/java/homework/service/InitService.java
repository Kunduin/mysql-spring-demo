package homework.service;

import homework.entity.Scheme;
import homework.entity.Subscription;
import homework.entity.User;
import homework.repository.SchemeRepository;
import homework.repository.SubscriptionRepository;
import homework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class InitService {
    private final SchemeRepository schemeRepository;

    private final SubscriptionRepository subscriptionRepository;

    private final UserRepository userRepository;

    @Autowired
    public InitService(SchemeRepository schemeRepository, SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.schemeRepository = schemeRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public void initData() {

        // 无套餐
        Scheme schemeNormal = new Scheme();
        schemeNormal.setFreeCallTime(0);
        schemeNormal.setFreeMessageCount(0);
        schemeNormal.setFreeOnlineData(0);
        schemeNormal.setIsFreeOnlineLocalData(0);
        schemeNormal.setPrice(0f);
        schemeNormal.setName("normal scheme");

        // 话费套餐
        Scheme schemeCall = new Scheme();
        schemeCall.setFreeCallTime(100);
        schemeCall.setFreeMessageCount(0);
        schemeCall.setFreeOnlineData(0);
        schemeCall.setIsFreeOnlineLocalData(0);
        schemeCall.setPrice(20f);
        schemeCall.setName("call scheme");

        // 短信套餐
        Scheme schemeMessage = new Scheme();
        schemeMessage.setFreeCallTime(0);
        schemeMessage.setFreeMessageCount(200);
        schemeMessage.setFreeOnlineData(0);
        schemeMessage.setIsFreeOnlineLocalData(0);
        schemeMessage.setPrice(10f);
        schemeMessage.setName("message scheme");

        // 本地流量套餐
        Scheme schemeOnlineLocal = new Scheme();
        schemeOnlineLocal.setFreeCallTime(0);
        schemeOnlineLocal.setFreeMessageCount(0);
        schemeOnlineLocal.setFreeOnlineData(0);
        schemeOnlineLocal.setIsFreeOnlineLocalData(2048);
        schemeOnlineLocal.setPrice(20f);
        schemeOnlineLocal.setName("online local scheme");

        // 国内流量套餐
        Scheme schemeOnline = new Scheme();
        schemeOnline.setFreeCallTime(0);
        schemeOnline.setFreeMessageCount(0);
        schemeOnline.setFreeOnlineData(2048);
        schemeOnline.setIsFreeOnlineLocalData(0);
        schemeOnline.setPrice(30f);
        schemeOnline.setName("online scheme");


        schemeRepository.save(schemeNormal);
        schemeRepository.save(schemeCall);
        schemeRepository.save(schemeMessage);
        schemeRepository.save(schemeOnline);
        schemeRepository.save(schemeOnlineLocal);

        ArrayList<Scheme> schemeArray = new ArrayList<>();
        schemeArray.add(schemeNormal);
        schemeArray.add(schemeCall);
        schemeArray.add(schemeMessage);
        schemeArray.add(schemeOnline);
        schemeArray.add(schemeOnlineLocal);

        ArrayList<Subscription> subscriptions = new ArrayList<>();
        int j = 1;
//        for (int j = 0; j < 5; j++) {
        Subscription subscription = new Subscription();
        subscription.setId(1);
        subscription.setActive(j % 2 == 0);
        subscription.setScheme(schemeArray.get(j % 5));
        subscription.setStartAt(j % 3 == 1 ? new Date(System.currentTimeMillis()) : Date.valueOf(LocalDate.now().plusMonths(1)));
//            subscriptionRepository.save(subscription);
        subscriptions.add(subscription);
        Subscription subscription2 = new Subscription();
        subscription2.setId(2);
        subscription2.setActive(j % 2 == 0);
        subscription2.setScheme(schemeArray.get(j % 5));
        subscription2.setStartAt(j % 3 == 1 ? new Date(System.currentTimeMillis()) : Date.valueOf(LocalDate.now().plusMonths(1)));
//            subscriptionRepository.save(subscription2);
        subscriptions.add(subscription2);
//        }

        int i=1;
//        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setCallTime(i * 10);
            user.setLocalOnlineTime(i * 256);
            user.setMessageCount(i * 50);
            user.setOnlineTime((10 - i) * 256);
            user.setName("user" + i);
            user.setSubscriptionList(subscriptions);
            userRepository.save(user);

//        }
    }
}
