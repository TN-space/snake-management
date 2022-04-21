<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />


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
            <label class="form-label">First Name</label>
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
            <label class="form-label">Last Name</label>
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
            <label class="form-label">Email</label>
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
            <label class="form-label">Password</label>
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
            <label class="form-label">Password Confirm</label>
            <c:forEach items="${bindingResult.getFieldErrors('confirmPassword')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>
        <button class="submit-btn" type="submit">Submit</button>
    </form>
<%--    <input type="radio" id="html" name="fav_language" value="HTML">--%>

</div>


<jsp:include page="../include/footer.jsp" />