<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<form action="/snake/showSnakes" method="get" class="search-box">
    <input name="search" type="search" placeholder="${searchTerm}" />
    <button type="submit" class="search-btn"><i class="fa-solid fa-magnifying-glass"></i></button>
</form>

<%--method = get to show query after submit, method  is = post before --%>
<div id="snakes-container-id" class="snake-container">
    <div class="grid" >
        <c:forEach items="${snakesModel}" var="snake">
            <div class="card">
                <input id="${snake.id}" type="checkbox">
                <label class="tgl-btn" for="${snake.id}"><span></span></label>
                <div class="tgl-view">
                    <div class="card-image">
<%--                        if there is an img url passed in--%>
                        <c:choose>
<%--                            then do this --%>
                            <c:when test="${not empty snake.imgUrl}">
                                <img src="${snake.imgUrl}" alt="${snake.species}">
                            </c:when>
<%--                            otherwise set use default img--%>
                            <c:otherwise>
                                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRONkkkM0RF84PFv3zxWOX5N1dbXMSRe5YWpg&usqp=CAU" alt="snake">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <h2 class="card-title">${snake.species}</h2>
                    <div class="card-detail">
                        <p><b>Sex: </b>${snake.sex}</p>
                        <p><b>Birthday: </b>${snake.birthDate}</p>
                        <p><b>Note: </b>${snake.note}</p>
                        <div class="card-control">
                            <a href="/snake/edit/${snake.id}" id="snake-edit-btn">Edit</a>
                            <a href="/snake/remove/${snake.id}" id="snake-remove-btn">Remove</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<jsp:include page="../include/footer.jsp" />