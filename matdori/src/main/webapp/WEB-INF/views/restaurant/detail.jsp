<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container">
	<div class="col">
		<div class="row justify-content-center">
			<div class="col-md-6 mt-4">
				<h1 class="text-center ">
					<i class="fa-regular fa-credit-card" style="color: #ffb416;"></i>매장상세
				</h1>
			</div>
		</div>
		
		<div class="row justify-content-center text-center">
			<div class="row">
				<c:forEach var="resDetail" items="${resDetail}">
					<div class="row">
						${resDetail.menuName}
						-
						${resDetail.menuPrice}원
						-
						${resDetail.menuContent}
						
					</div>
				</c:forEach>
			</div>
			<div>
				
			</div>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>