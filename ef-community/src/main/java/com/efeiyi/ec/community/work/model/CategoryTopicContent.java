package com.efeiyi.ec.community.work.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 主题内容
 * Created by Administrator on 2016/4/8.
 */
@Entity
@Table(name = "clan_category_topic_content")
public class CategoryTopicContent {

    private String id;
    private CategoryTopic categoryTopic;
    private String content;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_topic_id")
    public CategoryTopic getCategoryTopic() {
        return categoryTopic;
    }

    public void setCategoryTopic(CategoryTopic categoryTopic) {
        this.categoryTopic = categoryTopic;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
