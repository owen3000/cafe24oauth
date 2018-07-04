<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/social-buttons-3.css"/>
</head>
<body>
<div class="page-header">
    <h1>CAFE24 OAUTH 인증 테스트 페이지</h1>
</div>
<sec:authorize access="isAnonymous()">
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="row social-button-row">
                <div class="col-lg-4">
                    <form action="<c:url value="/connect2/cafe24" />" method="POST">
                        <input type="hidden" name="mall_id" value="utkg3000" />
                        <p>You haven't created any connections with Cafe24 yet. Click the button to create
                            a connection</p>
                        <p><button type="submit" value="submit">OAuth 인증</button></p>
                    </form>
                </div>
            </div><div class="row social-button-row">
                <div class="col-lg-4">
                    <form action="<c:url value="/connect2/result" />" method="POST">
                        <input type="hidden" name="mall_id" value="utkg3000" />
                        <p>You haven't created any connections with Cafe24 yet. Click the button to create
                            a connection</p>
                        <p><button type="submit" value="submit">Product 가져오기</button></p>
                    </form>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <p><spring:message code="text.login.page.authenticated.user.help"/></p>
</sec:authorize>
</body>
</html>