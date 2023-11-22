<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container mt-5">

		<form>
			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">사업자아이디</span>
						</div>
						<input type="text" name="busId" value="${businessBlock.busId}"
							class="form-control">
					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">사업자이름</span>
						</div>
						<input type="text" name="busName" value="${businessBlock.busName}"
							class="form-control">
					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">차단여부</span>
						</div>
						<select name="busBlockStatus" class="form-select">
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
		
				<!-- 페이지네이션 -->
    <ul class="pagination justify-content-center">
        <!-- 이전 버튼 -->
        <c:if test="${!vo.first}">
            <li class="page-item">
                <a class="page-link" href="?${vo.prevQueryString}" aria-label="Previous">
                    <span aria-hidden="true" style="color: #FFB416;">&laquo;</span>
                </a>
            </li>
        </c:if>

        <!-- 숫자 버튼 -->
        <c:forEach var="i" begin="${vo.begin}" end="${vo.end}" step="1">
            <li class="page-item ${vo.page == i ? 'active' : ''}">
                <c:choose>
                    <c:when test="${vo.page == i}">
                        <span class="page-link" style="background-color: #FFB416; border-color: #FFB416">${i}</span>
                    </c:when>
                    <c:otherwise>
                        <a class="page-link" href="?${vo.getQueryString(i)}" style="color: #FFB416;">${i}</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </c:forEach>

        <!-- 다음 버튼 -->
        <c:if test="${!vo.last}">
            <li class="page-item">
                <a class="page-link" href="?${vo.nextQueryString}" aria-label="Next">
                    <span aria-hidden="true" style="color: #FFB416;">&raquo;</span>
                </a>
            </li>
        </c:if>
    </ul>
    
	</div>
</div>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
