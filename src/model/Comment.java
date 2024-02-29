package model;

import java.time.LocalDateTime;

public class Comment {
    public long id;
    public String value;
    public LocalDateTime post_date;
    public User user;
    public Post post;

    public Comment(long id, String value, LocalDateTime post_date, User user, Post post) {
        this.id = id;
        this.value = value;
        this.post_date = post_date;
        this.user = user;
        this.post = post;
    }
    public Comment(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getPost_date() {
        return post_date;
    }

    public void setPost_date(LocalDateTime post_date) {
        this.post_date = post_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
