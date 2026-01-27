<%--
  Created by IntelliJ IDEA.
  User: may
  Date: 2026-01-26
  Time: 2:05 p.m.
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<c:set var="pageTitle" value="MODIFY"></c:set>

<%@ include file="../common/head.jspf" %>

<hr/>

<h1>${article.id}번 게시글 수정</h1>
<form action="doModify" method="POST">
		<div>
			<input name="id" type="hidden" value="${article.id}" />
		</div>

		<div>
			번호 :
			${article.id}
		</div>
		<div>
			날짜 :
			${article.regDate}
		</div>
		<div>
			새 제목 : <input name="title" type="text" placeholder="새 제목 입력해"
				value="${article.title}" />
		</div>
		<div>
			새 내용 :
			<textarea name="body" type="text" placeholder="새 내용 입력해">${article.body}</textarea>
		</div>
		<div>
			<input type="submit" value="수정" />
		</div>
	</form>
<%@ include file="../common/foot.jspf" %>