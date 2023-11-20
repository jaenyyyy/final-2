<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container mt-5">
    <h1 class="mb-4"><i class="fa-solid fa-circle-check" style="color: #ffb416;"></i>사업자 등록 심사 리스트</h1>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>신청일자</th>
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
                    <td>
                    	<fmt:formatDate value="${businessJudge.busJudgeDate}" pattern="yyyy-MM-dd" />
                    </td>
                    <td>${businessJudge.busId}</td>
                    <td>${businessJudge.busName}</td>
                    <td>${businessJudge.busRegNo}</td>
                    <td>${businessJudge.busJudgeStatus}</td>
                    <td>
                        <fmt:formatDate value="${businessJudge.busJudgeDate}" pattern="yyyy-MM-dd" />
                    </td>
                    <td><a href="/admin/business/blockManager/details/${businessBlock.busId}">상세보기</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
