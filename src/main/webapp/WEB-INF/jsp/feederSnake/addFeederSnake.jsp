<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />


<div class="add-form-container">

<h3 class="h3-add">Add Feeding</h3>

<%--method = get to show query after submit, method  is = post before--%>
<form id="feeder-snake-form" action="/feederSnake/added" method="get">
    <input type="hidden" name="id" value="${feedingFormBean.id}">
    <div class="group">
        <label class="form-label add-label long-margin">Snake</label>
        <select class="add-selection-long" name="snakeId" >
            <option></option>
            <c:forEach items="${snakesModelKey}" var="snake">
                <option value="${snake.id}">${snake.species} | ${snake.sex}</option>
            </c:forEach>
        </select>
        <c:forEach items="${bindingResult.getFieldErrors('snakeId')}" var="error">
            <div style="color: red; margin-left: 130px;">
                    ${error.getDefaultMessage()}
            </div>
        </c:forEach>
    </div>
    <div class="group">
        <label class="form-label add-label long-margin">Feeder</label>
        <select class="add-selection-long" name="feederId" >
            <option></option>
            <c:forEach items="${feedersModelKey}" var="feeder">
<%--                check if feeder.quantity is greater than 0, aka only show feeders that have quantity greater than 0--%>
                <c:if test = "${feeder.quantity > 0}">
                    <option value="${feeder.id}">${feeder.name} | <b>Size:</b> ${feeder.size} | <b>Availability:</b> ${feeder.quantity}</option>
                </c:if>
            </c:forEach>
        </select>
        <c:forEach items="${bindingResult.getFieldErrors('feederId')}" var="error">
            <div style="color: red; margin-left: 130px;">
                    ${error.getDefaultMessage()}
            </div>
        </c:forEach>
    </div>
    <div class="group">
        <input class="add-input-long" type="number" name="quantity"  value="${feederSnakeFormBean.quantity}" placeholder="default feeding quantity is 1..">
        <span class="highlight"></span>
        <span class="bar bar-long"></span>
        <label class="form-label add-label long-margin">Quantity</label>
<%--        <c:forEach items="${bindingResult.getFieldErrors('quantity')}" var="error">--%>
<%--            <div style="color: red;">--%>
<%--                    ${error.getDefaultMessage()}--%>
<%--            </div>--%>
<%--        </c:forEach>--%>
    </div>
    <button class="submit-btn" type="submit">Submit</button>
</form>
</div>

<jsp:include page="../include/footer.jsp" />