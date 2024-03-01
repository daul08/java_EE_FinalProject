<%@ page import="model.Post" %>
<%@ page import="model.User" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.Comment" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 29.02.2024
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<%
    String readonly = "readonly";
    String disabled = "disabled";
    Post post = (Post) request.getAttribute("post");
    if (user != null && user.getRole_id().equals("1")) {
        readonly = "null";
        disabled = "null";
    }
%>
<div class="container p-0">
    <div class="row justify-content-center">
        <form action="/postDetail" method="post">
            <div class="mb-3">
                <input type="hidden" name="id" value="<%=post.getId()%>">
                <label for="exampleInputName" class="form-label">Title:</label>
                <input type="text" name="title" <%=readonly%> value="<%=post.getTitle()%>" class="form-control" id="exampleInputName">
            </div>
            <div class="mb-3">
                <label for="exampleInputName3" class="form-label">Категория:</label>
                <select name="category" id="exampleInputName3" style="width: 100%;" <%=disabled%>>
                    <option value="1" <%if (post.getPostCategory().getName().equals("Sport")) {%>selected <%}%>>Sport</option>
                    <option value="2" <%if (post.getPostCategory().getName().equals("Music")) {%>selected <%}%>>Music</option>
                    <option value="3" <%if (post.getPostCategory().getName().equals("Cinema")) {%>selected <%}%>>Cinema</option>
                    <option value="4" <%if (post.getPostCategory().getName().equals("Politic")) {%>selected <%}%>>Politic</option>
                </select>
            </div>
            <div class="mb-1">
                <label for="exampleInputName1" class="form-label">Описание:</label>
            </div>
            <div class="mb-3">
                <textarea id="exampleInputName1" <%=readonly%> name="content" rows="4" class="form-control"><%=post.getContent()%></textarea>
            </div>
            <div class="mb-3">
                <label for="exampleInputName2" class="form-label">Дата публикации:</label>
                <input type="text" name="date" readonly value="<%=post.getPost_date()%>" class="form-control" id="exampleInputName2">
            </div>
            <%
                if(user != null && user.getRole_id().equals("1")){%>
                <div>
                    <button type="submit" class="btn btn-success">Сохранить изменения</button>
                </div>
            <% } %>

        </form>
        <%
            if(user != null && user.getRole_id().equals("1")){%>
        <div>
            <button type="submit" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">Удалить</button>
        </div>
           <% } %>
    </div>
    <hr>
    <form action="/comment" method="post">
        <input class="mb-2" type="hidden" name="post_id" value="<%=post.getId()%>">
        <textarea name="comment" placeholder="Insert comment..." class="form-control "></textarea>
        <button class="btn btn-primary">Добавить комментарий</button>
    </form>


    <%
        List<Comment> commentList = (List<Comment>) request.getAttribute("comments");
        for (Comment comment: commentList) {%>
            <h4><%=comment.getUser().getFull_name()%></h4>
            <p><%=comment.getValue()%></p>
            <p class="mb-2"><%=comment.getPost_date()%></p>
        <%}    %>

</div>
<!-- Modal for УДАЛИТЬ-->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Подтвердите удаление</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="/deletePost" method="post">
                <div class="modal-body">
                    <input type="hidden" name="id" value="<%=post.getId()%>">
                    <h1 style="text-align: center">Вы уверены что хотите удалить запись?</h1>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Нет</button>
                    <button type="submit" class="btn btn-success">Да</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
