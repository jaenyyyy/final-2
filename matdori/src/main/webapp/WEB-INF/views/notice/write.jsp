<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
 
<form action="write" method="post" autocomplete="off">
<div class="container w-600">
   
   <div>
      <input type="text" name="noticeTitle" placeholder="제목을 입력해주세요.">
   </div>
   <div>
      <textarea name="noticeContent"rows="15" style="resize:none;" placeholder="내용을 입력해주세요."></textarea>

   </div>
   <div class="right mt-10">
      <button>작성하기</button>
   </div>
</div>
</form>

 <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

