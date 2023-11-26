<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script>
	function goToDetail(customerId) {
	    window.location = 'detail?customerId=' + customerId;
	}
</script>




<div class="row" style="margin-top: 2%;">
	<div class="col">



		<form>
			<div class="row justify-content-center">
				<div class="col-md-4">

					<br> <br>
					<h2>회원 관리</h2>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">회원아이디</span>
						</div>
						<input type="text" name="customerId" value="${vo.customerId}"
							class="form-control">
					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">회원이름</span>
						</div>
						<input type="text" name="customerName" value="${vo.customerName}"
							class="form-control">
					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">차단여부</span>
						</div>
						<select name="customerStatus" class="form-select">
							<option value="">선택</option>
							<option value="Y">Y</option>
							<option value="N">N</option>
						</select>
					</div>





					<div class="col text-center">
						<button class="btn btn-warning" type="submit">검색</button>
					</div>
				</div>
			</div>
		</form>


		<br> <br>

		<div class="row" style="margin-left: 10%; margin-right: 10%">
			<div class="col-md-6 mt-4">
				<h1 class="text-md-start text-center">
					<i class="fa-solid fa-circle-check" style="color: #ffb416;"></i>회원
					관리
				</h1>
			</div>
		</div>


		<!-- 회원 리스트 -->

		<div class="row justify-content-center">
			<div class="col-md-10 col-sm-12">
				<table class="table table-hover justify-content-center text-center">
					<thead>
						<tr class="table-warning">
							<th scope="col" class="col-md-2">회원이름</th>
							<th scope="col" class="col-md-3">회원아이디</th>
							<th scope="col" class="col-md-1">회원상태</th>
							<th scope="col" class="col-md-3">회원연락처</th>
							<th scope="col" class="col-md-3">회원상세</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="customerAdminListDto" items="${list}">
							<tr onClick="goToDetail(${customerAdminListDto.customerId})"
								style="cursor: pointer;">
								<td>${customerAdminListDto.customerName}</td>
								<th>${customerAdminListDto.customerId}</th>



								<td>${customerAdminListDto.customerStatus}</td>
								<td>${customerAdminListDto.customerContact}</td>
								<td><a
									href="/admin/customer/detail?customerId=${customerAdminListDto.customerId}"
									class="btn btn-primary btn-sm">상세보기</a></td>


							</tr>
						</c:forEach>
					</tbody>
				</table>
    <div class="col">
            <ul class="pagination justify-content-center">
               <!-- 이전 버튼 -->
				<c:if test="${!vo.first}">
				    <li class="page-item">
				        <a class="page-link" href="list?${vo.prevQueryString}" aria-label="Previous">
				            <span aria-hidden="true" style="color: #FFB416;">&laquo;</span>
				        </a>
				    </li>
				</c:if>
				
				<!-- 숫자 버튼 -->
				<c:forEach var="i" begin="${vo.begin}" end="${vo.end}" step="1">
				    <li class="page-item ${vo.page == i ? 'active' : ''}">
				        <c:choose>
				            <c:when test="${vo.page == i}">
				                <span class="page-link" style=" background-color: #FFB416; border-color:#FFB416" >${i}</span>
				            </c:when>
				            <c:otherwise>
				                <a class="page-link" href="list?${vo.getQueryString(i)}" style="color: #FFB416; ">${i}</a>
				            </c:otherwise>
				        </c:choose>
				    </li>
				</c:forEach>
				
				<!-- 다음 버튼 -->
				<c:if test="${!vo.last}">
				    <li class="page-item">
				        <a class="page-link" href="list?${vo.nextQueryString}" aria-label="Next">
				            <span aria-hidden="true" style="color: #FFB416;">&raquo;</span>
				        </a>
				    </li>
				</c:if>
            </ul>
        </div>
				</div>


			</div>

		</div>
	</div>







<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>