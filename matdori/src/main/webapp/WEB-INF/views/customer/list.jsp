<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script>
	function goToDetail(customerId) {
	    window.location = 'detail?customerId=' + customerId;
	}
</script>


		<h1>회원 관리</h1>
	<div class="row justify-content-center">
			<div class="col-md-10 col-sm-12">
				<table class="table table-hover justify-content-center text-center">
					<thead>
						<tr class="table-warning">
							<th scope="col" class="col-md-2">회원아이디</th>
					
							<th scope="col" class="col-md-3">회원이름</th>
							<th scope="col" class="col-md-1">회원상태</th>
						
						</tr>
					</thead>
					<tbody>
						<c:forEach var="customerAdminList" items="${list}">
					    <tr onClick="goToDetail(${customerAdminListDto.customerId})" style="cursor: pointer;">
					      
					        <th>${customerAdminListDto.customerName}</th>
							
					        <td>${customerAdminListDto.customerStatus}</td>
					      
					    </tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>