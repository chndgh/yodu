package com.cmdt.yundu.model;

import javax.persistence.*;

/**
 * Created by dingguohua on 2017/5/27.
 */
@Entity
@Table(name="photo")
public class Photo {

    private long id;

    private User user;

    private Message message;

    private String name;

    private String address;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", user=" + user +
                ", message=" + message +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
