package DevLewi.My_Blog.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "follow") // Ensure this matches your database table name
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the follow record

    private Long userId; // The user who is being followed
    private Long follower; // The user who is following
    private Date followDate; // The date the follow action occurred

    public Follow() {
    }

    public Follow(Long id, Long userId, Long follower, Date followDate) {
        this.id = id;
        this.userId = userId;
        this.follower = follower;
        this.followDate = followDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFollower() {
        return follower;
    }

    public void setFollower(Long follower) {
        this.follower = follower;
    }

    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", userId=" + userId +
                ", follower=" + follower +
                ", followDate=" + followDate +
                '}';
    }
}
