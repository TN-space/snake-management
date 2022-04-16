<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<%--<c:choose>--%>
<%--    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>--%>
<%--    <c:when test="${not empty formBean.id}"><h1>Edit</h1></c:when>--%>
<%--    &lt;%&ndash;    <c:otherwise>undefined</c:otherwise>&ndash;%&gt;--%>
<%--</c:choose>--%>


<form action="/snake/showSnakes" method="get" class="search-box">
    <input name="search" type="search" placeholder="${searchTerm}" />
    <button type="submit" class="search-btn"><i class="fa-solid fa-magnifying-glass"></i></button>
</form>

<%--method = get to show query after submit, method  is = post before --%>
<div id="snakes-container" class="snake-container">
    <h1></h1>

    <div class="grid" >
        <c:forEach items="${snakesModel}" var="snake">
            <div class="card">
                <input id="${snake.id}" type="checkbox">
                <label class="tgl-btn" for="${snake.id}"><span></span></label>
                <div class="tgl-view">
                    <div class="card-image">
                        <c:choose>
                            <c:when test="${not empty snake.imgUrl}">
                                <img src="${snake.imgUrl}" alt="${snake.species}">
                            </c:when>
                            <c:otherwise>
                                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRONkkkM0RF84PFv3zxWOX5N1dbXMSRe5YWpg&usqp=CAU" alt="snake">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <h2 class="card-title">${snake.species}</h2>
                    <div class="card-detail">
<%--                        <c:forEach items="${ages}" var="age">--%>
<%--                            <b>Age: </b>${age}<br>--%>
<%--                        </c:forEach>--%>
                        <b>Sex: </b>${snake.sex}<br>
                        <b>Birthday: </b>${snake.birthDate}<br>
                        <b>Note: </b>${snake.note}<br>
                        <div class="card-control">
                            <a href="/snake/edit/${snake.id}" id="snake-edit-btn">Edit</a>
                            <a href="/snake/remove/${snake.id}" id="snake-remove-btn">Remove</a>
                        </div>
                    </div>

                </div>
<%--                <form action="/snake/remove/${snake.id}">--%>
<%--                    <button id="snake-remove-btn">Remove</button>--%>
<%--                </form>--%>
            </div>
        </c:forEach>

<%--    forEach use hashmap--%>
<%--    <c:forEach items="${mapModel}" var="snake">--%>
<%--        <div class="card showDiv">--%>
<%--            <input id="${snake.key}" type="checkbox">--%>
<%--            <label class="tgl-btn" for="${snake.key}"><span></span></label>--%>
<%--            <div class="tgl-view">--%>
<%--                <div class="card-image">--%>
<%--                    <img src="${snake.value.imgUrl}" alt="${snake.value.species}">--%>
<%--                </div>--%>
<%--                <h2 class="card-title">${snake.value.species}</h2>--%>
<%--                <p class="card-detail">--%>
<%--                    <c:forEach items="${ages}" var="age">--%>
<%--                        <b>Age: </b>${age}<br>--%>
<%--                    </c:forEach>--%>
<%--                    <b>Sex: </b>${snake.value.sex}<br>--%>
<%--                    <b>Note: </b>${snake.value.note}<br>--%>
<%--                </p>--%>
<%--            </div>--%>
<%--            <button class="EditBtn" >Edit</button>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>

    </div>
</div>

<script>
    $('#snake-edit-btn').click(function(){
        $('#snakes-container').hide();
        $('#snake-form').show();
    })
</script>

<jsp:include page="../include/footer.jsp" />