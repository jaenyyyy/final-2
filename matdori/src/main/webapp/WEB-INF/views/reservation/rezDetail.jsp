<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
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
    <div class="col-md-8 mx-auto">
	
		<!-- 이름 -->
		<div class="row justify-content-center">
			<div class="col-md-6 mt-4">
				<h1 class="text-center ">
					<i class="fa-regular fa-credit-card" style="color: #ffb416;"></i>결제하기
				</h1>
			</div>
		</div>
		
		
		
		<!-- 주내용 -->		
		<div class="row justify-content-center text-center">
		
			<!-- 매장정보 -->
			<div class="row ms-4 mt-4 mb-4">
				
				<div class="row res-line p-3">
				        <div class="col-3">
				            사진자리
				        </div>
				        <div class="col-7">
				            <input type="hidden" class="resNoInput" value="${ReservationListDto.resNo}">
				            <div class="row">
				                ${rezDto.resName}
				            </div>
				            <div class="row">
				                예약일 : ${fn:substring(ReservationListDto.clockSelect, 0, 10)}
				            </div>
				            <div class="row">
				                예약시간 : ${fn:substring(ReservationListDto.clockSelect, 11, 16)}
				            </div>
				        </div>
				        
				    </div>
			</div>

			<div class="col">
				<!-- 예약자 정보 -->
				<div class="list-border mb-4">
					<div>
						<h5 class="h-title">예약자정보</h5>
					</div>
					<div>
						<table class="table table-border table-stripe">
							<tr>
								<th>아이디</th>
								<td>${rezDto.rezCustomerId}</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- 예약내역 -->
				<div class="list-border">
					<div>
						<h5 class="h-title">예약내역</h5>
					</div>
					<div>
						<table class="table table-border table-stripe">
							<tr>
								<th>인원수</th>
								<td>${rezDto.rezCustomerCount} 명</td>
							</tr>
							<tr>
								<th>예약 일자</th>
								<td>${fn:substring(rezDto.clockSelect, 0, 10)}</td>
							</tr>
							<tr>
								<th>예약 시간</th>
								<td>${fn:substring(rezDto.clockSelect, 11, 16)}</td>
							</tr>
							<tr>
								<th>예약 메뉴</th>
								<td>${rezDto.menuName} (${rezDto.rezMenuQty}개)</td>
							</tr>
							<tr>
								<th>요청사항</th>
								<td>${rezDto.rezRequest}</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			
			
			<!-- 결제정보 -->
			<div class="col list-border mb-4 ms-4">
				<div>
					<h5 class="h-title">결제정보</h5>
				</div>
				<div>
					<table class="table table-border table-stripe">
						<tr>
							<th>예약 금액</th>
							<td>
								<fmt:formatNumber value="${rezDto.menuPrice}" pattern="#,##0"/> 원
							</td>
						</tr>
					</table>
				</div>
				<div>
					<table class="table table-border table-stripe">
						<tr>
							<th>포인트 사용</th>
							<td>
								/ 보유포인트 : pt
							</td>
						</tr>
					</table>
				</div>
				<div>
					<table class="table table-border table-stripe">
						<tr>
							<th>등급 페이백</th>
							<td>
								[어쩌고 수저 n%] <br>
								00원 적립
							</td>
						</tr>
					</table>
				</div>
				<div>
					<table class="table table-border table-stripe">
						<tr>
							<th>총 합계금액</th>
							<td>
								<fmt:formatNumber value="${rezDto.getTotal()}" pattern="#,##0"/> 원
							</td>
						</tr>
					</table>
				</div>
				<div>
					<table class="table table-border table-stripe">
						<tr>
							<th>결제방식</th>
							<td>
								카카페사진
							</td>
						</tr>
					</table>
				</div>
				<div >
					<input type="checkbox"> 동의하나요 <br>
					<input type="checkbox"> 동의하나요
				</div>
				<div>
					<button class="btn btn-warning w-100">결제하기</button>
				</div>
			</div>
			
			
		</div>
		
	</div>
</div>
		
		
		
							<th>좌석</th>
							<td>${rezDto.seatName}</td>
						
							<th>좌석수</th>
							<td>${rezDto.rezSeatQty}</td>
		
		

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>