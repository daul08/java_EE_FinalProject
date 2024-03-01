package model;

import java.time.LocalDateTime;

public class Post {
    public long id;
    public LocalDateTime post_date;
    public PostCategory postCategory;
    public String title;
    public String content;

    public Post(long id, LocalDateTime post_date, PostCategory postCategory, String title, String content) {
        this.id = id;
        this.post_date = post_date;
        this.postCategory = postCategory;
        this.title = title;
        this.content = content;
    }

    public Post(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getPost_date() {
        return post_date;
    }

    public void setPost_date(LocalDateTime post_date) {
        this.post_date = post_date;
    }

    public PostCategory getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(PostCategory postCategory) {
        this.postCategory = postCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
