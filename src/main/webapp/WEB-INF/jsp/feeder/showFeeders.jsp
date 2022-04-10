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

    <c:forEach items="${feedersModel}" var="feeder">
        <div class="card-wrapper">
            <div class="card-image">
                <c:choose>
                    <c:when test="${not empty feeder.imgUrl}">
                        <img class="feeder-img" src="${feeder.imgUrl}" alt="feeder">
                    </c:when>
                    <c:otherwise>
                        <img class="feeder-img" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6nybAOUm_pgyjHCBXZQ9ghbRT8R1L6aflGw&usqp=CAU" alt="feeder">
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="card-content">
                <h3 class="title">${feeder.name}</h3>
                <p><b>Size:</b> ${feeder.size}</p>
                <p><b>Status:</b> ${feeder.status}</p>
                <p><b>Quantity:</b> ${feeder.quantity}</p>
            </div>
        </div>
    </c:forEach>

</div>


<script>
    $('.EditBtn').click(function(){
        $('.divCard').hide();
        $('#formId').show();
    })
</script>

<jsp:include page="../include/footer.jsp" />