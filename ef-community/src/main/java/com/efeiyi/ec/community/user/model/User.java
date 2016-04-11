package com.efeiyi.ec.community.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

/**
 * 匠人
 * Created by Administrator on 2016/4/8.
 */
@Entity
@Table(name = "organization_user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class User {
    private String id;
    private String name;
    private String name2;    //别名
    private Integer status;      // 0假删  1粉丝 2匠人
    private String tempPageUrl;
    private Date lastLoginDatetime;//最后一次登陆时间
    private Date lastLogoutDatetime;//最后一次登陆时间
    protected Date createDatetime;

    @Id
    @GenericGenerator(name = "id", strategy = "com.ming800.core.p.util.M8idGenerator")
    @GeneratedValue(generator = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "truename")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "status")
    @Where(clause = "status = '2'")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "truename2")
    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @JsonIgnore
    @Transient
    public String getTempPageUrl() {
        return tempPageUrl;
    }

    public void setTempPageUrl(String tempPageUrl) {
        this.tempPageUrl = tempPageUrl;
    }

    @Column(name = "last_login_datetime")
    public Date getLastLoginDatetime() {
        return lastLoginDatetime;
    }

    public void setLastLoginDatetime(Date lastLoginDatetime) {
        this.lastLoginDatetime = lastLoginDatetime;
    }
    @Transient
    public Date getLastLogoutDatetime() {
        return lastLogoutDatetime;
    }

    public void setLastLogoutDatetime(Date lastLogoutDatetime) {
        this.lastLogoutDatetime = lastLogoutDatetime;
    }

    @Column(name = "create_datetime")
    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

}
