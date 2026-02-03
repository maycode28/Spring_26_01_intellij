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
<div class="flex justify-between">
    <h1 class="p-7">${article.id}번 게시글 상세</h1>
    <div class="p-7"><i class="fa-solid fa-eye"></i>${article.hitCount}</div>
</div>

<section class="mt-8 text-xl px-4">
    <div class="mx-auto">
        <table class="table" border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
            <tbody>
            <tr>
                <th style="text-align: center;">게시판</th>
                <td style="text-align: center;">${article.board}</td>
            </tr>
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
            <button class="btn btn-outline btn-ghost" type="button" onClick="location.href= document.referrer;">뒤로 가기</button>
            <c:if test="${article.userCanModify}">
                <button class="btn btn-outline btn-warning" type="button"
                        onClick="location.href='../article/modify?id=${article.id}';">수정
                </button>
            </c:if>
            <c:if test="${article.userCanDelete}">
                <button class="btn btn-outline btn-error" type="button"
                        onClick="location.href='../article/doDelete?id=${article.id}';">삭제
                </button>
            </c:if>
        </div>
    </div>
</section>
<%@ include file="../common/foot.jspf" %>