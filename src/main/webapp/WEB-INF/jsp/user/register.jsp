<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<%--<c:choose>--%>
<%--    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>--%>
<%--    <c:when test="${not empty formBean.id}"><h1>Edit</h1></c:when>--%>
<%--    &lt;%&ndash;    <c:otherwise>undefined</c:otherwise>&ndash;%&gt;--%>
<%--</c:choose>--%>

<div class="form-container">

    <c:choose>
        <c:when test="${empty formBean.id}"><h3>Register</h3></c:when>
        <c:when test="${not empty formBean.id}"><h3>Edit</h3></c:when>
        <%--    <c:otherwise>undefined</c:otherwise>--%>
    </c:choose>
    <form action="/user/registerSubmit" method="get">
        <input type="hidden" name="id" value="${formBean.id}">

        <div class="group">
            <input type="text" name="firstName" id="firstNameId" value="${formBean.firstName}" required>
            <span class="highlight"></span>
            <span class="bar"></span>
            <label>First Name</label>
            <c:forEach items="${bindingResult.getFieldErrors('firstName')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>

        <div class="group">
            <input type="text" name="lastName" id="lastNameId" value="${formBean.lastName}" required>
            <span class="highlight"></span>
            <span class="bar"></span>
            <label>Last Name</label>
            <c:forEach items="${bindingResult.getFieldErrors('lastName')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>

        <div class="group">
            <input type="email" name="email" id="emailId" value="${formBean.email}" required>
            <span class="highlight"></span>
            <span class="bar"></span>
            <label>Email</label>
            <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>

        <div class="group">
            <input type="password" name="password" id="passwordId" required>
            <span class="highlight"></span>
            <span class="bar"></span>
            <label>Password</label>
            <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>

        <div class="group">
            <input type="password" name="confirmPassword" id="confirmPasswordId" required>
            <span class="highlight"></span>
            <span class="bar"></span>
            <label>Password Confirm</label>
            <c:forEach items="${bindingResult.getFieldErrors('confirmPassword')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>

        <button class="submit-btn" type="submit">Submit</button>
    </form>

</div>

<%--&lt;%&ndash;method = get to show query after submit, method  is = post before &ndash;%&gt;--%>
<%--<form action="/user/registerSubmit" method="get">--%>
<%--    <input type="hidden" name="id" value="${formBean.id}">--%>
<%--    Email <input type="email" name="email" id="emailId" value="${formBean.email}">--%>
<%--    <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">--%>
<%--        <div style="color: red;">--%>
<%--                ${error.getDefaultMessage()}--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    <br>--%>
<%--    First Name <input type="text" name="firstName" id="firstNameId" value="${formBean.firstName}">--%>
<%--    <c:forEach items="${bindingResult.getFieldErrors('firstName')}" var="error">--%>
<%--        <div style="color: red;">--%>
<%--                ${error.getDefaultMessage()}--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    <br>--%>
<%--    Last Name <input type="text" name="lastName" id="lastNameId" value="${formBean.lastName}">--%>
<%--    <c:forEach items="${bindingResult.getFieldErrors('lastName')}" var="error">--%>
<%--        <div style="color: red;">--%>
<%--                ${error.getDefaultMessage()}--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    <br>--%>
<%--    Password <input type="password" name="password" id="passwordId" >--%>
<%--    <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">--%>
<%--        <div style="color: red;">--%>
<%--                ${error.getDefaultMessage()}--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    <br>--%>
<%--    Confirm Password <input type="password" name="confirmPassword" id="confirmPasswordId" >--%>
<%--    <c:forEach items="${bindingResult.getFieldErrors('confirmPassword')}" var="error">--%>
<%--        <div style="color: red;">--%>
<%--                ${error.getDefaultMessage()}--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    <br>--%>
<%--    Check box <input type="checkbox" name="checkbox">--%>
<%--    <c:forEach items="${bindingResult.getFieldErrors('checkbox')}" var="error">--%>
<%--        <div style="color: red;">--%>
<%--                ${error.getDefaultMessage()}--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    <br>--%>
<%--    <button type="submit">Submit</button>--%>

<%--</form>--%>



<jsp:include page="../include/footer.jsp" />