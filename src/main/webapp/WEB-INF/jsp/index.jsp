<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="include/header.jsp" />

<h1>This is an index page</h1>

<sec:authorize access="!isAuthenticated()">
    <h1>This is an unauthenticated page</h1>
</sec:authorize>


<sec:authorize access="isAuthenticated()">
    <h1>This is an authenticated page</h1>
</sec:authorize>

<jsp:include page="include/footer.jsp" />