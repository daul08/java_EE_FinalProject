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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            resp.sendRedirect("/posts");
            return;
        }
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");
        String fullname= req.getParameter("fullName");
        String roleId = req.getParameter("role_id");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFull_name(fullname);
        user.setRole_id(roleId);
        String result = DbManager.createUser(user, rePassword);
        req.setAttribute("result", result);
        resp.sendRedirect("/registration?result=" + result);
    }
}
