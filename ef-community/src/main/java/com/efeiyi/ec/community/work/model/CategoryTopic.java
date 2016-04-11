package com.efeiyi.ec.community.work.model;

import com.efeiyi.ec.community.user.model.MyUser;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 版面主题，每版面有多条
 * Created by Administrator on 2016/4/6.
 */
@Entity
@Table(name = "clan_category_topic")
public class CategoryTopic {
    private String id;
    private String title;
    private MyUser author;
    private Date createDatetime;
    private Category category;
    private List<CategoryTopicMessage> categoryTopicMessageList;
    private String status;
    private CategoryTopicContent categoryTopicContent;

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

    @Column(name = "author")
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
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToMany(mappedBy = "categoryTopic",fetch = FetchType.LAZY)
    @Where(clause = "status <> '0'")
    public List<CategoryTopicMessage> getCategoryTopicMessageList() {
        return categoryTopicMessageList;
    }

    public void setCategoryTopicMessageList(List<CategoryTopicMessage> categoryTopicMessageList) {
        this.categoryTopicMessageList = categoryTopicMessageList;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_topic_content_id")
    public CategoryTopicContent getCategoryTopicContent() {
        return categoryTopicContent;
    }

    public void setCategoryTopicContent(CategoryTopicContent categoryTopicContent) {
        this.categoryTopicContent = categoryTopicContent;
    }
}
