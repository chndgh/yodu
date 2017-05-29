package com.cmdt.yundu.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dingguohua on 2017/5/27.
 */

@Entity
@Table(name="message")
public class Message {

    private long id;

    private User user;

    private String content;

    private String link;

    private int replycount;

    private int forwardcount;

    private int favcount;

    private String topic;

    private Date createtime;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getReplycount() {
        return replycount;
    }

    public void setReplycount(int replycount) {
        this.replycount = replycount;
    }

    public int getForwardcount() {
        return forwardcount;
    }

    public void setForwardcount(int forwardcount) {
        this.forwardcount = forwardcount;
    }

    public int getFavcount() {
        return favcount;
    }

    public void setFavcount(int favcount) {
        this.favcount = favcount;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", link='" + link + '\'' +
                ", replycount=" + replycount +
                ", forwardcount=" + forwardcount +
                ", favcount=" + favcount +
                ", topic='" + topic + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
