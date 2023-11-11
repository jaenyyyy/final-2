<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>matdori</title>
         <!-- 아이콘 사용을 위한 Font Awesome 6 CDN -->
    <link rel="stylesheet" type="text/css"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

    <!-- 구글 웹 폰트 사용을 위한 CDN -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
     
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/5.3.2/united/bootstrap.min.css" rel="stylesheet">

</head>



<div class="container-fluid">

	<!-- 헤더 위 -->
    <div class="row mt-3">

    	<div class="col ms-4" style="margin-top: 70px;"> 
            <a href="#" class="btn btn-outline-warning">
       			북마크
      		</a>
        </div>
        <div class="col text-center">
           <img src="/images/logo.png" style="width:250px;">
        </div>
        <div class="col text-end me-4" style="margin-top: 70px;">
        	<a href="#" class="btn btn-outline-warning me-4">
				사업체
	      	</a>
        	<c:choose>
	        	<c:when test="${sessionScope.name != null}">
	        		<a href="http://localhost:8080/customer/logout" class="btn btn-outline-warning"> 
	        			로그아웃
	        		</a>
	        	</c:when>
	            <c:otherwise>
	           		<a href="http://localhost:8080/customer/login" class="btn btn-outline-warning">
		           		로그인
	           		</a>
	            </c:otherwise>
            </c:choose>
        </div>
     </div>
        
        
        
	<!-- 메뉴바 -->
	<div class="row">
        <div class="col-md-12 offset mt-4">
			<nav class="navbar navbar-expand-lg bg-warning" data-bs-theme="light">
			  <div class="container-fluid">
			    <a class="navbar-brand" href="/">맛도리</a>
			    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
			      <span class="navbar-toggler-icon"></span>
			    </button>
			    <div class="collapse navbar-collapse" id="navbarColor03">
			      <ul class="navbar-nav me-auto">

			        <li class="nav-item">
			          <a class="nav-link" href="#">메뉴1</a>
			        </li>
			        <li class="nav-item">
			          <a class="nav-link" href="#">메뉴2</a>
			        </li>
			        <li class="nav-item">
			          <a class="nav-link" href="/notice/list">공지사항</a>
			        </li>
			        <li class="nav-item">
			          <a class="nav-link" href="/qna/list">Q&A</a>
			        </li>
			        <li class="nav-item">
			          <a class="nav-link" href="/customer/mypage">마이페이지</a>
			        </li>
			        <li class="nav-item dropdown">
			          <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>
			          <div class="dropdown-menu">
			            <a class="dropdown-item" href="#">Action</a>
			            <a class="dropdown-item" href="#">Another action</a>
			            <a class="dropdown-item" href="#">Something else here</a>
			            <div class="dropdown-divider"></div>
			            <a class="dropdown-item" href="#">Separated link</a>
			          </div>
			        </li>
			      </ul>
			      <form class="d-flex">
			        <input class="form-control me-sm-2" type="search" placeholder="검색어를 입력하세요">
			        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
			      </form>
			    </div>
			  </div>
			</nav>

        </div>
    </div>
    
    
    
</div>

 <section>