<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="row">${reservationListDto.rezNo}</div>
<div class="row">${reservationListDto.customerId}</div>
<div class="row">${reservationListDto.resName}</div>
<div class="row">${reservationListDto.menuName}</div>
<div class="row">${reservationListDto.menuPrice}</div>
<div class="row">${reservationListDto.clockSelect}</div>
<div class="row">${reservationListDto.seatName}</div>
<div class="row">${reservationListDto.rezMenuQty}</div>
<div class="row">${reservationListDto.rezCustomerCount}</div>
<div class="row">${reservationListDto.rezSeatQty}</div>
<div class="row">${reservationListDto.getTotal()}</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>