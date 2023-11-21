<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container mt-5">
	<h1 class="mb-4">
		<i class="fa-solid fa-circle-check" style="color: #ffb416;"></i>사업자 관리
	</h1>
	<div class="table-responsive">
		<table class="table table-striped table-bordered">
			<thead class="thead-dark">
				<tr>
					<th>아이디</th>
					<th>사업자 명</th>
					<th>사업자 이메일</th>
					<th>차단 상태</th>
					<th>상세보기</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${businessBlockList}" var="businessBlock">
					<tr>
						<td>${businessBlock.busId}</td>
						<td>${businessBlock.busName}</td>
						<td>${businessBlock.busEmail}  </td>
						<td><c:choose>
								<c:when
									test="${businessBlock.busBlockStatus eq 'N' or empty businessBlock.busBlockStatus}">
									<span>차단 전</span>
								</c:when>
								<c:otherwise>
									<span style="color: red;">차단 됨</span>
								</c:otherwise>
							</c:choose></td>
						<td><a
							href="/admin/business/blockManager/details/${businessBlock.busId}"
							class="btn btn-primary btn-sm">상세보기</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
