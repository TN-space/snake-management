<jsp:include page="../include/header.jsp" />

<%--method has to be a post--%>
<h1>Sign in</h1>
<form action="/login/loginSubmit" method="post">
    Username: <input type="text" name="username">
    <br>
    Password: <input type="text" name="password">
    <br>
    <button type="submit">Submit</button>
</form>

<jsp:include page="../include/footer.jsp" />