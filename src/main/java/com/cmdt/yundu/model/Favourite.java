package com.cmdt.yundu.model;

import javax.persistence.*;

/**
 * Created by dingguohua on 2017/5/27.
 */

@Entity
@Table(name="favourite")
public class Favourite {

    private long id;

    private User user;

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
    @JoinColumn(name = "mid")
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "id=" + id +
                ", user=" + user +
                ", message=" + message +
                '}';
    }
}
