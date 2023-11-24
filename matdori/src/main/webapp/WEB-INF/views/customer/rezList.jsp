<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<style>
   .row-top{
      margin-top : 20%;
   }
   .page-line {
      border-right: 3px solid #ffb416; 
   }
   .bold{
      font-weight : bold;
   }
   .menu-tag{
      text-decoration: none;
      color: black;
   }
   .menu-tag:hover {
        color: #FFB416; 
   }
   .res-line{
      border-top: 1px solid #ffb416;
      border-bottom: 1px solid #ffb416;
      width: 70%;
   }
   
   .rating {
   width: 100%;
}

   .rating__star {
    cursor: pointer;
    color: #FFB416;
}
   
</style>

<div class="container">
   <div class="row" style="margin-top: 5%">
   
      <!-- 제목 -->
		<div class="row mb-4">
			<h1 class="bold">
				<a class="menu-tag" href="mypage">
					<i class="fa-solid fa-user" style="color: #ffb416;"></i> 마이페이지
				</a>
			</h1>
		</div>


		<!-- 마이페이지 탭 -->
		<div class="col-3 page-line">
			<div class="row">
				<h2 class="bold">
					<a class="menu-tag" href="pick">북마크</a>
				</h2>
			</div>
			<div class="row row-top">
				<h2 class="bold">
					<a class="menu-tag" style="color: #FFB416;" href="rezList">예약내역</a>
				</h2>
			</div>
			<div class="row row-top">
				<h2 class="bold">
					<a class="menu-tag" href="reviewListByCus">나의리뷰</a>
				</h2>
			</div>
		</div>
      
      
      
      <!-- 주 내용 -->
      <div class="col-9">
         <div class="row">
            <c:forEach var="reservationListDto" items="${rezList}">
                <div>
                    <br>
                </div>
                <div class="row res-line p-4" style="margin-left: 100px;">
                      <div class="col-3">
                        사진자리
                    </div>
                    <div class="col-7">
                        <div class="row modal-title">
                            ${reservationListDto.resName}
                        </div>
                        <div class="row">
                            예약일 : ${fn:substring(reservationListDto.clockSelect, 0, 10)}
                        </div>
                        <div class="row">
                            예약시간 : ${fn:substring(reservationListDto.clockSelect, 11, 16)}
                        </div>
                    </div>
                    <div class="col-2 text-end">
                        <div class="row mb-3">
                            <a href="#" class="menu-tag">예약상세 ></a>

                        </div>
                        <div class="row">
                            <a class="btn btn-warning btn-sm open-modal-review">리뷰작성</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
         </div>
      </div>
      
   </div>
</div>


      


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>