<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<div class="container mt-5 row">
	<div>
		<h1><i class="fa-solid fa-circle-check" style="color: #ffb416;"></i>매장 관리</h1>
	</div>
	<div class="col">
		<table class="table">
			<thead>
				<tr>
					<th>등록일자</th>
					<th>매장이름</th>
					<th>사업자 번호</th>
					<th>심사상태</th>
					<th>차단여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="restaurantAdminListDto" items="${list}">
			    <tr>
			        <td>${restaurantAdminListDto.resRegDate}</td>
			        <td>${restaurantAdminListDto.resName}</td>
			        <td>${restaurantAdminListDto.resRegNo}</td>
			        <td>${restaurantAdminListDto.resJudgeStatus}</td>
			        <td>${restaurantAdminListDto.resBlock}</td>
			    </tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>






<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>