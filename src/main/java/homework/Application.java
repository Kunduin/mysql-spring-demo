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
public class Application implements CommandLineRunner {

    private final SubscriptionService subscriptionService;

    private final InitService initService;

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    public Application(SubscriptionService subscriptionService, InitService initService) {
        this.subscriptionService = subscriptionService;
        this.initService = initService;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("启动项目并初始化数据");
        initService.initData();
        logger.info("初始化数据成功");
        subscriptionService.searchSubscriptionHistory(1);
        subscriptionService.subscribeScheme(2,3,false);
        subscriptionService.unsubscribeScheme(2);
        subscriptionService.callPay(1,10);
        subscriptionService.onlinePay(1,1024,1024);
        subscriptionService.monthPay(1);
    }
}