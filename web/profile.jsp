<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 01.03.2024
  Time: 22:07
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
            <form action="/profile" method="post">
                <div class="mb-3">
                    <input type="hidden" name="id" value="<%=user.getId()%>">
                    <label for="exampleInputName" class="form-label">email:</label>
                    <input type="text" name="email" readonly value="<%=user.getEmail()%>" class="form-control" id="exampleInputName">
                </div>
                <div class="mb-3">
                    <label for="exampleInputName3" class="form-label">Категория:</label>
                    <select name="category" id="exampleInputName3" style="width: 100%;" disabled>
                        <option value="1" <%if (user.getRole_id() == "1") {%>selected <%}%>>админ</option>
                        <option value="2" <%if (user.getRole_id() == "2") {%>selected <%}%>>пользователь</option>
                    </select>
                </div>
                <div>
                <label for="exampleInputName1" class="form-label">Пароль:</label>
                <input type="password" name="password" value="<%=user.getPassword()%>" class="form-control" id="exampleInputName1">
                </div>
                <div>
                <label for="exampleInputName2" class="form-label">ФИО:</label>
                <input type="text" name="fullName" value="<%=user.getFull_name()%>" class="form-control" id="exampleInputName2">
                </div>
                <div>
                    <button type="submit" class="btn btn-success mt-3">Сохранить изменения</button>
                </div>
            </form>
            <form action="/logout" method="post">
                <button type="submit" class="btn btn-primary mt-3">Выйти</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
