<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/2/2023
  Time: 11:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" action="create-product" modelAttribute="product">
    <label>Ten san pham</label>
    <form:input type="text"
    class="form-control" aria-describedby="helpId" placeholder="nhap ten san pham" path="productName"/>
    <label>Gia san pham</label>
    <form:input type="text"
                class="form-control" aria-describedby="helpId" placeholder="nhap gia san pham" path="price"/>
    <div class="form-group">
        <label>Danh muc</label>
        <form:select class="form-control" path="category.categoryId">
            <c:forEach items="${categoryList}" var="item">
                <option value="${item.categoryId}">${item.categoryName}</option>
            </c:forEach>
        </form:select>
    </div>
    <button class="btn btn-primary" type="submit">Them</button>
</form:form>
</body>
</html>
