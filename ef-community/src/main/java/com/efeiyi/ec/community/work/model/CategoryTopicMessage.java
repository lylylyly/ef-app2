package com.efeiyi.ec.community.work.model;

import com.efeiyi.ec.community.user.model.MyUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 主题回复记录，每个主题多条
 * Created by Administrator on 2016/4/6.
 */
@Entity
@Table(name = "clan_category_topic_message")
public class CategoryTopicMessage {
    private String id;
    private String title;
    private String content;
    private MyUser author;
    private Date createDatetime;
    private CategoryTopic categoryTopic;
    private String status;

    @Id
    @GenericGenerator(name = "id", strategy = "com.ming800.core.p.model.M8idGenerator")
    @GeneratedValue(generator = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }

    @Column(name = "create_datetime")
    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_topic_id")
    public CategoryTopic getCategoryTopic() {
        return categoryTopic;
    }

    public void setCategoryTopic(CategoryTopic categoryTopic) {
        this.categoryTopic = categoryTopic;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
