package com.cmdt.yundu.model;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by dingguohua on 2017/5/25.
 */
@Entity
@Table(name="user")
public class User implements java.io.Serializable{

    private Long id;

    private String alais;

//    private Blob headPortait;

    private String email;

    private String gender;
    @Column(name = "realName")
    private String realName;

    private String phone;

//    private Date createTime;

    private String username;

    private String password;

    private String selfIntroduce;

    private String tag;

    private String vehicleType;

    private String interest;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlais() {
        return alais;
    }

    public void setAlais(String alais) {
        this.alais = alais;
    }

//    public Blob getHeadPortait() {
//        return headPortait;
//    }
//
//    public void setHeadPortait(Blob headPortait) {
//        this.headPortait = headPortait;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSelfIntroduce() {
        return selfIntroduce;
    }

    public void setSelfIntroduce(String selfIntroduce) {
        this.selfIntroduce = selfIntroduce;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", alais='" + alais + '\'' +
//                ", headPortait=" + headPortait +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", realName='" + realName + '\'' +
                ", phone='" + phone + '\'' +
//                ", createTime=" + createTime +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", selfIntroduce='" + selfIntroduce + '\'' +
                ", tag='" + tag + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", interest='" + interest + '\'' +
                '}';
    }
}