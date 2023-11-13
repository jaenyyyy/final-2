<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script src="/js/cert.js"></script>

<style>
    .loading-icon {
        display: none;
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        z-index: 9999;
    }
    
    .loading-icon img {
        width: 50px; /* 아이콘의 너비 조절 */
    }
</style>

<div class="container w-300">
    <div class="row">
        <h1>비밀번호 찾기</h1>
    </div>

    <div id="step1">
        <div class="row left">
            <label>이메일</label>
            <input type="text" name="customerEmail" placeholder="test@kh.com" class="form-input underline-input w-100 customer-input">
            <div class="fail-feedback">이메일 형식이 잘못되었습니다</div>
            <div class="fail2-feedback">이미 이 이메일로 아이디를 만드셨습니다</div>
        </div>

        <div class="row flex-container">
            <div class="w-50">
                <button type="button" class="btn btn-send btn-positive" onclick="sendCertNumber()">
                    <i class="fa-solid fa-spinner fa-spin"></i>
                    <span>인증번호 보내기</span>
                </button>
            </div>

            <div class="cert-wrapper right">
                <input type="text" class="form-input underline-input customer-input cert-input w-50">
                <button type="button" class="btn btn-cert btn-positive" onclick="checkCertNumber()">확인</button>
                
                <div class="fail2-feedback">인증번호를 입력해주세요</div>
                <div class="fail-feedback">인증번호 잘못입력하셨습니다</div>
                <div class="success-feedback">성공!</div>
            </div>
        </div>
    </div>
    
    
    <div id="step2" style="display: none;">
        <!-- 비밀번호 변경 폼 -->
        <form action="findPw" method="post">
            <div class="row left">
                <label>새로운 비밀번호</label>
                <input type="password" name="newPassword" class="form-input underline-input w-100 customer-input">
            </div>

            <div class="row left">
                <label>비밀번호 확인</label>
                <input type="password" name="confirmPassword" class="form-input underline-input w-100 customer-input">
            </div>

            <div class="row">
                <button type="submit" class="btn btn-positive w-100 select">비밀번호 변경</button>
            </div>
        </form>
    </div>

    <div id="loading-icon" class="loading-icon">
        <i class="fa-solid fa-spinner fa-spin fa-6x black"></i>
    </div>
</div>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
