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
<h3>Feeding History</h3>
<div id="wrapper">

<table id="keywords" cellspacing="0" cellpadding="0">
    <thead>
    <tr>
        <th><span>Feeding Date</span></th>
        <th><span>Species</span></th>
        <th class="th-column-narrow"><span>Feeder</span></th>
        <th class="th-column-narrow"><span>Quantity Fed</span></th>
<%--        <th><span>Action</span></th>--%>
    </tr>
    </thead>
<%--    <tr>--%>
<%--        <th>Feeding Date</th>--%>
<%--        <th>Species</th>--%>
<%--        <th>Feeder</th>--%>
<%--        <th>Quantity</th>--%>
<%--&lt;%&ndash;        <th>Action</th>&ndash;%&gt;--%>
<%--    </tr>--%>
    <tbody>
    <c:forEach items="${feedingsModel}" var="feeding">
    <tr class="tr-on-hover">
        <td class="lalign">${feeding.feeding_date}</td>
        <td style="text-align: left">${feeding.species}</td>
        <td style="text-align: left">${feeding.name}</td>
        <td>${feeding.quantity}</td>
<%--        <td><a href="/feederSnake/remove/${feeding.id}">Remove</a></td>--%>
<%--        <td><a href="/feederSnake/edit/${feeding.id}">Edit</a></td>--%>
    </tr>
    </c:forEach>
    </tbody>
<%--    <c:forEach items="${feedingsModel}" var="feeding">--%>
<%--        <tr>--%>
<%--&lt;%&ndash;            accessing data in List<Map<String,Object>> using the name of the columns&ndash;%&gt;--%>
<%--            <td>${feeding.feeding_date}</td>--%>
<%--            <td>${feeding.species}</td>--%>
<%--            <td>${feeding.name}</td>--%>
<%--            <td>${feeding.quantity}</td>--%>
<%--&lt;%&ndash;            <td><a href="/feederSnake/remove/${feeding.id}">Remove</a></td>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <td><a href="/feederSnake/edit/${feeding.id}">Edit</a></td>&ndash;%&gt;--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
</table>

<%--    </div>--%>
</div>

<%--<script>--%>
<%--    $('#snake-edit-btn').click(function(){--%>
<%--        $('#snakes-container').hide();--%>
<%--        $('#snake-form').show();--%>
<%--    })--%>
<%--</script>--%>
<script>
    // for table in showFeedings
    $(function(){
        $('#keywords').tablesorter();
    })
</script>

<jsp:include page="../include/footer.jsp" />