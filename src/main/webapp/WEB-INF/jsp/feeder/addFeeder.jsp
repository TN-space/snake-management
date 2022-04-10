<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<%--<c:choose>--%>
<%--    &lt;%&ndash;    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>&ndash;%&gt;--%>
<%--    <c:when test="${not empty formBean.id}"><h1>Change information</h1></c:when>--%>
<%--    <c:otherwise><h1>Add snake</h1></c:otherwise>--%>
<%--</c:choose>--%>

<%--method = get to show query after submit, method  is = post before --%>
<form action="/feeder/added" method="get">
    <input type="hidden" name="id" value="${formBean.id}"><br>
    <br>
    Name <input type="text" name="name" id="nameId" value="${formBean.name}">
    <br>
    Size <input type="text" name="size" id="sizeId" value="${formBean.size}">
    <br>
    Status <select name="status" id="statusId">
    <c:choose>
        <c:when test="${formBean.sex == 'F/T'}">
            <option value="F/T">F/T</option>
            <option value="live">Live</option>
        </c:when>
        <c:otherwise>
            <option value="live">Live</option>
            <option value="F/T">F/T</option>
        </c:otherwise>
    </c:choose>
</select>
    <br>
    Quantity <input type="number" name="quantity" id="quantityId" value="${formBean.quantity}">
    <br>
    Image URL <input type="text" name="imgUrl" value="${formBean.imgUrl}">
    <br>
    <button type="submit">Submit</button>
</form>



<jsp:include page="../include/footer.jsp" />