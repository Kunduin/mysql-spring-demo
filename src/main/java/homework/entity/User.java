package homework.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "call_time")
    @ColumnDefault("0")
    private Integer callTime;

    @Column(name = "local_online_time")
    @ColumnDefault("0")
    private Integer localOnlineTime;

    @Column(name = "online_time")
    @ColumnDefault("0")
    private Integer onlineTime;

    @Column(name = "message_count")
    @ColumnDefault("0")
    private Integer messageCount;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_id")
    private List<Subscription> subscriptionList;

    public List<Subscription> getSubscriptionList() {

        return Optional.ofNullable(subscriptionList).orElse(new ArrayList<>());
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        if (subscriptionList != null) {
            this.subscriptionList = new ArrayList<>(subscriptionList);
        } else {
            this.subscriptionList = null;
        }
        this.subscriptionList = subscriptionList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCallTime() {
        return callTime;
    }

    public void setCallTime(Integer callTime) {
        this.callTime = callTime;
    }

    public Integer getLocalOnlineTime() {
        return localOnlineTime;
    }

    public void setLocalOnlineTime(Integer localOnlineTime) {
        this.localOnlineTime = localOnlineTime;
    }

    public Integer getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Integer onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }
}
