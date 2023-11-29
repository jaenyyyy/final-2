<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<!-- <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script> 
<script>
$(function() {
    $("[name=selectedDate]").on("change", function(e) {
        var selectedDate = $(this).val();
        var rezResNo = $("[name=rezResNo]").val();
        var workdayNo = workdayVO.workdayNo;

        $.ajax({
            url: "http://localhost:8080/rest/reservation/checkDate/"+ workdayNo,
            method: "post",
            data: {
                selectedDate: selectedDate,
                rezResNo: rezResNo  // 예약에 관련된 resNo 값 추가 (원하는 값으로 변경)
            },
            success: function(response) {
                console.log("서버 응답:", response);  // 서버 응답을 콘솔에 출력 (디버깅 용도)

                if (response === "Y") {
                    console.log("영업 가능한 날짜입니다.");
                    showSelect();  // select 요소를 보이게 함
                } else if (response === "N") {
                    console.log("영업 불가능한 날짜입니다.");
                    hideSelect();  // select 요소를 숨김
                } else {
                    console.log("서버 응답이 올바르지 않습니다.");
                }
            },
            error: function() {
                console.error("영업 시간 확인 중 오류가 발생했습니다.");
            }
        });
    });

    // 선택한 날짜에 따라 select 요소를 보이게 또는 숨기는 함수
    function showSelect() {
        $("[name=selectedClock]").show();
    }

    function hideSelect() {
        $("[name=selectedClock]").hide();
    }
});


</script>-->

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    $(document).ready(function() {
    	
        // 좌석이나 인원수가 변경될 때 이벤트 리스너 추가
        $("#selectedSeat, #rezCustomerCount").change(function() {
            // 선택된 좌석의 가용인원 문자열을 가져옴
            var nameString = $("#selectedSeat").find(":selected").data("name");

            // 가용인원 문자열에서 숫자만 추출
            var name = parseInt(nameString.match(/\d+/)[0]);

            // 현재 입력된 인원수를 가져옴
            var rezCustomerCount = $("#rezCustomerCount").val();

            // 가용인원수를 초과하는지 확인
            if (parseInt(rezCustomerCount) > name) {
                $("#warningMessage").text("인원수가 가용인원수를 초과합니다.");
            } else {
                $("#warningMessage").text("");
            }

            // 추출된 숫자를 출력하거나 다른 용도로 사용할 수 있음
            console.log("가용인원수 숫자: " + name);
        });
    });
</script>



<div class="row" style="margin-top:2%;">
	<div class="col">
		<form action="insert" method="post" >
		<div class="row justify-content-center">
			<div class="card mb-3" style="max-width:50rem;">
				<div class="card-body">
<%-- 					<input type="hidden" name="rezNo" value="${ReservationDto.rezNo}"> --%>
<!-- 					<label class="form-label mt-4" for="readOnlyInput">아이디</label> -->
<%-- 	    			<input type="hidden" name="rezCustomerId" value="${rezCustomerId}"> --%>
<!-- 					<label class="form-label mt-4" for="readOnlyInput">매장</label> -->
	    			<input type="hidden" name="rezResNo" value="${rezResNo}">
	    			
					<label class="form-label mt-4" for="readOnlyInput">시간</label>
<!-- 					<input type="date" name="selectedDate"> -->
	    			<select class="form-control" name="selectedClock">
				        <option>선택하세요</option>
				        <c:forEach var="clockDto" items="${clockList}">				        	
				            <option value="${clockDto.clockNo}">
				            <fmt:formatDate value="${clockDto.clockSelect}" pattern="yyyy-MM-dd HH:mm"/>
				            </option>
				        </c:forEach>
				   	</select>
	    			
					<label class="form-label mt-4" for="readOnlyInput">좌석</label>
					<select class="form-control" name="selectedSeat" id="selectedSeat">
					    <option>선택하세요</option>
					    <c:forEach var="seatDto" items="${seatList}">
					        <option value="${seatDto.seatNo}" data-name="${seatDto.seatName}">
					            ${seatDto.seatName}
					        </option>
					    </c:forEach>
					</select>
					
					<label class="form-label mt-4" for="readOnlyInput">인원수</label>
					<input class="form-control" type="number" name="rezCustomerCount" id="rezCustomerCount">
					
					<div id="warningMessage" style="color: red;"></div>
	    			
<!-- 					<label class="form-label mt-4" for="readOnlyInput">좌석수</label> -->
<!-- 	    			<input class="form-control" type="number" name="rezSeatQty"> -->
	    			
					<label class="form-label mt-4" for="readOnlyInput">메뉴</label><br>
			        <c:forEach var="MenuWithImagesVO" items="${menuList}">
			            <label>
			                <input type="checkbox" name="selectedMenus"
			                 value="${MenuWithImagesVO.menuNo}">
			                ${MenuWithImagesVO.menuName} (가격 : ${MenuWithImagesVO.menuPrice})
			            </label><br>
						<label class="form-label mt-1" for="readOnlyInput">수량</label>
		    			<input class="form-control" type="number" name="selectedQtys">
			            <br>
			        </c:forEach>
	    			
	    			
					<label class="form-label mt-4" for="readOnlyInput">요청사항</label>
	    			<input class="form-control" type="text" name="rezRequest">
	    			
					<button class="btn btn-warning w-100 mt-4" type="submit">예약하기</button>
				</div>
			</div>
		</div>
			
		</form>
	
	</div>
	</div>
    
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>