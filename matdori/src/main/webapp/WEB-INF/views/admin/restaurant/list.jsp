<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script>
	function goToDetail(resNo) {
	    window.location = 'detail?resNo=' + resNo;
	}
</script>

<div class="row" style="margin-top:2%;">
	<div class="col">
	
		
		<!-- 검색 -->
		<form>
		<div class="row justify-content-center">
			<div class="col-md-4">
			
			    <div class="input-group mb-3">
		            <div class="input-group-prepend">
		                <span class="input-group-text">매장이름</span>
		            </div>
		            <input type="text" name="resName" value="${vo.resName}" class="form-control">
	        	</div>
						
				<div class="input-group mb-3">
		            <div class="input-group-prepend">
		                <span class="input-group-text">사업자번호</span>
		            </div>
		           <input type="number" name="resRegNo" value="${vo.resRegNo}" class="form-control" placeholder="( - )제외 숫자만 입력">
	        	</div>
	        	
	        	<div class="input-group mb-3">
		            <div class="input-group-prepend">
		                <span class="input-group-text">심사상태</span>
		            </div>
		           <select name="resJudgeStatus"class="form-select">
						<option value="">선택</option>
						<option value="심사대기">심사대기</option>
						<option value="심사승인">심사승인</option>
						<option value="심사거절">심사거절</option>
					</select>
	        	</div>
	        	
				<div class="input-group mb-3">
		            <div class="input-group-prepend">
		                <span class="input-group-text">차단여부</span>
		            </div>
		        	<select name="resBlock" class="form-select">
						<option value="">선택</option>
						<option value="Y">Y(차단)</option>
						<option value="N">N</option>
					</select>
	        	</div>
				
				<div class="col text-center">
					<button class="btn btn-warning" type="submit">검색</button>
				</div>
			</div>
		</div>
		</form>
	
	
		<!-- 이름 -->
		<div class="row" style="margin-left : 10%; margin-right:10%">
			<div class="col-md-6 mt-4">
		        <h1 class="text-md-start text-center"> 
		            <i class="fa-solid fa-circle-check" style="color: #ffb416;"></i>매장 관리
		        </h1>
		    </div>
		</div>
	

		<!-- 테이블 -->
		<div class="row justify-content-center">
			<div class="col-md-10 col-sm-12">
				<table class="table table-hover justify-content-center text-center">
					<thead>
						<tr class="table-warning">
							<th scope="col" class="col-md-2">등록일자</th>
							<th scope="col" class="col-md-5">매장이름</th>
							<th scope="col" class="col-md-3">사업자 번호</th>
							<th scope="col" class="col-md-1">심사상태</th>
							<th scope="col" class="col-md-1">차단여부</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="restaurantAdminListDto" items="${list}">
					    <tr onClick="goToDetail(${restaurantAdminListDto.resNo})" style="cursor: pointer;">
					        <td>${restaurantAdminListDto.resRegDate}</td>
					        <th>${restaurantAdminListDto.resName}</th>
							<td>
								${fn:substring(restaurantAdminListDto.resRegNo, 0, 3)}
								-
								${fn:substring(restaurantAdminListDto.resRegNo, 3, 5)}
								-
								${fn:substring(restaurantAdminListDto.resRegNo, 5, 10)}
							</td>
					        <td>${restaurantAdminListDto.resJudgeStatus}</td>
					        <td>${restaurantAdminListDto.resBlock}</td>
					    </tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		
		
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



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>