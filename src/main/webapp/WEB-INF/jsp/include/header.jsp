<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../../../pub/css/header/header.css">
    <link rel="stylesheet" href="../../../pub/css/user/form.css">
    <link rel="stylesheet" href="../../../pub/css/user/login.css">
    <link rel="stylesheet" href="../../../pub/css/snake/addForm.css">
    <link rel="stylesheet" href="../../../pub/css/feederSnake/addFeeding.css">
    <link rel="stylesheet" href="../../../pub/css/snake/showSnakes.css">
    <link rel="stylesheet" href="../../../pub/css/feeder/showFeeders.css">
    <script src="https://kit.fontawesome.com/2c911235fd.js" crossorigin="anonymous"></script>
    <title>Snake Management</title>
</head>

<body>

    <div class="container">
        <nav class="navi">
            <div class="navi__container">
                <ul class="navi__ul">
                    <li class="navi__li"><a href="/index">Index</a></li>
                    <sec:authorize access="!isAuthenticated()">
                        <li class="navi__li"><a href="/login/login">Login</a></li>
                        <li class="navi__li"><a href="/user/register">Sign up</a></li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li class="navi__li"><a href="#">Snake</a>
                            <ul>
                                <li class="navi__li"><a href="/snake/add">Add Snake</a> </li>
                                <li class="navi__li"><a href="/snake/showSnakes">Show Snakes</a></li>
                            </ul>
                        </li>

                        <li class="navi__li"><a href="#">Feeder</a>
                            <ul>
                                <li class="navi__li"><a href="/feeder/add">Add Feeder</a></li>
                                <li class="navi__li"><a href="/feeder/showFeeders">Show Feeders</a></li>
                            </ul>
                        </li>

                        <li class="navi__li"><a href="#">Feeding</a>
                            <ul>
                                <li class="navi__li"><a href="/feederSnake/add">Add Feeding</a></li>
                                <li class="navi__li"><a href="/feederSnake/showFeedings">Show Feedings</a></li>
                            </ul>
                        </li>
                    </sec:authorize>
                </ul>

                <sec:authorize access="isAuthenticated()">
                    <ul class="navi__admin">
                        <li class="navi__li--admin">welcome <sec:authentication property="principal.username" /></li>
<%--                    <sec:authorize access="hasAuthority('USER')">--%>
                            <li class="navi__li--admin"><a href="/login/logout">Logout</a></li>
<%--                    </sec:authorize>--%>
                    <sec:authorize access="hasAuthority('ADMIN')">
                            <li class="navi__li--admin"><a href="/user/search">Search</a></li>
                    </sec:authorize>
                    </ul>
                </sec:authorize>
            </div>
        </nav>
    </div>
