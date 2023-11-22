<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
	.h-title{
		font-weight: bold;
		color: #ffb416;
	}
	.list-border{
		border: 2px solid #ffb416;
		border-radius: 10px;
		padding : 20px;
	}
	.res-line{
		border-top: 2px solid #ffb416;
		border-bottom: 2px solid #ffb416;
		width: 100%;
	}
</style>



<div class="container">
	<div class="col">
		<div class="row justify-content-center">
			<div class="col-md-6 mt-4">
				<h1 class="text-center ">
					<i class="fa-regular fa-credit-card" style="color: #ffb416;"></i>매장상세
				</h1>
			</div>
		</div>
		
		<div class="row justify-content-center text-center list-border">
			<div class="row">
				<h3>${resDto.resName}왼쪽으로 왜 안가냐</h3>
				<button class="btn btn-warning btn-sm">찜하기 니는 왜 크기가 이모양이냐</button>
			</div>
			<div class="row">
				<i class="far fa-star">0.1의 평균 계산해서 넣기 너도 왼쪽으로 별은 왜 비어잇냐</i>
			</div>
			<div class="row">
				매장 사진 겁나 크게
			</div>
			<div class="row">
				<button class="btn btn-warning">
				예약하기</button>
			</div>
			<div class="row">
				홈/공지/메뉴/리뷰 
			</div>
			<div>
				<div class="row">
					매장 위치 : ${resDto.resAddr1} / ${resDto.resAddr2} 지도 어떻게 넣어주냐
				</div>
				<div class="row">
					영업 시간 : ${resDto.resOpenTime} 어? 이거 몇시부터 몇시 이렇게 돼야 하는데
				</div>
			</div>
			<div class="row">
				공지사항<br>
				${resDto.resNotice} 여기는 공지사항입니다 ㅅㅂ
			</div>
			------------------------------------------------------------
			<div class="row">
				<c:forEach var="menuListByRes" items="${menuListByRes}">
					<div class="row">
						${menuListByRes.menuName}
						-
						${menuListByRes.menuPrice}원
						-
						${menuListByRes.menuContent}
					</div>
					<div class="row">
						<button class="btn menuSelect" data-menu-no="${menuListByRes.menuNo}">
							선택
						</button>
					</div>
				</c:forEach>
			</div>
			----------------------------------------------------------
			<div class="row">
				<c:forEach var="reviewByRes" items="${reviewByRes}">
					<div class="row">
						${reviewByRes.reviewWriter}
						-
						${reviewByRes.reviewContent}
						-
						${reviewByRes.reviewWriteDate}
						-
						${reviewByRes.reviewStarPoint}
					</div>
				</c:forEach>
			</div>
			<div>
				
			</div>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>