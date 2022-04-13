<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />


<c:choose>
    <%--    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>--%>
    <c:when test="${not empty feederSnakeFormBean.id}"><h1>Edit Feeding</h1></c:when>
    <c:otherwise><h1>Feeding</h1></c:otherwise>
</c:choose>

<%--method = get to show query after submit, method  is = post before --%>
<form id="feeder-snake-form" action="/feederSnake/added" method="get">
    <input type="hidden" name="id" value="${feederSnakeFormBean.id}"><br>
    <br>
    Snake species <input type="text" name="species" value="${feederSnakeFormBean.species}">
    <br>
    Feeder name <input type="text" name="name"  value="${feederSnakeFormBean.name}">
    <br>
<%--    Status <select name="status" id="statusId">--%>
<%--    <c:choose>--%>
<%--        <c:when test="${feederSnakeFormBean.status == 'F/T'}">--%>
<%--            <option value="F/T">F/T</option>--%>
<%--            <option value="Live">Live</option>--%>
<%--        </c:when>--%>
<%--        <c:otherwise>--%>
<%--            <option value="Live">Live</option>--%>
<%--            <option value="F/T">F/T</option>--%>
<%--        </c:otherwise>--%>
<%--    </c:choose>--%>
<%--</select>--%>
<%--    <br>--%>
<%--    Quantity <input type="number" name="quantity" id="quantityId" value="${feederSnakeFormBean.quantity}">--%>
<%--    <br>--%>
<%--    Image URL <input type="text" name="imgUrl" value="${feederSnakeFormBean.imgUrl}">--%>
<%--    <br>--%>
    <button type="submit">Submit</button>
</form>

<jsp:include page="../include/footer.jsp" />