<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
.text-badge {
   font-weight: bold;
   color: black;
}

.list-border {
   border: 2px solid #ffb416;
   border-radius: 10px;
   padding: 20px;
}

.res-line {
   border-top: 2px solid #ffb416;
   border-bottom: 2px solid #ffb416;
   width: 100%;
}

.star-avg {
   font-weight: bold;
   color: black;
   font-size: 25px;
}

.main-images {
   max-width: 100%;
   height: auto;
   display: flex;
   justify-content: center;
   align-items: center;
}

.sub-images {
   max-width: 50%;
   height: auto;
   display: flex;
   justify-content: center;
   align-items: center;
}

.info-margin {
   margin-bottom: 40px;
   margin-top: 40px;
}

.under-line {
   text-decoration: none;
   color: black;
}

.box {
   width: 200px;
   height: 200px;
   border: 2px solid #000000;
}

.rating {
   width: 100%;
}

.rating__star {
   cursor: pointer;
   color: #FFB416;
}
</style>





<div class="container" id="homeSection">
   <div class="col">

      <!-- 제목 틀 -->
      <div class="row justify-content-center mb-3" style="margin-top: 70px;">
         <div class="col-6 text-start">

            <!— 매장이름 —>
            <div class="row">
               <h1 class="text-left title" style="font-weight: bold;">${resDto.resName}</h1>
            </div>



<!--             별점 -->
            <div class="row">
<!--                <span class="star-avg"> <span -->
<%--                   class="rating__star ${averageRating >= 1 ? 'fas' : 'far'} fa-star"></span> --%>
<!--                   <span -->
<%--                   class="rating__star ${averageRating >= 2 ? 'fas' : 'far'} fa-star"></span> --%>
<!--                   <span -->
<%--                   class="rating__star ${averageRating >= 3 ? 'fas' : 'far'} fa-star"></span> --%>
<!--                   <span -->
<%--                   class="rating__star ${averageRating >= 4 ? 'fas' : 'far'} fa-star"></span> --%>
<!--                   <span -->
<%--                   class="rating__star ${averageRating >= 5 ? 'fas' : 'far'} fa-star"></span> --%>
<%--                   ${String.format("%.1f", averageRating)}점 식당 --%>
<!--                </span> -->
            </div>
         </div>


         <!-- 북마크 : 클릭하면 북마크 설정 / ajax로 값 전송 되게 구현할 예정 -->
         <div class="col-6 text-end">
            <c:choose>
               <c:when test="${empty sessionScope.name}">
               </c:when>
               <c:otherwise>
                  <c:choose>
                     <c:when test="${pickDto.resNo != null}">
                        <i class="fa-solid fa-bookmark fa-3x bookmark"
                           style="color: #ffb416;"></i>
                        <br>
                        <span></span>
                     </c:when>
                     <c:otherwise>
                        <i class="fa-regular fa-bookmark fa-3x bookmark"
                           style="color: #ffb416;"></i>
                        <br>
                        <span></span>
                     </c:otherwise>
                  </c:choose>
               </c:otherwise>
            </c:choose>
         </div>
      </div>

      <!-- 사진 틀-->
      <div class="row justify-content-center mb-3 mt-4">
<div class="container">
    <div class="row">
        <!-- Main Image (Left) -->
        <div class="col-md-6 mb-3">
            <div class="row mb-3">
                <img src="/image/restaurant/image/first/${resDto.resNo}" alt="Main Restaurant Image" style="max-width: 600px; height: 600px;">
            </div>
        </div>
        <!-- Sub Images (Right) -->
        <div class="col-md-6 mb-3">
            <div class="row">
                <div class="col-6">
                    <div class="row mb-3">
                        <img src="/image/restaurant/image/second/${resDto.resNo}" alt="Sub Restaurant Image" style="max-width: 100%; height: 300px;">
                    </div>
                </div>
                <div class="col-6">
                    <div class="row mb-3">
                        <img src="/image/restaurant/image/third/${resDto.resNo}" alt="Sub Restaurant Image" style="max-width: 100%; height: 300px;">
                    </div>
                </div>
                <div class="col-6">
                    <div class="row mb-3">
                        <img src="/image/restaurant/image/fourth/${resDto.resNo}" alt="Sub Restaurant Image" style="max-width: 100%; height: 300px;">
                    </div>
                </div>
                <div class="col-6">
                    <div class="row mb-3">
                        <img src="/image/restaurant/image/fifth/${resDto.resNo}" alt="Sub Restaurant Image" style="max-width: 100%; height: 300px;">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

        
      


      <!-- 예약 버튼 -->
      
<%--       <c:if test="${sessionSope.name != null}"> --%>
         <div class="row justify-content-center mb-3 mt-4">
            <div class="row justify-content-center">
               <a href="/reservation/insert?rezResNo=${resDto.resNo}"
                  class="btn btn-warning w-50 text-black"> 예약하기 </a>
            </div>
         </div>
<%--       </c:if> --%>




      <!-- 메뉴버튼 : 네비바로 사용함 -->
      <nav class="container-fluid navbar navbar-expand-lg bg-light mb-4"
         data-bs-theme="light" id="navbar">
         <div class="collapse navbar-collapse" id="navbarColor01">
            <ul class="navbar-nav w-100">
               <li class="nav-item flex-fill text-center"><a
                  class="nav-link fw-bold" href="#homeSection">홈</a></li>
               <li class="nav-item flex-fill text-center"><a
                  class="nav-link fw-bold" href="#noticeSection">공지</a></li>
               <li class="nav-item flex-fill text-center"><a
                  class="nav-link fw-bold" href="#menuSection">메뉴</a></li>
               <li class="nav-item flex-fill text-center"><a
                  class="nav-link fw-bold" href="#reviewSection">리뷰</a></li>
            </ul>
         </div>
      </nav>


      <!-- 상세정보 -->
      <div
         class="row justify-content-center text-center list-border info-margin">
         <!-- 제목 -->
         <div class="row text-start">
            <h3>
               <span class="badge rounded-pill bg-warning text-black">상세정보</span>
            </h3>
         </div>
         <!-- 매장설명 -->
         <div class="col-8 text-start">
            <div class="row text-start">
            	<h5>
            	매장위치 : ${resDto.resAddr1} ${resDto.resAddr2} <i class="fa-solid fa-grip-lines-vertical"></i>  
            	영업 시간 : ${resDto.resOpenTime} <i class="fa-solid fa-grip-lines-vertical"></i>
            	매장번호 : ${resDto.resTel}
            	</h5>
           	</div>
         </div>
         <!-- 해시태그 : 이거도 해시태그 불러와서 forEach 써야함 -->
         <div class="row justify-content-center mt-4 text-center">
            <c:forEach var="hashDto" items="${resHashDto}">
               <div class="col">
                  <h4>
                     <span class="badge rounded-pill bg-light text-black">#
                        ${hashDto.hashComment}</span>
                  </h4>
               </div>
            </c:forEach>
         </div>
      </div>
   </div>

   <!-- 공지사항 -->
   <div
      class="row justify-content-center text-center list-border info-margin "
      id="noticeSection">
      <!-- 제목 -->
      <div class="row text-start">
         <h3>
            <span class="badge rounded-pill bg-warning text-black">공지사항</span>
         </h3>
      </div>
      <!-- 공지내용 -->
      <div class="row text-start">
         <div class="row">${resDto.resNotice}</div>
      </div>
   </div>



   <!-- 메뉴 -->
   <div
      class="row justify-content-center text-center list-border info-margin"
      id="menuSection">
      <!-- 제목 -->
      <div class="row text-start">
         <h3>
            <span class="badge rounded-pill bg-warning text-black">메뉴</span>
         </h3>
      </div>
      <!-- 메뉴리스트 : list [0]~[3] 번쨰만 꺼내야할듯 -->
      <div class="container">
         <div class="row">
            
            
            
            <!-- 첫 번째 : 나중에 반복문으로처리 / 위치 잡으려고 네개 해놓음 -->
            <div class="col-md-6">
               <div class="row">
                  <div class="col-6">
                     <img src="/images/dummy.png" class="sub-images">
                  </div>
                  <div class="col-6">
                     <div class="row text-badge text-start">
                        <h5 class="text-badge">메뉴이름</h5>
                     </div>
                     <div class="row">메뉴설명</div>
                     <div class="row" style="color: red;">가격</div>
                  </div>
               </div>
            </div>
            <!-- 두 번째 : 나중에 반복문으로처리 / 위치 잡으려고 네개 해놓음 -->
            <div class="col-md-6">
               <div class="row">
                  <div class="col-6">
                     <img src="/images/dummy.png" class="sub-images">
                  </div>
                  <div class="col-6">
                     <div class="row text-badge text-start">
                        <h5 class="text-badge">메뉴이름</h5>
                     </div>
                     <div class="row">메뉴설명</div>
                     <div class="row" style="color: red;">가격</div>
                  </div>
               </div>
            </div>
         </div>
         <div class="row">
            <!-- 세 번째 : 나중에 반복문으로처리 / 위치 잡으려고 네개 해놓음 -->
            <div class="col-md-6">
               <div class="row">
                  <div class="col-6">
                     <img src="/images/dummy.png" class="sub-images">
                  </div>
                  <div class="col-6">
                     <div class="row text-badge text-start">
                        <h5 class="text-badge">메뉴이름</h5>
                     </div>
                     <div class="row">메뉴설명</div>
                     <div class="row" style="color: red;">가격</div>
                  </div>
               </div>
            </div>
            <!-- 네 번째 : 나중에 반복문으로처리 / 위치 잡으려고 네개 해놓음 -->
            <div class="col-md-6">
               <div class="row">
                  <div class="col-6">
                     <img src="/images/dummy.png" class="sub-images">
                  </div>
                  <div class="col-6">
                     <div class="row text-badge text-start">
                        <h5 class="text-badge">메뉴이름</h5>
                     </div>
                     <div class="row">메뉴설명</div>
                     <div class="row" style="color: red;">가격</div>
                  </div>
               </div>
            </div>
         </div>
      </div>

      <!-- 상세로 가는 a태그 -->
      <div class="row mt-4">
         <h5>
            <a href="/restaurant/menuList?resNo=${resDto.resNo}" class="under-line text-badge"> 메뉴 더보기<i
               class="fa-solid fa-angles-right" style="color: #000000;"></i>
            </a>
         </h5>
      </div>
   </div>



   <!-- 리뷰 -->
   <div
      class="row justify-content-center text-center list-border info-margin"
      id="reviewSection">
      <!-- 제목 -->
      <div class="row text-start">
         <h3>
            <span class="badge rounded-pill bg-warning text-black">리뷰</span>
         </h3>
      </div>
      <div class="row text-start">
         <div class="row">
            <div class="col">
               <c:choose>
                  <c:when test="${customerDto.customerLevel == '어쩌고수저'}">
                     <i class="fa-solid fa-spoon" style="color: #b5b5b5;"></i>
                  </c:when>
               </c:choose>
               ${reviewDto.reviewWriter}
            </div>
         </div>
         <div class="row">
            <c:forEach var="ReviewDto" items="${reviewByRes}" varStatus="loop">
               <c:if test="${loop.index < 3}">
                  <div class="col-2 mt-2">
                     <img
                        src="http://localhost:8080/customer/image?reviewNo=${ReviewDto.reviewNo}"
                        width="100" height="100" class="">
                  </div>
                  <div class="col-10 text-left mt-2">
                     <span> <span
                        class="rating__star ${ReviewDto.reviewStarPoint >= 1 ? 'fas' : 'far'} fa-star"></span>
                        <span
                        class="rating__star ${ReviewDto.reviewStarPoint >= 2 ? 'fas' : 'far'} fa-star"></span>
                        <span
                        class="rating__star ${ReviewDto.reviewStarPoint >= 3 ? 'fas' : 'far'} fa-star"></span>
                        <span
                        class="rating__star ${ReviewDto.reviewStarPoint >= 4 ? 'fas' : 'far'} fa-star"></span>
                        <span
                        class="rating__star ${ReviewDto.reviewStarPoint >= 5 ? 'fas' : 'far'} fa-star"></span>
                        ${ReviewDto.reviewWriteDate}
                     </span> <br>${ReviewDto.reviewContent}
                  </div>
               </c:if>
            </c:forEach>
         </div>
      </div>

      <!-- 리뷰 더보기 버튼 -->
      <div class="row mt-4">
         <h5>
            <a href="/restaurant/reviewList?resNo=${resDto.resNo}"
               class="under-line text-badge">리뷰 더보기<i
               class="fa-solid fa-angles-right" style="color: #000000;"></i>
            </a>
         </h5>
      </div>


   </div>
</div>
</div>


<script>
//메뉴 누르면 부드럽게 이동하는거
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();

        const targetSection = document.querySelector(this.getAttribute('href'));

        window.scrollTo({
            top: targetSection.offsetTop - window.innerHeight / 3, 
            behavior: 'smooth'
        });
    });
});

// 스크롤 해도 네비바 고정시키는거
window.addEventListener('scroll', function() {
    const navbar = document.getElementById('navbar'); 
    const scrollThreshold = 800; 

    if (window.scrollY > scrollThreshold) {
        navbar.style.position = 'fixed'; 
        navbar.style.top = '0';
        navbar.style.left = '50%'; 
        navbar.style.transform = 'translateX(-50%)';
    } else {
        navbar.style.position = 'relative'; 
        navbar.style.left = 'auto'; 
        navbar.style.transform = 'none'; 
    }
});

<!-- 메뉴 선택이다 임마 하 다 쓸모가 없어 졋구나 제기럴-->
// $(function(){
//     $(".menuSelect").click(function(e){
//        e.preventDefault();
        
//         // 상품 번호 가져오기
//         var menuNo = $(this).data("menu-no");
        
//         $.ajax({
//             url: "/rest/reservation/add",
//             method: "post",
//             data: { menuNo: menuNo },
//             success: function(response) {
//                     $("#modalMessage").text(response.message);
// //                     openModal(); // 모달 열기

//             },
//             error: function (xhr) {
//                 // 에러 처리
//                 console.log(arguments);
//                 $("#modalMessage").text(xhr.responseJSON.message);
// //                 openModal(); // 모달 열기
//             },
            
//         });
//     });
//  });
</script>


<!-- 북마크 설정/해제를 위한 -->
<c:if test="${sessionScope.name != null}">
   <script>
        $(function() {
           var params = new URLSearchParams(location.search);
           var resNo = params.get("resNo");

           /* console.log("resNo:", resNo); */

            $.ajax({
                url: "/rest/pick/check",
                method: "post",
                data: { resNo: resNo },
                success: function(response) {
                    if (response.check) {
                        $(".fa-bookmark").removeClass("fa-solid fa-regular").addClass("fa-solid");
                    } else {
                        $(".fa-bookmark").removeClass("fa-solid fa-regular").addClass("fa-regular");
                    }
                //전달받은 찜 개수를 북마크 뒤의 span에 출력
                    $(".fa-bookmark").next("br").next("span").text(response.count + "명이 찜한 식당!");
                }
            });

            $(".fa-bookmark").click(function() {
               console.log("북마크 클릭됨");
                $.ajax({
                    url: "/rest/pick/action",
                    method: "post",
                    data: { resNo: resNo },
                    success: function(response) {
                        if (response.check) {
                            $(".fa-bookmark").removeClass("fa-solid fa-regular").addClass("fa-solid");
                        } else {
                            $(".fa-bookmark").removeClass("fa-solid fa-regular").addClass("fa-regular");
                        }
                    //전달받은 찜 개수를 북마크 뒤의 span에 출력
                        $(".fa-bookmark").next("br").next("span").text(response.count + "명이 찜한 식당!");
                    }
                });
            });
        });
    </script>
</c:if>




<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>