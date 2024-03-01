package db;

import model.Comment;
import model.Post;
import model.PostCategory;
import model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                post.setTitle(result.getString("title"));
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
                post.setTitle(result.getString("title"));
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
    public static void addPost(Post post, Long category_id) {
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO posts (post_date, category_id, title, content) " +
                            "VALUES (now(), ?, ?, ?)"
            );
            statement.setLong(1, category_id);
            statement.setString(2, post.getTitle());
            statement.setString(3, post.getContent());
            statement.executeUpdate();
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String createUser(User user, String rePassword) {
        User currentUser = findByEmail(user.getEmail());
        if(currentUser != null) {
            return "emailError";
        }
        if (!Objects.equals(user.getPassword(), rePassword)) {
            return "rePassword error";
        }
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
        return "success";
    }
    public static User findByEmail(String email) {
        User user = null;
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users " +
                            "WHERE email = ?"
            );
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user = new User();
                user.setId(result.getLong("id"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("full_name"));
                user.setRole_id(result.getString("role_id"));
            }
            statement.close();
        }
        catch  (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public static void updatePost(Post post, Long category_id) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE posts SET post_date = now(), category_id = ?, title = ?, content = ? " +
                            "WHERE id = ?"
            );
            preparedStatement.setLong(1, category_id);
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getContent());
            preparedStatement.setLong(4, post.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static User updateUser(User user) {
        User updatedUser = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET full_name = ?, password = ? " +
                            "WHERE id = ?"
            );
            preparedStatement.setString(1, user.getFull_name());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users " +
                            "WHERE id = ?"
            );
            preparedStatement.setLong(1, user.getId());
            ResultSet result = preparedStatement.executeQuery();
            updatedUser = new User();
            if (result.next()) {
                updatedUser.setId(result.getLong("id"));
                updatedUser.setEmail(result.getString("email"));
                updatedUser.setFull_name(result.getString("full_name"));
                updatedUser.setPassword(result.getString("password"));
                updatedUser.setRole_id(result.getString("role_id"));
            }
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedUser;
    }
    public static void addComment(String comment, Long user_id, Long post_id) {
        Comment newComment = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO comments (value, post_date, user_id, post_id) " +
                            "VALUES (?,now(),?,?)"
            );
            preparedStatement.setString(1,comment);
            preparedStatement.setLong(2,user_id);
            preparedStatement.setLong(3,post_id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Comment> getCommentsByPost(Long blogId) {
        List<Comment> commentList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT c.*, ps.title, ps.content, u.full_name, u.email, u.role_id  FROM comments c " +
                            "INNER JOIN posts ps ON ps.id = c.post_id " +
                            "INNER JOIN users u ON u.id = c.user_id " +
                            "WHERE c.post_id = ?"
            );
            preparedStatement.setLong(1, blogId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
               Comment comment = new Comment();
               comment.setId(result.getLong("id"));
               comment.setValue(result.getString("value"));
               comment.setPost_date(result.getObject("post_date", LocalDateTime.class));
               User user = new User();
               user.setId(result.getLong("user_id"));
               user.setFull_name(result.getString("full_name"));
               user.setEmail(result.getString("email"));
               user.setRole_id(result.getString("role_id"));
               comment.setUser(user);
               Post post = new Post();
               post.setId(result.getLong("post_id"));
               post.setTitle(result.getString("title"));
               post.setContent(result.getString("content"));
               comment.setPost(post);
               commentList.add(comment);
            }
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;
    }
}
