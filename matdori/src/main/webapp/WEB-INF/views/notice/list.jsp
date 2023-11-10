<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
 
 
 <div class="row">
	 <div class="col text-center"> 
	 	<div>
			<h1>공지사항</h1>
			<a href="write" class="btn btn-warning">작성</a>
	 	</div>
	 	
		 <table class="table table-hover">
		 
		  <thead>
		    <tr class="table-warning">
		      <th scope="col">글번호</th>
		      <th scope="col">제목</th>
		      <th scope="col">작성일</th>
		    </tr>
		  </thead>
		  
		  <tbody>
		  <c:forEach var="noticeDto" items="${list}">
		    <tr>
		      <th scope="row">${noticeDto.noticeNo}</th>
		      <td>
		      	<a href="detail?noticeNo=${noticeDto.noticeNo}">
			      	${noticeDto.noticeTitle}
		      	</a>
		      </td>
		      <td>${noticeDto.noticeWriteDate}</td>
		    </tr>
		    </c:forEach>
		  </tbody>
		  
		</table>
		
		
		
		
		<div>
		  <ul class="pagination">
		    <!-- 이전 버튼 -->
		    <c:if test="${!vo.first}">
		      <li class="page-item">
		        <a class="page-link" href="list?${vo.prevQueryString}" aria-label="Previous">
		          <span aria-hidden="true">&laquo;</span>
		        </a>
		      </li>
		    </c:if>
		
		    <!-- 숫자 버튼 -->
		    <c:forEach var="i" begin="${vo.begin}" end="${vo.end}" step="1">
		      <li class="page-item ${vo.page == i ? 'active' : ''}">
		        <c:choose>
		          <c:when test="${vo.page == i}">
		            <span class="page-link">${i}</span>
		          </c:when>
		          <c:otherwise>
		            <a class="page-link" href="list?${vo.getQueryString(i)}">${i}</a>
		          </c:otherwise>
		        </c:choose>
		      </li>
		    </c:forEach>
		
		    <!-- 다음 버튼 -->
		    <c:if test="${!vo.last}">
		      <li class="page-item">
		        <a class="page-link" href="list?${vo.nextQueryString}" aria-label="Next">
		          <span aria-hidden="true">&raquo;</span>
		        </a>
		      </li>
		    </c:if>
		  </ul>
		</div>
		
	 </div>
 </div>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>