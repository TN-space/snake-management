<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />


<div class="add-form-container">
    <c:choose>
        <%--    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>--%>
        <c:when test="${not empty snakeFormBean.id}"><h3 class="h3-add">Change Snake Information</h3></c:when>
        <c:otherwise><h3 class="h3-add">Add Snake</h3></c:otherwise>
    </c:choose>
    <%--method = get to show query after submit, method  is = post before --%>
    <form id="snake-form" action="/snake/added" method="get">
        <input type="hidden" name="id" value="${snakeFormBean.id}">
        <div class="group">
            <input class="add-input" type="text" name="species" id="speciesId" value="${snakeFormBean.species}">
            <span class="highlight"></span>
            <span class="bar add-bar"></span>
            <label class="add-label">Species</label>
            <c:forEach items="${bindingResult.getFieldErrors('species')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>
        <div class="group">
            <label class="add-label">Sex</label>
            <select class="add-selection" name="sex" id="sexId">
                <c:choose>
                    <c:when test="${snakeFormBean.sex == 'female'}">
                        <option value="female">Female</option>
                        <option value="male">Male</option>
                    </c:when>
                    <c:otherwise>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </c:otherwise>
                </c:choose>
            <span class="highlight"></span>
            <span class="bar add-bar"></span>
<%--            <label class="add-label">Sex</label>--%>
            <c:forEach items="${bindingResult.getFieldErrors('sex')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
            </select>
        </div>
        <div class="group">
            <input class="add-input" type="date" name="birthDate" id="birthDateId" value="${snakeFormBean.birthDate}">
            <span class="highlight"></span>
            <span class="bar add-bar"></span>
            <label class="add-label">Birth Date</label>
            <c:forEach items="${bindingResult.getFieldErrors('birthDate')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>
        <div class="group">
            <input class="add-input" type="text" name="imgUrl" value="${snakeFormBean.imgUrl}">
            <span class="highlight"></span>
            <span class="bar add-bar"></span>
            <label class="add-label">Image URL</label>
            <c:forEach items="${bindingResult.getFieldErrors('imgUrl')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>
        <div class="group">
            <input class="add-input" type="text" name="note" id="noteId" value="${snakeFormBean.note}">
            <span class="highlight"></span>
            <span class="bar add-bar"></span>
            <label class="add-label">Note</label>
            <c:forEach items="${bindingResult.getFieldErrors('note')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>
        <button class="submit-btn" type="submit">Submit</button>
    </form>
</div>


<jsp:include page="../include/footer.jsp" />