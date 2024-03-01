package servlet;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("comment");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        Long user_id = user.getId();
        Long post_id = Long.valueOf(req.getParameter("post_id"));
        DbManager.addComment(value, user_id, post_id);
        resp.sendRedirect("/postDetail?id=" + post_id);
    }
}
