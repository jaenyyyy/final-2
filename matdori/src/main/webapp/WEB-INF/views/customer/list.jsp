<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script>
	
	function goToDetail(customerId) {
	    console.log("customerId:", customerId);
	    window.location = 'detail?customerId=' + customerId;
	}
	
	 
	 function manageBlock(customerId, blockStatus) {
		    const blockSpan = document.getElementById(`blockStatus_${customerId}`);
		    if (blockStatus === 'Y') {
		        fetch(`/rest/customer/cancle?customerId=${customerId}`)
		            .then(response => response.text())
		            .then(data => {
		                if (data === 'success') {
		                    blockSpan.innerText = 'N'; // 차단 버튼 클릭 시 해제 상태로 변경
		                } else {
		                    console.error('해제 실패');
		                }
		            })
		            .catch(error => console.error('해제 오류:', error));
		    } else {
		        fetch(`/rest/customer/block?customerId=${customerId}`)
		            .then(response => response.text())
		            .then(data => {
		                if (data === 'success') {
		                    blockSpan.innerText = 'Y'; // 해제 버튼 클릭 시 차단 상태로 변경
		                } else {
		                    console.error('차단 실패');
		                }
		            })
		            .catch(error => console.error('차단 오류:', error));
		    }
		}



</script>

<div class="row" style="margin-top:2%;">
	<div class="col">
	


<form>
	<div class="row justify-content-center">
		<div class="col-md-4">
		
		<br><br>
		<h2> 회원 관리 </h2>

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
				<select name="customerBlock" class="form-select">
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


<br><br>

<div class="row" style="margin-left : 10%; margin-right:10%">
			<div class="col-md-6 mt-4">
		        <h1 class="text-md-start text-center"> 
		            <i class="fa-solid fa-circle-check" style="color: #ffb416;"></i>회원 관리
		        </h1>
		    </div>
		</div>
	
	

<div class="row justify-content-center">
	<div class="col-md-10 col-sm-12">
		<table class="table table-hover justify-content-center text-center">
			<thead>
				<tr class="table-warning">
					<th scope="col" class="col-md-2">회원아이디</th>

					<th scope="col" class="col-md-2">회원이름</th>

					<th scope="col" class="col-md-1">회원상태</th>

					<th scope="col" class="col-md-3">회원연락처</th>
					
					<th scope="col" class="col-md-3">회원차단</th>
				

				</tr>
			</thead>
			<tbody>
				<c:forEach var="customerAdminListDto" items="${list}">
    <tr>
        <td>${customerAdminListDto.customerId}</td>
        <td>${customerAdminListDto.customerName}</td>
        <td>${customerAdminListDto.customerStatus}</td>
        <td>${customerAdminListDto.customerContact}</td>
        <td>
           <a class="link" onclick="manageBlock('${customerAdminListDto.customerId}', '${customerAdminListDto.customerBlock}')">
                    <span id="blockStatus_${customerAdminListDto.customerId}">
                        <c:choose>
                            <c:when test="${customerAdminListDto.customerBlock == 'Y'}">해제</c:when>
                            <c:otherwise>차단</c:otherwise>
                        </c:choose>
                    </span>
                </a>
        </td>
    </tr>
</c:forEach>

			</tbody>
		</table>
	</div>
</div>
</div>
</div>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>