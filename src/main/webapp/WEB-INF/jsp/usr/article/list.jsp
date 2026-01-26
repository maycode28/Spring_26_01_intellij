<%--
  Created by IntelliJ IDEA.
  User: may
  Date: 2026-01-26
  Time: 2:05 p.m.
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
    <style>
        table>thead>tr>th, table>tbody>tr>td {
            padding: 10px;
        }
    </style>
</head>
<body>
<a href="../home/main">메인으로 이동</a>

<h1>게시글 목록</h1>
<table border="1"
       style="border-collapse: collapse; border-color: green">
    <thead>
    <tr>
        <th>번호</th>
        <th>날짜</th>
        <th>제목</th>
        <th>작성자</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="article" items="${articles}">
    <tr style="text-align: center;">
        <td>${article.id}번</td>
        <td>${article.regDate.substring(0,10)}</td>
        <td><a href="detail?id=${article.id}">${article.title}</a></td>
        <td>${article.memberId}</td>

    </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>