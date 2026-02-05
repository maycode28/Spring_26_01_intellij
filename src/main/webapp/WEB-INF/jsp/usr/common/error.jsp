<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<script>
    console.log("123")
    alert("${rd.resultCode}/${rd.msg}");
    <c:choose>
    <c:when test="${action eq 'historyBack'}">
    history.back();
    </c:when>
    <c:when test="${action eq 'redirect'}">
    location.replace("${replaceUri}");
    </c:when>
    </c:choose>
</script>