<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<h1>Search page</h1>

<form action="/user/search" method="get" class="search col-md-8">
    <input name="searchInput" type="text" class="form-control" placeholder="${searchValue}">
    <button class="btn btn-primary" type="submit">Search</button>
</form>

<c:if test="${not empty searchValue}">
    <h4>Search Result: ${usersModel.size()}</h4>
</c:if>

<table class="table">
    <tr>
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Edit</th>
    </tr>
    <c:forEach items="${usersModel}" var="user">
        <tr>
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td><a href="/user/edit/${user.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>


<jsp:include page="../include/footer.jsp" />