<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<c:choose>
<%--    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>--%>
    <c:when test="${not empty snakeFormBean.id}"><h1>Change Snake Information</h1></c:when>
        <c:otherwise><h1>Add Snake</h1></c:otherwise>
</c:choose>

<%--method = get to show query after submit, method  is = post before --%>
<form id="snake-form" action="/snake/added" method="get">
    <input type="hidden" name="id" value="${snakeFormBean.id}"><br>
    Species <input type="text" name="species" id="speciesId" value="${snakeFormBean.species}">
<%--    <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">--%>
<%--        <div style="color: red;">--%>
<%--                ${error.getDefaultMessage()}--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
    <br>
    Sex <select name="sex" id="sexId">
    <c:choose>
        <c:when test="${snakeFormBean.sex == 'female'}">
            <option value="female">Female</option>
            <option value="male">Male</option>
        </c:when>
        <c:otherwise>
            <option value="male">Male</option>
            <option value="female">Female</option>
        </c:otherwise>
    </c:choose>
    </select>
    <br>
    Birthdate <input type="date" name="birthDate" id="birthDateId" value="${snakeFormBean.birthDate}">
    <br>
    Image URL <input type="text" name="imgUrl" value="${snakeFormBean.imgUrl}">
    <br>
    Note <input type="text" name="note" id="noteId" value="${snakeFormBean.note}">
<%--    <c:forEach items="${bindingResult.getFieldErrors('firstName')}" var="error">--%>
<%--        <div style="color: red;">--%>
<%--                ${error.getDefaultMessage()}--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
    <br>
    <%--    Check box <input type="checkbox" name="checkbox">--%>
    <%--    <c:forEach items="${bindingResult.getFieldErrors('checkbox')}" var="error">--%>
    <%--        <div style="color: red;">--%>
    <%--                ${error.getDefaultMessage()}--%>
    <%--        </div>--%>
    <%--    </c:forEach>--%>
    <br>
    <button type="submit">Submit</button>

</form>



<jsp:include page="../include/footer.jsp" />