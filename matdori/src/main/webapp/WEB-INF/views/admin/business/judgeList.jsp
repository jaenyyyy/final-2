<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container mt-5">
	<h1 class="mb-4">
		<i class="fa-solid fa-circle-check" style="color: #ffb416;"></i>사업자 등록
		심사 리스트
	</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>아이디</th>
				<th>사업자 명</th>
				<th>사업자 등록 번호</th>
				<th>심사상태</th>
				<th>심사일자</th>
				<th>심사하기</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${businessJudgeList}" var="businessJudge">
				<tr>
					<td>${businessJudge.busId}</td>
					<td>${businessJudge.busName}</td>
					<td>${businessJudge.busRegNo}</td>
					<c:choose>
						<c:when test="${empty businessJudge.busJudgeStatus}">
							<td>심사 전</td>
						</c:when>
						<c:otherwise>
							<td>${businessJudge.busJudgeStatus}</td>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${empty businessJudge.busJudgeDate}">
							<td>심사 전</td>
						</c:when>
						<c:otherwise>
							<td><fmt:formatDate value="${businessJudge.busJudgeDate}"
									pattern="yyyy-MM-dd" /></td>
						</c:otherwise>
					</c:choose>

					<td><a href="${pageContext.request.contextPath}/admin/business/details/${businessJudge.busId}">상세보기</a></td>
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

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>