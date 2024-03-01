<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 28.02.2024
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand ms-2" href="/posts">BITLAB NEWS</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <%
            User user = (User) session.getAttribute("currentUser");
        %>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0 me-2">
                <%
                    if (user != null && user.getRole_id().equals("1")) {%>
                <li class="nav-item">
                    <a class="nav-link active btn btn-primary text-white" aria-current="page" href="/addPost">Добавить новость</a>
                </li>
                    <%}%>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/posts">Новости</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/profile">Профиль</a>
                </li>
                <%if (user == null) {%>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/auth">Войти</a>
                </li>
                    <%}%>
            </ul>
        </div>
    </div>
</nav>
</html>
