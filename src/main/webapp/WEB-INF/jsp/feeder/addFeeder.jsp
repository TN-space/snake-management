<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<div class="add-form-container">

    <c:choose>
        <%--    <c:when test="${empty formBean.id}"><h1>Sign up</h1></c:when>--%>
        <c:when test="${not empty feederFormBean.id}"><h3  class="h3-add">Edit Feeder Information</h3></c:when>
        <c:otherwise><h3 class="h3-add">Add Feeder</h3></c:otherwise>
    </c:choose>

    <%--method = get to show query after submit, method  is = post before --%>
    <form id="feeder-form" action="/feeder/added" method="get">
        <input type="hidden" name="id" value="${feederFormBean.id}"><br>
        <div class="group">
            <input class="add-input" type="text" name="name" id="nameId" value="${feederFormBean.name}">
            <span class="highlight"></span>
            <span class="bar add-bar"></span>
            <label class="form-label add-label">Name</label>
            <c:forEach items="${bindingResult.getFieldErrors('name')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>
        <div class="group">
            <input class="add-input" type="text" name="size" id="sizeId" value="${feederFormBean.size}">
            <span class="highlight"></span>
            <span class="bar add-bar"></span>
            <label class="form-label add-label">Size</label>
            <c:forEach items="${bindingResult.getFieldErrors('size')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>
        <div class="group">
            <label class="form-label add-label">Status</label>
            <select class="add-selection" name="status" id="statusId">
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
                <span class="highlight"></span>
                <span class="bar add-bar"></span>
                <c:forEach items="${bindingResult.getFieldErrors('status')}" var="error">
                    <div style="color: red;">
                            ${error.getDefaultMessage()}
                    </div>
                </c:forEach>
            </select>
        </div>
        <div class="group">
            <input class="add-input" type="number" name="quantity" id="quantityId" value="${feederFormBean.quantity}">
            <span class="highlight"></span>
            <span class="bar add-bar"></span>
            <label class="form-label add-label">Quantity</label>
            <c:forEach items="${bindingResult.getFieldErrors('quantity')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>
        <div class="group">
            <input class="add-input" type="text" name="imgUrl" id="imgUrlId" value="${feederFormBean.imgUrl}">
            <span class="highlight"></span>
            <span class="bar add-bar"></span>
            <label class="form-label add-label">Image URL</label>
            <c:forEach items="${bindingResult.getFieldErrors('imgUrl')}" var="error">
                <div style="color: red;">
                        ${error.getDefaultMessage()}
                </div>
            </c:forEach>
        </div>
        <button class="submit-btn" type="submit">Submit</button>
    </form>
</div>

<jsp:include page="../include/footer.jsp" />