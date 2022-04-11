<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<c:choose>
    <%--    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>--%>
    <c:when test="${not empty feederFormBean.id}"><h1>Edit Feeder Information</h1></c:when>
    <c:otherwise><h1>Add Feeder</h1></c:otherwise>
</c:choose>

<%--method = get to show query after submit, method  is = post before --%>
<form id="feeder-form" action="/feeder/added" method="get">
    <input type="hidden" name="id" value="${feederFormBean.id}"><br>
    <br>
    Name <input type="text" name="name" id="nameId" value="${feederFormBean.name}">
    <br>
    Size <input type="text" name="size" id="sizeId" value="${feederFormBean.size}">
    <br>
    Status <select name="status" id="statusId">
    <c:choose>
        <c:when test="${feederFormBean.status == 'F/T'}">
            <option value="F/T">F/T</option>
            <option value="Live">Live</option>
        </c:when>
        <c:otherwise>
            <option value="Live">Live</option>
            <option value="F/T">F/T</option>
        </c:otherwise>
    </c:choose>
</select>
    <br>
    Quantity <input type="number" name="quantity" id="quantityId" value="${feederFormBean.quantity}">
    <br>
    Image URL <input type="text" name="imgUrl" value="${feederFormBean.imgUrl}">
    <br>
    <button type="submit">Submit</button>
</form>



<jsp:include page="../include/footer.jsp" />