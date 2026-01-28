<%--
  Created by IntelliJ IDEA.
  User: may
  Date: 2026-01-27
  Time: 5:36 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE WRITE"></c:set>

<%@ include file="../common/head.jspf"%>

<hr />

<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<form action="../article/doWrite" method="POST">
			<table class="table" border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<tbody>
					<tr>
						<th>제목</th>
						<td style="text-align: center;">
							<input class="input input-neutral" name="title" autocomplete="off" type="text" placeholder="제목 입력" />
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td style="text-align: center;">
							<input class="input input-neutral" name="body" autocomplete="off" type="text" placeholder="내용 입력" />
						</td>
					</tr>
					<tr>
						<th></th>
						<td style="text-align: center;">
							<input class="btn btn-outline btn-ghost" type="submit" value="글 작성" />
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="btns">
			<button class="btn btn-outline btn-ghost" type="button" onClick="history.back();">뒤로가기</button>


		</div>
	</div>
</section>

<%@ include file="../common/foot.jspf"%>