package db;

import model.Post;
import model.PostCategory;
import model.User;

import java.security.spec.ECField;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DbManager {
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/finalProject",
                    "postgres",
                    "admin"
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT p.*, pc.name FROM posts p " +
                            "INNER JOIN post_categories pc ON pc.id = p.category_id " +
                            "ORDER BY p.post_date DESC"
            );
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Post post = new Post();
                post.setId(result.getLong("id"));
                post.setPost_date(result.getObject("post_date", LocalDateTime.class));
                post.setTitel(result.getString("title"));
                post.setContent(result.getString("content"));
                PostCategory postCategory = new PostCategory();
                postCategory.setId(result.getLong("category_id"));
                postCategory.setName(result.getString("name"));
                post.setPostCategory(postCategory);
                posts.add(post);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }

    public static User auth(String email, String password) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT u.* FROM users u " +
                            "WHERE u.email = ? and u.password = ? "
            );
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user = new User();
                user.setId(result.getLong("id"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setFull_name(result.getString("full_name"));
                user.setRole_id(result.getString("role_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static Post getById(Long id) {
        Post post = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT p.*, pc.name FROM posts p " +
                            "INNER JOIN post_categories pc ON pc.id = p.category_id " +
                            "WHERE p.id = ?"
            );
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                post = new Post();
                post.setId(result.getLong("id"));
                post.setPost_date(result.getObject("post_date", LocalDateTime.class));
                post.setTitel(result.getString("title"));
                post.setContent(result.getString("content"));
                PostCategory postCategory = new PostCategory();
                postCategory.setId(result.getLong("category_id"));
                postCategory.setName(result.getString("name"));
                post.setPostCategory(postCategory);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return post;
    }
    public static void deletePost(Long id) {
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM posts " +
                            "WHERE id = ?"
            );
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static void addPost(Post post) {
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO posts (post_date, category_id, titel, content) " +
                            "VALUES (now(), ?, ?, ?)"
            );
            statement.setLong(1, post.getPostCategory().getId());
            statement.setString(2, post.getTitel());
            statement.setString(3, post.getContent());
            statement.executeUpdate();
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addUser(User user) {
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (email, password, full_name, role_id) " +
                            "VALUES (?,?,?,?)"
            );
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFull_name());
            statement.setString(4, user.getRole_id());
            statement.executeUpdate();
            statement.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
