package homework.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class Scheme {

    @Id
    @Column(name = "scheme_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "scheme_name")
    private String name;

    @Column(name = "price")
    @ColumnDefault("0")
    private Float price;

    @Column(name = "free_call_time")
    @ColumnDefault("0")
    private Integer freeCallTime;

    @Column(name = "free_message_count")
    @ColumnDefault("0")
    private Integer freeMessageCount;

    @Column(name = "free_online_data")
    @ColumnDefault("0")
    private Integer freeOnlineData;

    @Column(name = "free_online_local_data")
    @ColumnDefault("0")
    private Integer freeOnlineLocalData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getFreeCallTime() {
        return freeCallTime;
    }

    public void setFreeCallTime(Integer freeCallTime) {
        this.freeCallTime = freeCallTime;
    }

    public Integer getFreeMessageCount() {
        return freeMessageCount;
    }

    public void setFreeMessageCount(Integer freeMessageCount) {
        this.freeMessageCount = freeMessageCount;
    }

    public Integer getFreeOnlineData() {
        return freeOnlineData;
    }

    public void setFreeOnlineData(Integer freeOnlineData) {
        this.freeOnlineData = freeOnlineData;
    }

    public Integer getFreeOnlineLocalData() {
        return freeOnlineLocalData;
    }

    public void setFreeOnlineLocalData(Integer freeOnlineLocalData) {
        this.freeOnlineLocalData = freeOnlineLocalData;
    }
}
