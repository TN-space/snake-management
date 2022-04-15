<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />


<%--<c:choose>--%>
<%--    &lt;%&ndash;    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>&ndash;%&gt;--%>
<%--    <c:when test="${not empty feederSnakeFormBean.id}"><h1>Edit Feeding</h1></c:when>--%>
<%--    <c:otherwise><h1>Feeding</h1></c:otherwise>--%>
<%--</c:choose>--%>
<h1>Feeding</h1>

<%--method = get to show query after submit, method  is = post before --%>
<form id="feeder-snake-form" action="/feederSnake/added" method="get">
    <input type="hidden" name="id" value="${feederSnakeFormBean.id}"><br>
    <br>
    Snake species <input type="text" name="species" value="${feederSnakeFormBean.species}">
    <br>
    Feeder name <input type="text" name="name"  value="${feederSnakeFormBean.name}">
    <br>
    Quantity fed <input type="number" name="quantity"  value="${feederSnakeFormBean.quantity}">
    <br>

<%--    Snake --%>
<%--    <select name="species" >--%>
<%--    <option value="">hello</option>--%>
<%--    <option value="">hi</option>--%>
<%--    <c:forEach items="${snakesModel}" var="snake">--%>
<%--        <option value="${snake.species}">${snake.species}</option>--%>
<%--    </c:forEach>--%>


    </select>
    <br>
    <button type="submit">Submit</button>
</form>

<jsp:include page="../include/footer.jsp" />