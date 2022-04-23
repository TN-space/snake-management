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

<div class="index-wrapper">
    <div class="index-container">
        <div class="slider">
            <div class="slider__box__container">
                <div class="slider__box">
                    <img class="slider__image" src="../../pub/img/hypo.jpg" alt="hypo">
                </div>
            </div>
            <div class="slider__box__container">
                <div class="slider__box">
                    <img class="slider__image" src="../../pub/img/hypo.jpg" alt="hypo">
                </div>
            </div>
            <div class="slider__box__container">
                <div class="slider__box">
                    <img class="slider__image" src="../../pub/img/hypo.jpg" alt="hypo">
                </div>
            </div>
            <div class="slider__box__container">
                <div class="slider__box">
                    <img class="slider__image" src="../../pub/img/hypo.jpg" alt="hypo">
                </div>
            </div>
            <div class="slider__box__container">
                <div class="slider__box">
                    <img class="slider__image" src="../../pub/img/hypo.jpg" alt="hypo">
                </div>
            </div>

<%--            <div class="slider__box">--%>
<%--                <img class="slider__image" src="../../pub/img/moonglow.jpg" alt="moonglow">--%>
<%--            </div>--%>
<%--            <div class="slider__box">--%>
<%--                <img class="slider__image" src="../../pub/img/sunglow.jpg" alt="sunglow">--%>
<%--            </div>--%>
<%--            <div class="slider__box">--%>
<%--                <img class="slider__image" src="../../pub/img/img.jpg" alt="img">--%>
<%--            </div>--%>
<%--            <div class="slider__box">--%>
<%--                <img class="slider__image" src="../../pub/img/albino.jpg" alt="albino">--%>
<%--            </div>--%>
        </div>
    </div>
</div>

<script>
    $(document).ready(function(){
        $('.slider').slick({
            slidesToShow: 3,
            slidesToScroll: 1,
            autoplay: true,
            centerMode: true,
            autoplaySpeed: 2000,
        })
    })
</script>
<jsp:include page="include/footer.jsp" />