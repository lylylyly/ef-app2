package com.efeiyi.ec.community.work.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 技艺大分类
 * Created by Administrator on 2016/4/6.
 */
@Entity
@Table(name = "clan_category")
public class Category {
    private String id;
    private String name;
    private String memo;
    private String status;

    @Id
    @GenericGenerator(name = "id", strategy = "com.ming800.core.p.util.M8idGenerator")
    @GeneratedValue(generator = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
