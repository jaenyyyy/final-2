<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>



<style>
   .swiper {
      width: 50%; 
      height: 400px;
      margin: 0 auto; 
      border-radius: 15px;
   }
   
   .categoryTag {
      text-decoration: none;
      color: black;
   }
   
   .detail-tag{
      text-decoration: none;
       color: black;
   }


</style>

<script>
   $(function() {
      var swiper = new Swiper('.swiper', {
         loop : true,
         pagination : {
            el : '.swiper-pagination',
            type : 'bullets',
            clickable : true,
         },
         navigation : {
            nextEl : '.swiper-button-next',
            prevEl : '.swiper-button-prev',
            hideOnClick : true,
         },
         autoplay : {
            delay : 2000,
            pauseOnMouseEnter : true,
         },
         effect : "slide",
      });
   });
</script>

<div class="row justify-content-center mt-4">
    <div class="col">
      <div class="row">
         <!-- Slider main container -->
         <div class="swiper">
            <!-- Additional required wrapper -->
            <div class="swiper-wrapper">
               <!-- Slides -->
               <div class="swiper-slide">
                  <img src="${pageContext.request.contextPath}/images/mainA.png" width="100%" height="100%">
               </div>
               <div class="swiper-slide">
                  <img src="${pageContext.request.contextPath}/images/mainB.png" width="100%" height="100%">
               </div>
               <div class="swiper-slide">
                  <img src="${pageContext.request.contextPath}/images/mainC.png" width="100%" height="100%">
               </div>
            </div>
            <!-- If we need pagination -->
            <div class="swiper-pagination" style="color: #FFB416;"></div>
            <!-- If we need navigation buttons -->
            <div class="swiper-button-prev" style="color: #FFB416;"></div>
            <div class="swiper-button-next" style="color: #FFB416;"></div>
         </div>
      </div>


      <!-- 카테고리 -->
      <div class="row justify-content-center" style="margin-top: 70px;">
          <div class="col-lg-6 col-md-6 col-sm-6 col-12 d-flex align-items-center justify-content-around">
              <div class="d-flex flex-column align-items-center">
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=한식">
                      <img src="${pageContext.request.contextPath}/images/korea.png" width="100px" height="100px">
                  </a>
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=한식" class="categoryTag">한식</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=양식">
                      <img src="${pageContext.request.contextPath}/images/steak.png" width="100px" height="100px">
                  </a>
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=양식" class="categoryTag">양식</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=중식">
                      <img src="${pageContext.request.contextPath}/images/dumpling.png" width="100px" height="100px">
                  </a>
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=중식" class="categoryTag">중식</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=일식">
                      <img src="${pageContext.request.contextPath}/images/japan.png" width="100px" height="100px">
                  </a>
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=일식" class="categoryTag">일식</a>
              </div>
          </div>
      </div>
      <div class="row justify-content-center" style="margin-top: 30px;">
          <div class="col-lg-6 col-md-6 col-sm-6 col-12 d-flex align-items-center justify-content-around">
              <div class="d-flex flex-column align-items-center">
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=오마카세">
                      <img src="${pageContext.request.contextPath}/images/salmon.png" width="100px" height="100px">
                  </a>
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=오마카세" class="categoryTag">오마카세</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=우마카세">
                      <img src="${pageContext.request.contextPath}/images/meat.png" width="100px" height="100px">
                  </a>
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=우마카세" class="categoryTag">우마카세</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=이자카야">
                      <img src="${pageContext.request.contextPath}/images/sool.png" width="100px" height="100px">
                  </a>
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=이자카야" class="categoryTag">이자카야</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=콜키지">
                      <img src="${pageContext.request.contextPath}/images/red-wine.png" width="100px" height="100px">
                  </a>
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=콜키지" class="categoryTag">콜키지</a>
              </div>
          </div>
      </div>
      <div class="row justify-content-center" style="margin-top: 30px;">
          <div class="col-lg-6 col-md-6 col-sm-6 col-12 d-flex align-items-center justify-content-around">
              <div class="d-flex flex-column align-items-center">
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=데이트">
                      <img src="${pageContext.request.contextPath}/images/couple.png" width="100px" height="100px">
                  </a>
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=데이트" class="categoryTag">데이트</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=루프탑">
                      <img src="${pageContext.request.contextPath}/images/rooftop.png" width="100px" height="100px">
                  </a>
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=루프탑" class="categoryTag">루프탑</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=디저트">
                      <img src="${pageContext.request.contextPath}/images/dessert.png" width="100px" height="100px">
                  </a>
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=디저트" class="categoryTag">디저트</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=단체">
                      <img src="${pageContext.request.contextPath}/images/teamwork.png" width="100px" height="100px">
                  </a>
                  <a href="${pageContext.request.contextPath}/restaurant/resSearchList?regionName=&resName=&hashComment=단체" class="categoryTag">단체</a>
              </div>
          </div>
      </div>
      
      
      
      </div>
      </div>

<br>


 
</section>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>