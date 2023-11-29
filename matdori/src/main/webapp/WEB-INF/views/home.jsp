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
                  <img src="/images/mainA.png" width="100%" height="100%">
               </div>
               <div class="swiper-slide">
                  <img src="/images/mainB.png" width="100%" height="100%">
               </div>
               <div class="swiper-slide">
                  <img src="/images/mainC.png" width="100%" height="100%">
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
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=한식">
                      <img src="/images/korea.png" width="100px" height="100px">
                  </a>
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=한식" class="categoryTag">한식</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=양식">
                      <img src="/images/steak.png" width="100px" height="100px">
                  </a>
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=양식" class="categoryTag">양식</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=중식">
                      <img src="/images/dumpling.png" width="100px" height="100px">
                  </a>
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=중식" class="categoryTag">중식</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=일식">
                      <img src="/images/japan.png" width="100px" height="100px">
                  </a>
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=일식" class="categoryTag">일식</a>
              </div>
          </div>
      </div>
      <div class="row justify-content-center" style="margin-top: 30px;">
          <div class="col-lg-6 col-md-6 col-sm-6 col-12 d-flex align-items-center justify-content-around">
              <div class="d-flex flex-column align-items-center">
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=오마카세">
                      <img src="/images/salmon.png" width="100px" height="100px">
                  </a>
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=오마카세" class="categoryTag">오마카세</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=우마카세">
                      <img src="/images/meat.png" width="100px" height="100px">
                  </a>
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=우마카세" class="categoryTag">우마카세</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=이자카야">
                      <img src="/images/sool.png" width="100px" height="100px">
                  </a>
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=이자카야" class="categoryTag">이자카야</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=콜키지">
                      <img src="/images/red-wine.png" width="100px" height="100px">
                  </a>
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=콜키지" class="categoryTag">콜키지</a>
              </div>
          </div>
      </div>
      <div class="row justify-content-center" style="margin-top: 30px;">
          <div class="col-lg-6 col-md-6 col-sm-6 col-12 d-flex align-items-center justify-content-around">
              <div class="d-flex flex-column align-items-center">
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=데이트">
                      <img src="/images/couple.png" width="100px" height="100px">
                  </a>
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=데이트" class="categoryTag">데이트</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=루프탑">
                      <img src="/images/rooftop.png" width="100px" height="100px">
                  </a>
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=루프탑" class="categoryTag">루프탑</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=디저트">
                      <img src="/images/dessert.png" width="100px" height="100px">
                  </a>
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=디저트" class="categoryTag">디저트</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=단체">
                      <img src="/images/teamwork.png" width="100px" height="100px">
                  </a>
                  <a href="/restaurant/resSearchList?regionName=&resName=&hashComment=단체" class="categoryTag">단체</a>
              </div>
          </div>
      </div>
      
      
      <!-- 매장 목록 -->
      <div class="row justify-content-center" style="margin-top: 70px;">
          <div class="col" style="margin: 0 250px;">
              <hr style="border-color: #FFB416; border-width: 5px;">
              <div class="d-flex justify-content-between">
                  <h2 class="text-start m-0 mb-2" style="display: inline;">BEST 맛집</h2>
                  <a class="detail-tag" href="#detail 들어가는 링크" style="display: inline;"> 
                      전체보기  <i class="fa-solid fa-angles-right" style="color: #000000;"></i>
                  </a>
              </div>
      
      
            <!-- 이거 나중에는 반복문으로 처리해야함 -->
              <div class="row justify-content-center">
                  <div class="col">
                      <a class="detail-tag" href="#res 들어가는 링크">
                          <img src="/images/dummy.png" width="200px" height="200px">
                      </a>
                  </div>
      
                  <div class="col">
                      <a class="detail-tag" href="#res 들어가는 링크">
                          <img src="/images/dummy.png" width="200px" height="200px">
                      </a>
                  </div>
      
                  <div class="col">
                      <a class="detail-tag" href="#res 들어가는 링크">
                          <img src="/images/dummy.png" width="200px" height="200px">
                      </a>
                  </div>
                  
                  <div class="col">
                      <a class="detail-tag" href="#res 들어가는 링크">
                          <img src="/images/dummy.png" width="200px" height="200px">
                      </a>
                  </div>
              </div>
      
          </div>
      </div>
      
      </div>
      </div>

<br>



</section>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>