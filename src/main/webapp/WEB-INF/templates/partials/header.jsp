<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Java Example</title>
    <link href="/static/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid m-0 p-0">
    <nav class="navbar navbar-expand navbar-dark bg-primary">
        <div class="container">
            <span class="navbar-brand">Java</span>
            <div class="navbar-nav text-uppercase">
                <c:forEach items="${mainMenuItems}" var="mainMenuItem">
                    <a class="nav-item nav-link <c:if test="${mainMenuItem.getServletClassName().equals(currentServletClassName)}">active</c:if>" href="<c:out value="${mainMenuItem.getPath()}"/>">
                    <c:out value="${mainMenuItem.getName()}"/>
                    </a>
                </c:forEach>
            </div>
        </div>
    </nav>
</div>

<div class="container p-5">