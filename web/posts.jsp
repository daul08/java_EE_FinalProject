<%@ page import="model.Post" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 28.02.2024
  Time: 20:48
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
    List<Post> posts = (List<Post>) request.getAttribute("posts");
%>
<div class="container p-0">
    <div class="row justify-content-center">
        <%
            for(Post post: posts) {%>
        <div class="card mt-3 me-3" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title"><%=post.getTitle()%></h5>
                <h6 class="card-subtitle mb-2 text-body-secondary"><%=post.getPostCategory().name%></h6>
                <p class="card-text"><%=post.getPost_date()%></p>
                <a href="/postDetail?id=<%=post.getId()%>" class="card-link">Подробнее</a>
            </div>
        </div>
            <%}%>
    </div>
</div>
</body>
</html>
