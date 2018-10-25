package homework;


import homework.service.InitService;
import homework.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final SubscriptionService subscriptionService;

    private final InitService initService;

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    @Autowired
    public Main(SubscriptionService subscriptionService, InitService initService) {
        this.subscriptionService = subscriptionService;
        this.initService = initService;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("启动项目并初始化数据");
        initService.initData();
        logger.info("初始化数据成功");

        searchSubscriptionHistory(1);
        subscribeScheme(2,3,false);
        unsubscribeScheme(2);
        callPay(1,10);
        onlinePay(1,1024,1024);
        monthPay(1);
    }

    /**
     * 查询用户订阅历史
     * @param userId 用户id（默认id范围 1-10）
     */
    public void searchSubscriptionHistory(Integer userId) {
        subscriptionService.searchSubscriptionHistory(userId);
    }

    /**
     * 用户订阅套餐
     * @param userId 用户id（默认id范围 1-10）
     * @param schemeId 套餐id（默认id范围 1-5）
     * @param isActiveNow 立即或下月生效
     */
    public void subscribeScheme(Integer userId, Integer schemeId, Boolean isActiveNow) {
        subscriptionService.subscribeScheme(userId, schemeId, isActiveNow);
    }

    /**
     * 退订某订单
     * @param subscriptionId 订单id（默认id范围 1-50）
     */
    public void unsubscribeScheme(Integer subscriptionId) {
        subscriptionService.unsubscribeScheme(subscriptionId);
    }

    /**
     * 用户通话花费
     * @param userId 用户id（默认id范围 1-10）
     * @param thisCallTime 本次通话时长
     */
    public void callPay(Integer userId, Integer thisCallTime) {
        subscriptionService.callPay(userId, thisCallTime);
    }

    /**
     * 用户使用流量花费
     * @param userId 用户id（默认id范围 1-10）
     * @param thisOnlineData 本次国内流量使用量
     * @param thisOnlineLocalData 本次本地流量使用量
     */
    public void onlinePay(Integer userId, Integer thisOnlineData, Integer thisOnlineLocalData) {
        subscriptionService.onlinePay(userId, thisOnlineData, thisOnlineLocalData);
    }

    /**
     * 用户每月消费
     * @param userId 用户id（默认id范围 1-10）
     */
    public void monthPay(Integer userId) {
        subscriptionService.monthPay(userId);
    }
}