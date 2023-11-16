<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script src="/js/cert.js"></script>


<form action="join" method="post" autocomplete="off">
	<div class="container form-border mt-30">
		<div class="center w-400">
		
		
		<br><br><br>
		
		<div class="row left">
			아이디 : <input type="text" name="customerId" placeholder="영문 소문자, 숫자 4~19자"
			class="form-input underline-input w-100 customer-input">
			<div class="success-feedback">올바른 아이디 형식입니다.</div>
			<div class="fail-feedback"> 아이디를 형식에 맞게 입력해주세요. </div>
			<div class="fail2-feedback"> 이미 사용중인 아이디입니다. </div>
			</div>
			
			<br>
		<div class="row left">
			비밀번호 : <input type="text" name="customerPw" placeholder="영문,숫자,특수문자 반드시 1개 이상 포함">
			<div class="success-feedback">올바른 비밀번호 형식입니다.</div>
			<div class="fail-feedback"> 비밀번호를 형식에 맞게 입력해주세요. </div>
			</div>
			
			<br>
		<div class="row left">
			이름 : <input type="text" name="customerName" placeholder="한글 2~7자 입력"
					 class="form-input underline-input w-100 customer-input">
			<div class="success-feedback">올바른 형식입니다.</div>
			<div class="fail-feedback"> 형식에 맞게 입력해주세요. </div>
			</div>
			
			<br>
		
			  <div class="row left">
            <label>이메일</label>
            <input type="text" name="customerEmail" placeholder="test@kh.com" 
            				class="form-input underline-input w-100 customer-input">
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

			
			<br>
		<div class="row left">
			연락처 : <input type="tel" name="customerContact" placeholder="010******** (-없이)">
			<div class="fail-feedback"> 전화번호 형식이 올바르지 않습니다.</div>
			</div>
			
			<br>
		<div class="row left">
			생년월일 : <input type="date" name="customerBirth">
			<div class="fail-feedback"> 형식을 다시 한번 확인해주세요.</div>
			</div>
			
			<br>
		<div class="row left">
			성별 : <input type="text" name="customerGender" placeholder="'남자' / '여자'로 기입해주세요.">
			<div class="success-feedback">올바른 형식입니다.</div>
			<div class="fail-feedback"> 형식에 맞게 입력해주세요. </div>
			</div>
			
			<br>
		</div>
		
		<div class="col">
			<button type="submit" class="btn btn-positive w-100 mb-40">
			가입하기
			</button>
		</div>
	</div>
</form>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>