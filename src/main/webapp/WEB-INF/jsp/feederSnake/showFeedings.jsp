<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<%--<c:choose>--%>
<%--    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>--%>
<%--    <c:when test="${not empty formBean.id}"><h1>Edit</h1></c:when>--%>
<%--    &lt;%&ndash;    <c:otherwise>undefined</c:otherwise>&ndash;%&gt;--%>
<%--</c:choose>--%>


<%--<form action="/snake/showSnakes" method="get" class="search-box">--%>
<%--    <input name="search" type="search" placeholder="${searchTerm}" />--%>
<%--    <button type="submit" class="search-btn"><i class="fa-solid fa-magnifying-glass"></i></button>--%>
<%--</form>--%>

<table class="table">
    <tr>
        <th>Species</th>
        <th>Feeder</th>
        <th>Quantity</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${feederSnakes}" var="feeding">
        <tr>
            <td>${feeding.species}</td>
            <td>${feeding.name}</td>
            <td>${feeding.quantity}</td>
            <td><a href="/feederSnake/edit/${feeding.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>

    </div>
</div>

<%--<script>--%>
<%--    $('#snake-edit-btn').click(function(){--%>
<%--        $('#snakes-container').hide();--%>
<%--        $('#snake-form').show();--%>
<%--    })--%>
<%--</script>--%>

<jsp:include page="../include/footer.jsp" />