<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>



<style>
   .swiper {
      width: 70%; 
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
                  <img src="/images/dummy.png" width="100%" height="100%">
               </div>
               <div class="swiper-slide">
                  <img src="/images/dummy.png" width="100%" height="100%">
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
                  <a href="#카테고리 링크">
                      <img src="/images/category.png" width="100px" height="100px">
                  </a>
                  <a href="#카테고리 링크" class="categoryTag">카테고리</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="#카테고리 링크">
                      <img src="/images/category.png" width="100px" height="100px">
                  </a>
                  <a href="#카테고리 링크" class="categoryTag">카테고리</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="#카테고리 링크">
                      <img src="/images/category.png" width="100px" height="100px">
                  </a>
                  <a href="#카테고리 링크" class="categoryTag">카테고리</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="#카테고리 링크">
                      <img src="/images/category.png" width="100px" height="100px">
                  </a>
                  <a href="#카테고리 링크" class="categoryTag">카테고리</a>
              </div>
          </div>
      </div>
      <div class="row justify-content-center" style="margin-top: 30px;">
          <div class="col-lg-6 col-md-6 col-sm-6 col-12 d-flex align-items-center justify-content-around">
              <div class="d-flex flex-column align-items-center">
                  <a href="#카테고리 링크">
                      <img src="/images/category.png" width="100px" height="100px">
                  </a>
                  <a href="#카테고리 링크" class="categoryTag">카테고리</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="#카테고리 링크">
                      <img src="/images/category.png" width="100px" height="100px">
                  </a>
                  <a href="#카테고리 링크" class="categoryTag">카테고리</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="#카테고리 링크">
                      <img src="/images/category.png" width="100px" height="100px">
                  </a>
                  <a href="#카테고리 링크" class="categoryTag">카테고리</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="#카테고리 링크">
                      <img src="/images/category.png" width="100px" height="100px">
                  </a>
                  <a href="#카테고리 링크" class="categoryTag">카테고리</a>
              </div>
          </div>
      </div>
      <div class="row justify-content-center" style="margin-top: 30px;">
          <div class="col-lg-6 col-md-6 col-sm-6 col-12 d-flex align-items-center justify-content-around">
              <div class="d-flex flex-column align-items-center">
                  <a href="#카테고리 링크">
                      <img src="/images/category.png" width="100px" height="100px">
                  </a>
                  <a href="#카테고리 링크" class="categoryTag">카테고리</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="#카테고리 링크">
                      <img src="/images/category.png" width="100px" height="100px">
                  </a>
                  <a href="#카테고리 링크" class="categoryTag">카테고리</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="#카테고리 링크">
                      <img src="/images/category.png" width="100px" height="100px">
                  </a>
                  <a href="#카테고리 링크" class="categoryTag">카테고리</a>
              </div>
              <div class="d-flex flex-column align-items-center">
                  <a href="#카테고리 링크">
                      <img src="/images/category.png" width="100px" height="100px">
                  </a>
                  <a href="#카테고리 링크" class="categoryTag">카테고리</a>
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