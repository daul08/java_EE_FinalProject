package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Post {
    public long id;
    public LocalDateTime post_date;
    public PostCategory postCategory;
    public String titel;
    public String content;

    public Post(long id, LocalDateTime post_date, PostCategory postCategory, String titel, String content) {
        this.id = id;
        this.post_date = post_date;
        this.postCategory = postCategory;
        this.titel = titel;
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

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
