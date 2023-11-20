<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container mt-5">
	<h1 class="mb-4">
		<i class="fa-solid fa-circle-check" style="color: #ffb416;"></i>사업자 관리
	</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>아이디</th>
				<th>사업자 명</th>
				<th>사업자 이메일</th>
				<th>차단상태</th>
				<th>차단하기</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${businessBlockList}" var="businessBlock">
				<tr>
					<td>${businessBlock.busId}</td>
					<td>${businessBlock.busName}</td>
					<td>${businessBlock.busEmail}</td>
					<c:choose>
						<c:when
							test="${empty businessBlock.busBlockStatus or businessBlock.busBlockStatus eq 'N'}">
							<td>차단전</td>
						</c:when>
						<c:otherwise>
							<td>${businessBlock.busBlockStatus}</td>
						</c:otherwise>
					</c:choose>

					<td><a href="/admin/business/blockManager/details/${businessBlock.busId}">상세보기</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
