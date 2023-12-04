<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/2/2023
  Time: 11:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form action="create-category" method="post" modelAttribute="category">
    <div class="form-group">
        <label >Name</label>
        <form:input type="text"
               class="form-control" name="categoryName"  aria-describedby="helpId" placeholder="" path="categoryName"/>
        <div class="form-check">
            <label class="form-check-label">
                <form:radiobutton  class="form-check-input"  value="1" checked="checked" path="categoryStatus"/>
                active
            </label>
            <label class="form-check-label">
                <form:radiobutton  class="form-check-input" value="0" path="categoryStatus"/>
                Inactive
            </label>
        </div>
    </div>
    <button class="btn btn_primary" type="submit">Them</button>
</form:form>
</body>
</html>
