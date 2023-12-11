<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script>
window.contextPath = "${pageContext.request.contextPath}";
    function goToDetail(noticeNo) {
        window.location = window.contextPath+'detail?noticeNo=' + noticeNo;
    }
</script>

<div class="row" style="margin-top:2%;">
    <div class="col">
    
    
		<div class="row" style="margin-left: 10%; margin-right: 10%;">
		    <div class="col-md-6 mt-4">
		        <h1 class="text-md-start text-center"> 
		            <i class="fa-solid fa-bullhorn" style="color: #ffb416;"></i>
		             <span
					style="font-weight: bold;"> 공지사항</span>
		        </h1>
		        <br><br>
		    </div>
		    
		    <c:if test="${sessionScope.name != null && sessionScope.level == '관리자'}">
		    <div class="col-md-6 mt-4 text-md-end text-center"> 
		        <a href="write" class="btn btn-warning">작성</a>
		    </div>
			</c:if>
		</div>
	
	<div class="row justify-content-center"> 
    	<div class="col-md-10 col-sm-12">
	    <table class="table table-hover justify-content-center text-center">
            <thead>
                <tr class="table-warning">
                    <th scope="col" class="col-md-2">글번호</th>
                    <th scope="col" class="col-md-6">제목</th>
                    <th scope="col" class="col-md-3">작성일</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="noticeDto" items="${list}">
                    <tr onclick="goToDetail(${noticeDto.noticeNo})" style="cursor: pointer;">
                        <td>${noticeDto.noticeNo}</td>
                        <td>${noticeDto.noticeTitle}</td>
                        <td>${noticeDto.noticeWriteDate}</td>
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

        <!-- 검색창 -->
        <div class="col">
		<form action="list" method="get">
		    <div class="row justify-content-center">
		        <div class="col-md-3">
		             <div class="input-group mb-3">
				      <input type="search" name="keyword" class="form-control" placeholder="검색어를 입력해주세요"required>
				      <button type="submit" class="btn btn-warning" type="button" id="button-addon2">검색</button>
				    </div>
		        </div>
		    </div>
		</form>
        </div>
		
		
    </div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
