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

<!-- 변수 -->
<script>
	var isAlreadyAddGoodRp = ${isAlreadyAddGoodRp};
	var isAlreadyAddBadRp = ${isAlreadyAddBadRp};
</script>

<!-- 좋아요 싫어요  -->
<script>
<!-- 좋아요 싫어요 버튼	-->
	function checkRP() {
		if (isAlreadyAddGoodRp == true) {
            $('#likeButton>i').removeClass('fa-regular');
            $('#likeButton>i').addClass('fa-solid');
		} else if (isAlreadyAddBadRp == true) {
            $('#dislikeButton>i').removeClass('fa-regular');
			$('#dislikeButton>i').addClass('fa-solid');
		} else {
			return;
		}
	}

	function doLike(articleId) {

		$.ajax({
			url : '/usr/reaction/doLike',
			type : 'POST',
			data : {
				relDataTypeCode : 'article',
				relId : articleId
			},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				console.log('data.data1Name : ' + data.data1Name);
				console.log('data.data1 : ' + data.data1);
				console.log('data.data2Name : ' + data.data2Name);
				console.log('data.data2 : ' + data.data2);
				if (data.resultCode.startsWith('S-')) {
					var likeButton = $('#likeButton>i');
					var likeCountC = $('.likeCount');
					var dislikeButton = $('#dislikeButton>i');
                    var dislikeCountC = $('.dislikeCount');
                    var sumPointC = $('.sumPoint');
                    var sumPoint=data.data1-data.data2;

					if (data.resultCode == 'S-1') {
						likeButton.removeClass('fa-solid');
                        likeButton.addClass('fa-regular');
						likeCountC.text(data.data1);
                        sumPointC.text(sumPoint);
					} else if (data.resultCode == 'S-2') {
						dislikeButton.removeClass('fa-solid');
                        dislikeButton.addClass('fa-regular');
                        dislikeCountC.text(data.data2);
                        likeButton.removeClass('fa-regular');
						likeButton.addClass('fa-solid');
						likeCountC.text(data.data1);
                        sumPointC.text(sumPoint);
					} else {
						likeButton.removeClass('fa-regular');
						likeButton.addClass('fa-solid');
						likeCountC.text(data.data1);
                        sumPointC.text(sumPoint);
					}

				} else {
					alert(data.msg);
				}

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('좋아요 오류 발생 : ' + textStatus);

			}

		});
	}

	function doDislike(articleId) {

		$.ajax({
			url : '/usr/reaction/doDislike',
			type : 'POST',
			data : {
				relDataTypeCode : 'article',
				relId : articleId
			},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				console.log('data.data1Name : ' + data.data1Name);
				console.log('data.data1 : ' + data.data1);
				console.log('data.data2Name : ' + data.data2Name);
				console.log('data.data2 : ' + data.data2);
				if (data.resultCode.startsWith('S-')) {
					var likeButton = $('#likeButton>i');
					var likeCountC = $('.likeCount');
					var dislikeButton = $('#dislikeButton>i');
                    var dislikeCountC = $('.dislikeCount');
                    var sumPointC = $('.sumPoint');
                    var sumPoint=data.data1-data.data2;

					if (data.resultCode == 'S-1') {
						dislikeButton.removeClass('fa-solid');
                        dislikeButton.addClass('fa-regular');
                        dislikeCountC.text(data.data2);
                        sumPointC.text(sumPoint);
					} else if (data.resultCode == 'S-2') {
						likeButton.removeClass('fa-solid');
                        likeButton.addClass('fa-regular');
						likeCountC.text(data.data1);
						dislikeButton.removeClass('fa-regular');
                        dislikeButton.addClass('fa-solid');
                        dislikeCountC.text(data.data2);
                        sumPointC.text(sumPoint);

					} else {
                        console.log("1");
						dislikeButton.removeClass('fa-regular');
                        dislikeButton.addClass('fa-solid');
                        dislikeCountC.text(data.data2);
                        sumPointC.text(sumPoint);
					}

				} else {
					alert(data.msg);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('싫어요 오류 발생 : ' + textStatus);
			}

		});
	}

	$(function() {
		checkRP();
	});
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
                <td style="text-align: center;"><span class="dislikeCount">${article.dislikePoint}</span></td>
            </tr>
            <tr>
                <th style="text-align: center;">SUM</th>
                <td style="text-align: center;"><span class="sumPoint">${article.likePoint-article.dislikePoint}</span></td>
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
                <button id="likeButton" class="btn btn-outline btn-ghost btn-like" type="button"
                        onClick="doLike(${param.id})">
                    <i class="fa-regular fa-thumbs-up"></i>
                    <span class="likeCount">${article.likePoint}</span>
                </button>
                <button id="dislikeButton" class="btn btn-outline btn-ghost btn-dislike" type="button"
                        onClick="doDislike(${param.id})">
                    <i class="fa-regular fa-thumbs-down"></i>
                </button>
            </div>

        </div>
    </div>
</section>
<%@ include file="../common/foot.jspf" %>