<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<%--<c:choose>--%>
<%--    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>--%>
<%--    <c:when test="${not empty formBean.id}"><h1>Edit</h1></c:when>--%>
<%--    &lt;%&ndash;    <c:otherwise>undefined</c:otherwise>&ndash;%&gt;--%>
<%--</c:choose>--%>


<form action="/feeder/showFeeders" method="get" class="search-box">
    <input name="search" type="search" placeholder="${searchTerm}" />
    <button type="submit" class="search-btn"><i class="fa-solid fa-magnifying-glass"></i></button>
</form>

<div id="feeders-container" class="cards-container">
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
            <a href="/feeder/edit/${feeder.id}" id="feeder-edit-btn">Edit</a>
        </div>
    </c:forEach>
</div>

<%--<form id="testId" action="/feeder/edit" method="get" style="display: none;">--%>
<%--    <input type="hidden" name="id" value="${FeederformBean.id}"><br>--%>
<%--    <br>--%>
<%--    Name <input type="text" name="name" id="nameId" value="${FeederformBean.name}">--%>
<%--    <br>--%>
<%--    Size <input type="text" name="size" id="sizeId" value="${FeederformBean.size}">--%>
<%--    <br>--%>
<%--    Status <select name="status" id="statusId">--%>
<%--    <c:choose>--%>
<%--        <c:when test="${FeederformBean.status == 'F/T'}">--%>
<%--            <option value="F/T">F/T</option>--%>
<%--            <option value="live">Live</option>--%>
<%--        </c:when>--%>
<%--        <c:otherwise>--%>
<%--            <option value="live">Live</option>--%>
<%--            <option value="F/T">F/T</option>--%>
<%--        </c:otherwise>--%>
<%--    </c:choose>--%>
<%--</select>--%>
<%--    <br>--%>
<%--    Quantity <input type="number" name="quantity" id="quantityId" value="${FeederformBean.quantity}">--%>
<%--    <br>--%>
<%--    Image URL <input type="text" name="imgUrl" value="${FeederformBean.imgUrl}">--%>
<%--    <br>--%>
<%--    <button type="submit">Submit</button>--%>
<%--</form>--%>

<script>
    $('#feeder-edit-btn').click(function(){
        $('#feeders-container').hide();
        $('#feeder-form').show();
    })
</script>

<jsp:include page="../include/footer.jsp" />