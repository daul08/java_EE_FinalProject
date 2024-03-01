package servlet;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Comment;
import model.User;
import model.Post;
import java.util.List;

import java.io.IOException;

@WebServlet("/postDetail")
public class PostDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            resp.sendRedirect("/auth");
            return;
        }
        Long id = Long.valueOf(req.getParameter("id"));
        Post post = DbManager.getById(id);
        req.setAttribute("post", post);
        List<Comment> comments = DbManager.getCommentsByPost(id);
        req.setAttribute("comments", comments);
        req.getRequestDispatcher("postDetail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Long category_id = Long.valueOf(req.getParameter("category"));
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setId(id);
        DbManager.updatePost(post, category_id);
        resp.sendRedirect("/posts");
    }
}
