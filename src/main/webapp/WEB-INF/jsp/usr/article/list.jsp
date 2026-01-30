<%--
  Created by IntelliJ IDEA.
  User: may
  Date: 2026-01-26
  Time: 2:05 p.m.
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<c:set var="pageTitle" value="ARTICLE LIST"></c:set>

<%@ include file="../common/head.jspf" %>

<hr/>
<section class="mt-8 text-xl px-4">
    <div class="flex h-20 mx-auto items-center text-xl">
        <div class="flex-grow">${board}</div>
        <table>
            <tbody class="flex">
            <tr class="p-4">
                <td style="text-align: center;">
                    <a class="hover:underline" href="list">전체 게시판</a>
                </td>

            </tr>
            <c:forEach var="board" items="${boards }">
                <tr class="p-4">
                    <td style="text-align: center;">
                        <a class="hover:underline" href="list?boardId=${board.id } ">${board.name }</a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <div class="mx-auto">
        <table class="table" border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
            <thead>
            <tr>
                <c:choose>
                    <c:when test="${board eq '전체 게시판'}">
                        <th style="text-align: center;">Board</th>
                    </c:when>
                    <c:otherwise>
                        <th style="text-align: center;">ID</th>
                    </c:otherwise>
                </c:choose>
                <th style="text-align: center;">Registration date</th>
                <th style="text-align: center;">Title</th>
                <th style="text-align: center;">Member</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="article" items="${articles }">
                <tr>
                    <c:choose>
                        <c:when test="${board eq '전체 게시판'}">
                            <td style="text-align: center;">${article.board }</td>
                        </c:when>
                        <c:otherwise>
                            <td style="text-align: center;">${article.id }</td>
                        </c:otherwise>
                    </c:choose>

                    <td style="text-align: center;">${article.regDate.substring(0,10) }</td>
                    <td style="text-align: center;">
                        <a href="detail?id=${article.id } ">${article.title }</a>
                    </td>
                    <td style="text-align: center;">${article.author}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <fmt:parseNumber value="${(cPage +9)/10}" integerOnly="true" var="block"/>
    <fmt:parseNumber value="${(totalPage+9)/10}" integerOnly="true" var="lastBlock"/>
    <div align="center">
        <table border="1" cellpadding="5">
            <tbody>
            <tr>
                <c:if test="${block ne '1'}">
                    <fmt:parseNumber value="${block*10-10}" integerOnly="true" var="beforeBlock"/>
                    <td style="text-align: center;"><a href="list?page=${beforeBlock}">◀</a></td>
                </c:if>
                <c:choose>
                    <c:when test="${block eq lastBlock}">
                        <fmt:parseNumber value="${totalPage}" integerOnly="true" var="lastNumber"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:parseNumber value="${block*10}" integerOnly="true" var="lastNumber"/>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="page" begin="${block*10-9}" end="${lastNumber}" step="1">
                    <c:choose>
                        <c:when test="${board eq '전체 게시판'}">
                            <td style="text-align: center;"><a class="btn ${page==cPage ? 'btn-active' : ''}"
                                                               href="list?page=${page}">${page}</a></td>
                        </c:when>
                        <c:otherwise>
                            <td style="text-align: center;"><a class="btn ${page==cPage ? 'btn-active' : ''}"
                                                               href="list?page=${page}&id=${boardId}">${page}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${block ne lastBlock}">
                    <fmt:parseNumber value="${block*10+1}" integerOnly="true" var="nextBlock"/>
                    <td style="text-align: center;"><a href="list?page=${nextBlock}">▶</a></td>
                </c:if>
            </tr>
            </tbody>
        </table>
    </div>
</section>

<%@ include file="../common/foot.jspf" %>