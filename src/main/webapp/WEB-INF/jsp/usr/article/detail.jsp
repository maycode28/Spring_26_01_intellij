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

<c:set var="pageTitle" value="DETAIL"></c:set>

<%@ include file="../common/head.jspf"%>

<hr />

<h1>${article.id}번 게시글 상세</h1>

<div>번호 : ${article.id}</div>
<div>날짜 : ${article.regDate.substring(0,10)}</div>
<div>작성자 : ${article.memberId}</div>
<div>제목 : ${article.title}</div>
<div>내용 : ${article.body}</div>

<%@ include file="../common/foot.jspf"%>