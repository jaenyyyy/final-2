<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<script>
	function goToDetail(resNo) {
	    window.location = '/restaurant/detail?resNo=' + resNo;
	}
</script>



<div class="row" style="margin-top: 2%;">
	<div class="col">
		<!-- 검색 -->
		<form>

			<div class="row justify-content-center">
				<div class="col-md-5 border border-warning p-4">


					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">지역</span>
						</div>
						<select name="regionName" class="form-select">
							<option value="">지역선택</option>
							<option value="강남구">강남구</option>
							<option value="강동구">강동구</option>
							<option value="강북구">강북구</option>
							<option value="강서구">강서구</option>
							<option value="관악구">관악구</option>
							<option value="광진구">광진구</option>
							<option value="구로구">구로구</option>
							<option value="금천구">금천구</option>
							<option value="노원구">노원구</option>
							<option value="도봉구">도봉구</option>
							<option value="동대문구">동대문구</option>
							<option value="동작구">동작구</option>
							<option value="마포구">마포구</option>
							<option value="서대문구">서대문구</option>
							<option value="서초구">서초구</option>
							<option value="성동구">성동구</option>
							<option value="성북구">성북구</option>
							<option value="송파구">송파구</option>
							<option value="양천구">양천구</option>
							<option value="영등포구">영등포구</option>
							<option value="용산구">용산구</option>
							<option value="은평구">은평구</option>
							<option value="종로구">종로구</option>
							<option value="중구">중구</option>
							<option value="중랑구">중랑구</option>
						</select>

						<div class="input-group-prepend">
							<span class="input-group-text">매장이름</span>
						</div>
						<input type="text" name="resName" value="${vo.resName}"
							class="form-control">

						<div class="input-group-prepend">
							<span class="input-group-text">키워드</span>
						</div>
						<input type="text" name="hashComment" value="${vo.hashComment}"
							class="form-control">


					</div>





					<div class="row mt-4">
						<div class="col text-center">
							<button class="btn btn-warning" type="submit">검색</button>
						</div>
					</div>
				</div>
			</div>
		</form>


		<!-- list반환 -->
		<!-- 테이블 -->
		<div class="row justify-content-center mt-4">
			<div class="col-md-5 col-sm-12">
				<c:choose>
					<c:when test="${empty resSearchList}">
						<div class="row mt-4 text-center"></div>
						<h5 class="text-center">
							<i class="fa-solid fa-magnifying-glass" style="color: #ffb416;"></i>
							검색 결과가 없습니다. 검색어를 입력해주세요.
						</h5>
					</c:when>
					<c:otherwise>
						<c:forEach var="resSearchList" items="${resSearchList}">
							<div class="row border border-warning mt-4"
								onClick="goToDetail(${resSearchList.resNo})"
								style="cursor: pointer;">
								<div class="col-3 p-4">
									<img src="/image/restaurant/image/first/${resSearchList.resNo}" alt="Restaurant Image" style="width:100px; height:100px;">
								</div>
								<div class="col-4 p-4">
									<h5>${resSearchList.resName}</h5>
									<span>${resSearchList.regionName}</span>
								</div>
								<div class="col-4 text-end p-4">
									<span>매장상세보기 ></span>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>



	</div>
</div>





<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>