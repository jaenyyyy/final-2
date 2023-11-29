<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<style>
.row-top {
	margin-top: 20%;
}

.page-line {
	border-right: 3px solid #ffb416;
}

.bold {
	font-weight: bold;
}

.menu-tag {
	text-decoration: none;
	color: black;
}

.menu-tag:hover {
	color: #FFB416;
}

.res-line {
	border-top: 1px solid #ffb416;
	border-bottom: 1px solid #ffb416;
	width: 70%;
}

.rating {
	width: 100%;
}

.rating__star {
	cursor: pointer;
	color: #FFB416;
}
</style>

<div class="container">
	<div class="row" style="margin-top: 5%">

		<!-- 마이페이지 탭 -->
		<div class="col-3 page-line">
			<div class="row">

				<!--  제목  -->
				<div class="row mb-4">
					<h2 class="bold">
						<a class="menu-tag" href="mypage"> <i class="fa-solid fa-user"
							style="color: #ffb416;"></i> 마이페이지
						</a>
					</h2>
				</div>
				<br>
				<br>
				<br>	<br>
				<br>
				<br>
		
				<h4 class="bold">
					<a class="menu-tag" href="pick">북마크</a>
				</h4>
			</div>
			<div class="row row-top">
				<h4 class="bold">
					<a class="menu-tag" href="rezList">예약내역</a>
				</h4>
			</div>
			<div class="row row-top">
				<h4 class="bold">
					<a class="menu-tag" href="reviewListByCus">나의리뷰</a>
				</h4>
			</div>
		</div>


		<!-- 주 내용 -->
		<div class="col-9">
			<div class="row">
			 <br>  <br>
         <h4 class="bold"> 북마크  </h4>
				<c:forEach var="RestaurantDto" items="${pickList}">
					<div>
						<br>
					</div>
					<div class="row res-line p-4" style="margin-left: 100px;">
						<div class="col">
							<div class="row modal-title text-start">
							<h5 class="bold">
								${RestaurantDto.resName}
							</h5>
							</div>
							<div class="row ms-2">매장 번호 : ${RestaurantDto.resTel}</div>
							<div class="row ms-2">위치 : [${RestaurantDto.resPost}]  ${RestaurantDto.resAddr1} 
								${RestaurantDto.resAddr2}</div>
						</div>
						<div class="col-2 text-center">
							<div class="row mb-3">
								<a href="/restaurant/detail?resNo=${RestaurantDto.resNo}"
									class="menu-tag">매장상세 ></a>
							</div>
							<div class="row">
								<a class="btn btn-danger btn-sm open-modal-review delete-bookmark" data-resNo="${RestaurantDto.resNo}">삭제</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

	</div>
</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
    $(".delete-bookmark").on("click", function(e) {
        e.preventDefault();
        var resNo = $(this).data("resno");
        
        $.ajax({
            url: "/rest/pick/action",
            method: "post",
            data: { resNo: resNo },
            success: function(response) {
                // 성공적으로 삭제된 경우 UI에서 해당 항목을 삭제합니다.
                $(e.target).closest('.res-line').remove();
            },
            error: function(xhr, status, error) {
                // 실패한 경우 처리할 내용을 여기에 작성하세요.
            }
        });
    });
});
</script>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>