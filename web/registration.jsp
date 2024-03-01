<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 01.03.2024
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <%
                String result = request.getParameter("result");
                if (result != null) {
                if (result.equals("emailError")) {%>
            <div class="alert alert-danger  alert-dismissible fade show" role="alert">
                Такой email уже зарегестрирован!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
               <% } else if (result.equals("rePassword error"))     {   %>
            <div class="alert alert-danger  alert-dismissible fade show" role="alert">
                Пароли не совпадают!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%} else if (result.equals("success")) {%>
            <div class="alert alert-success" role="alert">
                Пользователь успешно создан! <a href="/auth" class="alert-link">Для входа перейдите по этой ссылке!</a>
            </div>
            <%}}%>
            <form action="/registration" method="post">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email:</label>
                    <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                </div>
                <div class="mb-3">
                    <label for="InputFullName" class="form-label">ФИО:</label>
                    <input type="text" name="fullName" class="form-control" id="InputFullName">
                </div>
                <div class="mb-3">
                    <label for="exampleInputName3" class="form-label">Категория:</label>
                    <select name="role_id" id="exampleInputName3" style="width: 100%;">
                        <option value="1">админ</option>
                        <option value="2">пользователь</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Пароль</label>
                    <input type="password" name="password" class="form-control" id="exampleInputPassword1">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword2" class="form-label">Подтвердите пароль</label>
                    <input type="password" name="rePassword" class="form-control" id="exampleInputPassword2">
                </div>
                <button type="submit" class="btn btn-success">Регистрация</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
