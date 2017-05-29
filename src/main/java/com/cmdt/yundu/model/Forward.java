package com.cmdt.yundu.model;

import javax.persistence.*;

/**
 * Created by dingguohua on 2017/5/27.
 */

@Entity
@Table(name="forward")
public class Forward {

    private long id;

    private User user;

    private User bUser;

    private Message message;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "buid")
    public User getbUser() {
        return bUser;
    }

    public void setbUser(User bUser) {
        this.bUser = bUser;
    }

    @ManyToOne
    @JoinColumn(name = "mid")
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Forward{" +
                "id=" + id +
                ", user=" + user +
                ", bUser=" + bUser +
                ", message=" + message +
                '}';
    }
}
