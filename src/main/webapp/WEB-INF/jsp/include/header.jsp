<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../../../pub/css/snake/showSnakes.css">
    <link rel="stylesheet" href="../../../pub/css/feeder/showFeeders.css">
    <script src="https://kit.fontawesome.com/2c911235fd.js" crossorigin="anonymous"></script>
    <title>Snake Management</title>
</head>

<body>

<div class="container">
    <sec:authorize access="!isAuthenticated()">
        <a href="/index">Index</a> &nbsp;
        <a href="/login/login">Login</a>&nbsp;
        <a href="/user/register">Sign up</a> &nbsp;
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <a href="/snake/add">Add Snake</a> &nbsp;
        <a href="/snake/showSnakes">Show Snakes</a> &nbsp;
        <a href="/feeder/add">Add Feeder</a> &nbsp;
        <a href="/feeder/showFeeders">Show Feeders</a> &nbsp;
        <a href="/feederSnake/add">Add Feeding</a> &nbsp;
        <a href="/login/logout">Logout</a>&nbsp;
    authenticated as <sec:authentication property="principal.username" />
    </sec:authorize>

<%--    <a href="/user/search">Search</a>--%>
    <hr>



