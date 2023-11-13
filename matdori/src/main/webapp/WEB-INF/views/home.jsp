<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>



<style>
    .swiper {
        width: 200%;
        height: 400px;
    }
</style>

<script>
    $(function () {
        var swiper = new Swiper('.swiper', {
            loop: true,
            pagination: {
                el: '.swiper-pagination',
                type: 'bullets',
                clickable: true,
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
                hideOnClick: true,
            },
            autoplay: {
                delay: 2000,
                pauseOnMouseEnter: true,
            },
            effect: "slide",
        });
    });
</script>

<div class="row mt-4">
    <div class="col">
        <div class="container w-900">
            <div class="row">
                <!-- Slider main container -->
                <div class="swiper">
                    <!-- Additional required wrapper -->
                    <div class="swiper-wrapper">
                        <!-- Slides -->
                        <div class="swiper-slide">
                            <img src="/images/dummy.png" width="100%" height="100%">
                        </div>
                        <div class="swiper-slide">
                            <img src="/images/logo.png" width="100%" height="100%">
                        </div>
                    </div>
                    <!-- If we need pagination -->
                    <div class="swiper-pagination"></div>
                    <!-- If we need navigation buttons -->
                    <div class="swiper-button-prev"></div>
                    <div class="swiper-button-next"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<h3> 맛도리 홈페이지 입니당 ~ </h3>





<a href="exit">회원 탈퇴</a>
<br>
<a href="customer/findPw">비밀번호 찾기</a>


</section>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>