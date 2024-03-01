<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 01.03.2024
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container p-0">
    <div class="row justify-content-center">
        <form action="/addPost" method="post">
            <div class="mb-3">
                <label for="exampleInputName" class="form-label">Titel:</label>
                <input type="text" name="title"  class="form-control" id="exampleInputName">
            </div>
            <div class="mb-3">
                <label for="exampleInputName3" class="form-label">Категория:</label>
                <select name="category" id="exampleInputName3" style="width: 100%;">
                    <option value="1" selected>Sport</option>
                    <option value="2" >Music</option>
                    <option value="3" >Cinema</option>
                    <option value="4" >Politic</option>
                </select>
            </div>
            <div class="mb-1">
                <label for="exampleInputName1" class="form-label">Описание:</label>
            </div>
            <div class="mb-3">
                <textarea id="exampleInputName1" name="content" rows="4" class="form-control"></textarea>
            </div>
            <div>
                <button type="submit" class="btn btn-success">Добавить новость</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>

