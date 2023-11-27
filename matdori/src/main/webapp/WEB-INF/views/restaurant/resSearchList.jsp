<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>



<div class="row" style="margin-top: 2%;">
	<div class="col">
		<!-- 검색 -->
		<form>
			<div class="row justify-content-center">
				<div class="col-md-4">

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">매장이름</span>
						</div>
						<input type="text" name="resName" value="${#.resName}"
							class="form-control">
					</div>
					
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">지역</span>
						</div>
						<input type="text" name="regionName" value="${#.regionName}"
							class="form-control">
					</div>
					
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">해시태그</span>
						</div>
						<input type="text" name="hashComment" value="${#.hashComment}"
							class="form-control">
					</div>
					
					


					<div class="col text-center">
						<button class="btn btn-warning" type="submit">검색</button>
					</div>
				</div>
			</div>
		</form>

	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>