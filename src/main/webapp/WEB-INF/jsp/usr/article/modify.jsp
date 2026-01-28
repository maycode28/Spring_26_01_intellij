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
	<table border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
            <tbody>
            <tr>
                <th style="text-align: center;">번호</th>
                <td style="text-align: center;"><input name="id" type="hidden" value="${article.id}" />${article.id}</td>
            </tr>
            <tr>
                <th style="text-align: center;">작성자</th>
                <td style="text-align: center;">${article.author}</td>
            </tr>
            <tr>
                <th style="text-align: center;">작성일</th>
                <td style="text-align: center;">${article.regDate}</td>
            </tr>
            <tr>
                <th style="text-align: center;">수정일</th>
                <td style="text-align: center;">${article.updateDate}</td>
            </tr>
            <tr>
                <th style="text-align: center;">제목</th>
                <td style="text-align: center;"><input name="title" type="text" placeholder="새 제목 입력해"
				value="${article.title}" /></td>
            </tr>
            <tr>
                <th style="text-align: center;">내용</th>
                <td style="text-align: center;"><textarea name="body" type="text" placeholder="새 내용 입력해">${article.body}</textarea></td>
            </tr>
            <tr>
                <td style="text-align: center;"><input type="submit" value="수정" /></td>
            </tr>

            </tbody>
        </table>
</form>
        <div class="btns">
            <button type="button" onClick="history.back();">뒤로가기</button>
        </div>
<%@ include file="../common/foot.jspf" %>