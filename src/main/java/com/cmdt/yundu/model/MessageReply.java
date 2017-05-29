package com.cmdt.yundu.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dingguohua on 2017/5/27.
 */

@Entity
@Table(name="messagereply")
public class MessageReply {

    private long id;

    private Message message;

    private User user;

    private Date createTime;

    private String content;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "mid")
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageReply{" +
                "id=" + id +
                ", message=" + message +
                ", user=" + user +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                '}';
    }
}
