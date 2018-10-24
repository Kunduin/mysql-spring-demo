package homework.service;

import homework.entity.Scheme;
import homework.entity.User;
import homework.repository.SchemeRepository;
import homework.repository.SubscriptionRepository;
import homework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final UserRepository userRepository;

    private final SchemeRepository schemeRepository;

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(UserRepository userRepository, SchemeRepository schemeRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.schemeRepository = schemeRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public void searchSubscriptionHistory(Integer userId){
        System.out.print(userId);
        Optional.ofNullable(userRepository.findById(userId)).orElse(null);
    }



}