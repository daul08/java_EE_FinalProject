package servlet;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Post;
import model.User;

import java.io.IOException;

@WebServlet("/addPost")
public class AddPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            resp.sendRedirect("/auth");
            return;
        }
        req.getRequestDispatcher("addPost.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titel = req.getParameter("title");
        Long category_id = Long.valueOf(req.getParameter("category"));
        String content = req.getParameter("content");
        Post post = new Post();
        post.setTitle(titel);
        post.setContent(content);
        DbManager.addPost(post, category_id);
        resp.sendRedirect("/posts");
    }
}
