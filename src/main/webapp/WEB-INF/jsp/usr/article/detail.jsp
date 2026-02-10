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
<script>
    const params = {};
    params.id = parseInt('${param.id}');
</script>

<script>
    function ArticleDetail__doIncreaseHitCount() {
        $.get('../article/doIncreaseHitCountRd', {
            id: params.id,
            ajaxMode: 'Y'
        }, function (data) {
            $('.article-detail__hit-count').html(data.data1);
        }, 'json')
    }

    $(function () {
        ArticleDetail__doIncreaseHitCount();
    })
</script>

<script>
    function ArticleDetail__onReactionClick(reaction) {
        if (reaction === 1) {
            if (reactionStatus === 1) {
                reaction = 0;
            } else {
                reaction = 1;
            }

        } else if (reaction === -1) {
            if (reactionStatus === -1) {
                reaction = 0;
            } else {
                reaction = -1;
            }
        }
        ArticleDetail__doUpdateReaction(reaction);
    }

    function ArticleDetail__doUpdateReaction(reaction) {
        $.get('../reaction/updateReaction', {
            memberId:${memberId},
            relDataTypeCode: 'article',
            relId: params.id,
            reactionStatus: reaction
        }, function () {
            ArticleDetail__getReactionCount();
            Reaction__getCurrentReactionStatus();
        }, 'json')
    }

</script>

<script>
    function ArticleDetail__getReactionCount() {
        $.get('../reaction/getReactionCount', {
            relDataTypeCode: 'article',
            relId: params.id,
            ajaxMode: 'Y'
        }, function (data) {
            $('.article-detail__reaction-count').html(data);
        }, 'json')
    }

    // $(function () {
    //     ArticleDetail__getReactionCount();
    // })
</script>

<script>
    let reactionStatus = 0;

    function Reaction__getCurrentReactionStatus() {
        $.get('../reaction/getCurrentReactionStatus', {
            memberId:${memberId},
            relDataTypeCode: 'article',
            relId: params.id
        }, function (data) {
            reactionStatus = data;
            renderReactionUI();
        }, 'json')
    }

    $(function () {
        Reaction__getCurrentReactionStatus();
    })

    function renderReactionUI() {
        $('.btn-like i')
            .removeClass('fa-solid')
            .addClass('fa-regular');

        $('.btn-dislike i')
            .removeClass('fa-solid')
            .addClass('fa-regular');

        if (reactionStatus === 1) {
            $('.btn-like i')
                .removeClass('fa-regular')
                .addClass('fa-solid');
        } else if (reactionStatus === -1) {
            $('.btn-dislike i')
                .removeClass('fa-regular')
                .addClass('fa-solid');
        }
    }
</script>

<div class="flex justify-between">
    <h1 class="p-7">${article.id}번 게시글 상세</h1>
    <div class="p-7"><i class="fa-solid fa-eye"></i><span class="article-detail__hit-count">${article.hitCount}</span>
    </div>
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
            <tr>
                <th style="text-align: center;">DISLIKE</th>
                <td style="text-align: center;">${article.extra__badReactionPoint }</td>
            </tr>
            <tr>
                <th style="text-align: center;">SUM</th>
                <td style="text-align: center;">${article.extra__sumReactionPoint }</td>
            </tr>
            </tbody>
        </table>
        <div class="flex justify-between">
            <div class="btns">
                <button class="btn btn-outline btn-ghost" type="button" onClick="location.href= document.referrer;">뒤로
                    가기
                </button>
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
            <div class="btns">
                <button class="btn btn-outline btn-ghost btn-like" type="button"
                        onClick="ArticleDetail__onReactionClick(1)">
                    <i class="fa-regular fa-thumbs-up"></i>
                    <div class="inline article-detail__reaction-count">${article.extra__goodReactionPoint}</div>
                </button>
                <button class="btn btn-outline btn-ghost btn-dislike" type="button"
                        onClick="ArticleDetail__onReactionClick(-1)">
                    <i class="fa-regular fa-thumbs-down"></i>
                </button>
            </div>

        </div>
    </div>
</section>
<%@ include file="../common/foot.jspf" %>