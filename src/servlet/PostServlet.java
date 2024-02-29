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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/posts")
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            resp.sendRedirect("/auth");
            return;
        }
        List<Post> posts = DbManager.getPosts();
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("posts.jsp").forward(req, resp);
    }
}
