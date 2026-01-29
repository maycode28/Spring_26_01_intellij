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

<c:set var="pageTitle" value="ARTICLE LIST"></c:set>

<%@ include file="../common/head.jspf"%>

<hr />
<section class="mt-8 text-xl px-4">
<div class="flex h-20 mx-auto items-center text-xl">
<div class="flex-grow">${board}</div>
<table>
<tbody class="flex">
<tr class ="p-4">
						<td style="text-align: center;">
							<a class="hover:underline" href="list" >전체 게시판</a>
						</td>

					</tr>
				<c:forEach var="board" items="${boards }">
					<tr class ="p-4">
						<td style="text-align: center;">
							<a class="hover:underline" href="list?id=${board.id } ">${board.name }</a>
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
					<th style="text-align: center;">ID</th>
					<th style="text-align: center;">Registration date</th>
					<th style="text-align: center;">Title</th>
					<th style="text-align: center;">Member</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="${articles }">
					<tr>
						<td style="text-align: center;">${article.id }</td>
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
</section>

<%@ include file="../common/foot.jspf"%>