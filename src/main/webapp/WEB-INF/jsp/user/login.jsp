<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="../include/header.jsp" />

<%--this method has to be a post--%>
<div class="form-container">
    <h3>Login</h3>
    <form action="/login/loginSubmit" method="post">
        <div class="group">
            <input class="login-inputs" type="text" name="username">
            <span class="highlight"></span>
            <span class="bar"></span>
            <label class="login-labels">Email</label>
            <c:forEach items="${bindingResult.getFieldErrors('username')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>
        <div class="group">
            <input class="login-inputs" type="password" name="password">
            <span class="highlight"></span>
            <span class="bar"></span>
            <label class="login-labels">Password</label>
            <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>
        <button class="submit-btn" type="submit">Login</button>
    </form>
</div>

<jsp:include page="../include/footer.jsp" />