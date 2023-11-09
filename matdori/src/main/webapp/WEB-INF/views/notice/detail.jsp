<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
    
<div class="container w-800">
   <div class="row">
      <h1">공지사항</h1>
   </div>
   
   <div>

      
      <div>
      <h4>${noticeDto.noticeTitle}</h4>
      <hr class="w-100 hr-style">
      </div>
      <div>
         <span>${noticeDto.noticeWriteDate}</span>
      </div>
      
      <div>
         <span>${noticeDto.noticeContent}</span>
      </div>
      
   </div>
   
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>