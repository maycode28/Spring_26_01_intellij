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

<c:set var="pageTitle" value="DETAIL"></c:set>

<%@ include file="../common/head.jspf" %>

<hr/>

<h1>${article.id}번 게시글 상세</h1>
<section class="mt-8 text-xl px-4">
    <div class="mx-auto">
        <table border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
            <tbody>
            <tr>
                <th style="text-align: center;">번호</th>
                <td style="text-align: center;">${article.id}</td>
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
                <td style="text-align: center;">${article.title}</td>
            </tr>
            <tr>
                <th style="text-align: center;">내용</th>
                <td style="text-align: center;">${article.body}</td>
            </tr>
            </tbody>
        </table>

        <div class="btns">
            <button type="button" onClick="history.back();">뒤로가기</button>
            <c:if test="${isAuthor}">
            <button type="button" onClick="location.href='../article/modify?id=${article.id}';">수정</button>
            <button type="button" onClick="location.href='../article/doDelete?id=${article.id}';">삭제</button>
            </c:if>
        </div>
    </div>
</section>
<%@ include file="../common/foot.jspf" %>