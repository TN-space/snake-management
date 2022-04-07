<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<%--<c:choose>--%>
<%--    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>--%>
<%--    <c:when test="${not empty formBean.id}"><h1>Edit</h1></c:when>--%>
<%--    &lt;%&ndash;    <c:otherwise>undefined</c:otherwise>&ndash;%&gt;--%>
<%--</c:choose>--%>

<%--method = get to show query after submit, method  is = post before --%>
<div class="container">

    <h1></h1>

    <div class="grid">
        <c:forEach items="${snakeModels}" var="snake">
            <div class="card">
                <input id="${snake.id}" type="checkbox">
                <label class="tgl-btn" for="${snake.id}"><span></span></label>
                <div class="tgl-view">
                    <div class="card-image">
                        <img src="${snake.imgUrl}" alt="${snake.species}">
                    </div>
                    <h2 class="card-title">${snake.species}</h2>
                    <p class="card-detail">
                        <b>Age: </b>${snake.age}<br>
                        <b>Sex: </b>${snake.sex}<br>
                        <b>Note: </b>${snake.note}<br>
                    </p>
                </div>
            </div>
        </c:forEach>

<%--        <div class="card">--%>
<%--            <input id="card2" type="checkbox">--%>
<%--            <label class="tgl-btn" for="card2"><span></span></label>--%>
<%--            <div class="tgl-view">--%>
<%--                <div class="card-image">--%>
<%--                    <img src="https://www.placecage.com/g/600/600" alt="cage">--%>
<%--                </div>--%>
<%--                <h2 class="card-title">PlaceCage</h2>--%>
<%--                <p class="card-detail">I think you've got this figured out by now.<br><br>Calm: https://www.placecage.com/200/300<br>Gray: https://www.placecage.com/g/200/300<br>CRAZY: https://www.placecage.com/c/200/300<br>GIF: https://www.placecage.com/gif/200/300</p>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <div class="card">--%>
<%--            <input id="card3" type="checkbox">--%>
<%--            <label class="tgl-btn" for="card3"><span></span></label>--%>
<%--            <div class="tgl-view">--%>
<%--                <div class="card-image">--%>
<%--                    <img src="https://www.stevensegallery.com/g/600/600" alt="seagal">--%>
<%--                </div>--%>
<%--                <h2 class="card-title">Steven SeGALLERY</h2>--%>
<%--                <p class="card-detail">The internet was missing the ability to provide custom-sized placeholder images of Steven Segal. Now it can.<br><br>Calm: https://stevensegallery.com/200/300<br>Gray: https://stevensegallery.com/g/200/300</p>--%>
<%--            </div>--%>
<%--        </div>--%>

    </div>
</div>



<jsp:include page="../include/footer.jsp" />