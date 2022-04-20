<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />


<%--<c:choose>--%>
<%--    &lt;%&ndash;    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>&ndash;%&gt;--%>
<%--    <c:when test="${not empty feederSnakeFormBean.id}"><h1>Edit Feeding</h1></c:when>--%>
<%--    <c:otherwise><h1>Feeding</h1></c:otherwise>--%>
<%--</c:choose>--%>
<div class="add-form-container">
<%--    <c:choose>--%>
<%--        &lt;%&ndash;    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>&ndash;%&gt;--%>
<%--        <c:when test="${not empty snakeFormBean.id}"><h3 class="h3-add">Change Snake Information</h3></c:when>--%>
<%--        <c:otherwise><h3 class="h3-add">Add Snake</h3></c:otherwise>--%>
<%--    </c:choose>--%>

<h3 class="h3-add">Add Feeding</h3>

<%--method = get to show query after submit, method  is = post before--%>
<form id="feeder-snake-form" action="/feederSnake/added" method="get">
    <input type="hidden" name="id" value="${formBean.id}">
    <div class="group">
        <label class="form-label add-label long-margin">Snake</label>
        <select class="add-selection-long" name="snakeId" >
            <option></option>
            <c:forEach items="${snakesModelKey}" var="snake">
                <option value="${snake.id}">${snake.species} | ${snake.sex}</option>
            </c:forEach>
        </select>
    </div>
    <div class="group">
        <label class="form-label add-label long-margin">Feeder</label>
        <select class="add-selection-long" name="feederId" >
            <option></option>
            <c:forEach items="${feedersModelKey}" var="feeder">
                <c:if test = "${feeder.quantity > 0}">
                    <option value="${feeder.id}">${feeder.name} | <b>Size:</b> ${feeder.size} | <b>Availability:</b> ${feeder.quantity}</option>
                </c:if>
            </c:forEach>
        </select>
    </div>
    <div class="group">
        <input class="add-input-long" type="number" name="quantity"  value="${feederSnakeFormBean.quantity}">
        <span class="highlight"></span>
        <span class="bar bar-long"></span>
        <label class="form-label add-label long-margin">Feeding Quantity</label>
        <c:forEach items="${bindingResult.getFieldErrors('quantity')}" var="error">
            <div style="color: red;">
                    ${error.getDefaultMessage()}
            </div>
        </c:forEach>
    </div>
    <button class="submit-btn" type="submit">Submit</button>
</form>
</div>

<jsp:include page="../include/footer.jsp" />