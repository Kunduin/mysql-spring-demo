package homework.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "call_time")
    @ColumnDefault("0")
    private Integer callTime;

    @Column(name = "local_online_data")
    @ColumnDefault("0")
    private Integer localOnlineData;

    @Column(name = "online_data")
    @ColumnDefault("0")
    private Integer onlineData;

    @Column(name = "message_count")
    @ColumnDefault("0")
    private Integer messageCount;


    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "user_id")
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

    public Integer getLocalOnlineData() {
        return localOnlineData;
    }

    public void setLocalOnlineData(Integer localOnlineData) {
        this.localOnlineData = localOnlineData;
    }

    public Integer getOnlineData() {
        return onlineData;
    }

    public void setOnlineData(Integer onlineData) {
        this.onlineData = onlineData;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }
}
