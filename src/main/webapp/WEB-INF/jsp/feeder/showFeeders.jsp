<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<%--<c:choose>--%>
<%--    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>--%>
<%--    <c:when test="${not empty formBean.id}"><h1>Edit</h1></c:when>--%>
<%--    &lt;%&ndash;    <c:otherwise>undefined</c:otherwise>&ndash;%&gt;--%>
<%--</c:choose>--%>


<%--<form action="/snake/showSnakes" method="get" class="search-box">--%>
<%--    <input name="search" id="searchInput" type="search" placeholder="${searchTerm}" />--%>
<%--    <button type="submit" class="search-btn"><i class="fa-solid fa-magnifying-glass"></i></button>--%>
<%--</form>--%>

<div class="cards-container">

    <div class="card-wrapper">
        <div class="card-image">
            <img class="feeder-img" src="https://illuminatisymbols.info/wp-content/uploads/illuminati-symbols-Starbucks-Coffee-Logo.gif" alt="Avatar">
        </div>
        <div class="card-content">
            <h3 class="title">Coffe Pack</h3>
        </div>
    </div>


</div>


<script>
    $('.EditBtn').click(function(){
        $('.divCard').hide();
        $('#formId').show();
    })
</script>

<jsp:include page="../include/footer.jsp" />