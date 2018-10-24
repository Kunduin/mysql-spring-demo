package homework;


import homework.service.InitService;
import homework.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final SubscriptionService subscriptionService;

    private final InitService initService;

    @Autowired
    public Application(SubscriptionService subscriptionService, InitService initService) {
        this.subscriptionService = subscriptionService;
        this.initService = initService;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
//        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        initService.initData();
    }
}