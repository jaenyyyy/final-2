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

					<div class="input-group mb-3 col-md-4">
						<div class="input-group-prepend">
							<span class="input-group-text">매장이름</span>
						</div>
						<input type="text" name="resName" value="${vo.resName}"
							class="form-control">

						<div class="input-group-prepend">
							<span class="input-group-text">지역</span>
						</div>
							<select name="regionNme" class="form-select">
								<option value="">지역을 선택해주세요</option>
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
							<span class="input-group-text">해시태그</span>
						</div>
						<input type="text" name="hashComment" value="${vo.hashComment}"
							class="form-control">
					</div>

					<div class="col text-center">
						<button class="btn btn-warning" type="submit">검색</button>
					</div>
				</div>
			</div>
		</form>
		
		
		<!-- list반환 -->
		<!-- 테이블 -->
		<div class="row justify-content-center">
			<div class="col-md-10 col-sm-12">
				<table class="table table-hover justify-content-center text-center">
					<thead>
						<tr class="table-warning">
							<th scope="col" class="col-md-2">매장이름</th>
							<th scope="col" class="col-md-5">매장위치</th>
							<th scope="col" class="col-md-3">해시태그</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="resSearchList" items="${resSearchList}">
						
						<div class="row">
							<div class="col">
								${resSearchList.resName}
							</div>
						</div>
						
						
						
						
					    <tr>
					        <td>${resSearchList.resName}</td>
					        <td>${resSearchList.regionName}</td>
					        <td>${resSearchList.hashComment}</td>
					    </tr>
					</c:forEach>
					</tbody>
				</table>

	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>