<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<style>
    .table{
        width: 90%;
        margin: 50px 100px;
        font-size: 20px;
    }
    .table-head {
        background-color: lightblue;
        filter: brightness(90%);
    }
    .table tr:hover {
        background: lightblue;
    }
    .table tr td a {
        color: #003d5c;
        opacity: 0.8;
        text-decoration: none;
    }
    .search{
        display: flex;
        justify-content: center;
        align-items: center;
        align-content: center;
        /*margin: 0 50px;*/
        width: 80vw;
    }
    .search input {
        margin-left: 300px;
    }
    .btn-primary{
        opacity: 0.9;
    }
</style>

<h3>Search page</h3>

<form action="/user/search" method="get" class="search col-md-8">
    <input name="searchInput" type="text" class="form-control" placeholder="${searchValue}">
    <button class="btn btn-primary" type="submit">Search</button>
</form>

<c:if test="${not empty searchValue}">
    <h4 class="h4-index" style="margin-top: 40px">Search Result: ${usersModel.size()}</h4>
</c:if>

<table class="table">
    <tr class="table-head">
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Action</th>
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