<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>CAFE24 OAUTH 인증 테스트 결과 페이지</h1>
</div>
<c:forEach items="${productList}" var="product" step="1" varStatus="status">
    <p>샵 번호: ${product.shopNo}</p>
    <p>상품 번호: ${product.productNo}</p>
    <p>상품 코드: ${product.productCode}</p>
    <p>상품 이름: ${product.productName}</p>
    <p>상품 이미지: <img style="width: 200px; height: 200px"
                    src="<c:set value="${product.detailImage}"/>"/></p>
</c:forEach>

</body>
</html>