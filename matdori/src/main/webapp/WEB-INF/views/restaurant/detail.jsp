<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
.text-badge {
	font-weight: bold;
	color: black;
}

.list-border {
	border: 2px solid #ffb416;
	border-radius: 10px;
	padding: 20px;
}

.res-line {
	border-top: 2px solid #ffb416;
	border-bottom: 2px solid #ffb416;
	width: 100%;
}

.star-avg {
	font-weight: bold;
	color: black;
	font-size: 25px;
}

.main-images {
	max-width: 100%;
	height: auto;
	display: flex;
	justify-content: center;
	align-items: center;
}

.sub-images {
	max-width: 50%;
	height: auto;
	display: flex;
	justify-content: center;
	align-items: center;
}

.info-margin {
	margin-bottom: 40px;
	margin-top: 40px;
}

.under-line {
	text-decoration: none;
	color: black;
}

.box {
	width: 200px;
	height: 200px;
	border: 2px solid #000000;
}
</style>





<div class="container" id="homeSection">
	<div class="col">

		<!-- 제목 틀 -->
		<div class="row justify-content-center mb-3" style="margin-top: 70px;">
			<div class="col-6">
				<!-- 매장이름 -->
				<div class="row">
					<h1 class="text-left title">${resDto.resName}</h1>
				</div>
				<!-- 별점 -->
				<div class="row">
					<i class="fa-solid fa-star fa-2x" style="color: #ff2424;"> <span
						class="star-avg">별점평균추가</span>
					</i>
				</div>
			</div>


			<!-- 북마크 : 클릭하면 북마크 설정 / ajax로 값 전송 되게 구현할 예정 -->
			<div class="col-6" style="text-align: right;">
				<c:choose>
					<c:when test="${pickDto.resNo != null}">
						<i class="fa-solid fa-bookmark fa-3x bookmark"
							style="color: #ffb416;"></i>
						<br>
						<span></span>
					</c:when>
					<c:otherwise>
						<i class="fa-regular fa-bookmark fa-3x bookmark"
							style="color: #ffb416;"></i>
						<br>
						<span></span>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>

	<!-- 사진 틀-->
	<div class="row justify-content-center mb-3 mt-4">
		<!-- 메인 이미지 -->
		<div class="col-6">
			<img src="/images/dummy.png" class="main-images">
		</div>
		<!-- 서브 이미지 : 반복문? 써야하나 / 그리고 이미지 누르면 크게 띄우는 스크립트 추가 -->
		<div class="col-6">
			<div class="row mb-3">
				<img src="/images/dummy.png" class="sub-images"> <img
					src="/images/dummy.png" class="sub-images">
			</div>
			<div class="row">
				<img src="/images/dummy.png" class="sub-images"> <img
					src="/images/dummy.png" class="sub-images">
			</div>
		</div>
	</div>


	<!-- 예약 버튼 -->
	<div class="row justify-content-center mb-3 mt-4">
		<div class="row justify-content-center">
			<a href="/reservation/insert?rezResNo=${resDto.resNo}"
				class="btn btn-warning w-50 text-black"> 예약하기 </a>
		</div>
	</div>

	<!-- 시간버튼 : 없어질 수 있음 -->
	<div class="row justify-content-center mb-3 mt-4 text-center"
		style="margin-left: 50px; margin-right: 50px;">
		<div class="row justify-content-center">
			<!-- 이거 반복문으로 시간 선택하게 하기 가능한건 위에꺼 불가능한건 밑에꺼 disabled처리한거 쓰면됨-->
			<div class="col">
				<button class="btn btn-outline-warning">12 : 00</button>
			</div>
			<div class="col">
				<button class="btn btn-outline-secondary disabled">12 : 30</button>
			</div>
			<div class="col">
				<button class="btn btn-outline-warning">13 : 00</button>
			</div>
			<div class="col">
				<button class="btn btn-outline-warning">13 : 30</button>
			</div>
		</div>
	</div>


	<!-- 메뉴버튼 : 네비바로 사용함 -->
	<nav class="container-fluid navbar navbar-expand-lg bg-light mb-4"
		data-bs-theme="light" id="navbar">
		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav w-100">
				<li class="nav-item flex-fill text-center"><a
					class="nav-link fw-bold" href="#homeSection">홈</a></li>
				<li class="nav-item flex-fill text-center"><a
					class="nav-link fw-bold" href="#noticeSection">공지</a></li>
				<li class="nav-item flex-fill text-center"><a
					class="nav-link fw-bold" href="#menuSection">메뉴</a></li>
				<li class="nav-item flex-fill text-center"><a
					class="nav-link fw-bold" href="#reviewSection">리뷰</a></li>
			</ul>
		</div>
	</nav>


	<!-- 상세정보 -->
	<div
		class="row justify-content-center text-center list-border info-margin">
		<!-- 제목 -->
		<div class="row text-start">
			<h3>
				<span class="badge rounded-pill bg-warning text-black">상세정보</span>
			</h3>
		</div>
		<!-- 매장설명 -->
		<div class="col-6 text-start">
			<div class="row">매장위치 : ${resDto.resAddr1} ${resDto.resAddr2}</div>
			<div class="row">영업 시간 : ${resDto.resOpenTime}</div>
			<div class="row">매장번호 : ${resDto.resTel}</div>
		</div>
		<!-- 매장지도 : 지도 api 가져와서 대체 -->
		<div class="col-6 text-start">
			<img src="/images/dummy.png" class="sub-images">
		</div>
		<!-- 해시태그 : 이거도 해시태그 불러와서 forEach 써야함 -->
		<div class="row justify-content-center mt-4 text-center">
			<c:forEach var="hashDto" items="${resHashDto}">
				<div class="col">
					<h4>
					<span class="badge rounded-pill bg-light text-black">#
						${hashDto.hashComment}</span>
					</h4>
				</div>
			</c:forEach>

		</div>

	</div>



	<!-- 공지사항 -->
	<div
		class="row justify-content-center text-center list-border info-margin "
		id="noticeSection">
		<!-- 제목 -->
		<div class="row text-start">
			<h3>
				<span class="badge rounded-pill bg-warning text-black">공지사항</span>
			</h3>
		</div>
		<!-- 공지내용 -->
		<div class="row text-start">
			<div class="row">
				${resDto.resNotice} 공지사항 넣는 칸 모두 화이팅 !!! 여러분 힘내요 <br> 영국에서.. 온
				.. 편지<br> 프론트 프론트 <br>
			</div>
		</div>
	</div>



	<!-- 메뉴 -->
	<div
		class="row justify-content-center text-center list-border info-margin"
		id="menuSection">
		<!-- 제목 -->
		<div class="row text-start">
			<h3>
				<span class="badge rounded-pill bg-warning text-black">메뉴</span>
			</h3>
		</div>
		<!-- 메뉴리스트 : list [0]~[3] 번쨰만 꺼내야할듯 -->
		<div class="container">
			<div class="row">
				<!-- 첫 번째 : 나중에 반복문으로처리 / 위치 잡으려고 네개 해놓음 -->
				<div class="col-md-6">
					<div class="row">
						<div class="col-6">
							<img src="/images/dummy.png" class="sub-images">
						</div>
						<div class="col-6">
							<div class="row text-badge text-start">
								<h5 class="text-badge">메뉴이름</h5>
							</div>
							<div class="row">메뉴설명</div>
							<div class="row" style="color: red;">가격</div>
						</div>
					</div>
				</div>
				<!-- 두 번째 : 나중에 반복문으로처리 / 위치 잡으려고 네개 해놓음 -->
				<div class="col-md-6">
					<div class="row">
						<div class="col-6">
							<img src="/images/dummy.png" class="sub-images">
						</div>
						<div class="col-6">
							<div class="row text-badge text-start">
								<h5 class="text-badge">메뉴이름</h5>
							</div>
							<div class="row">메뉴설명</div>
							<div class="row" style="color: red;">가격</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<!-- 세 번째 : 나중에 반복문으로처리 / 위치 잡으려고 네개 해놓음 -->
				<div class="col-md-6">
					<div class="row">
						<div class="col-6">
							<img src="/images/dummy.png" class="sub-images">
						</div>
						<div class="col-6">
							<div class="row text-badge text-start">
								<h5 class="text-badge">메뉴이름</h5>
							</div>
							<div class="row">메뉴설명</div>
							<div class="row" style="color: red;">가격</div>
						</div>
					</div>
				</div>
				<!-- 네 번째 : 나중에 반복문으로처리 / 위치 잡으려고 네개 해놓음 -->
				<div class="col-md-6">
					<div class="row">
						<div class="col-6">
							<img src="/images/dummy.png" class="sub-images">
						</div>
						<div class="col-6">
							<div class="row text-badge text-start">
								<h5 class="text-badge">메뉴이름</h5>
							</div>
							<div class="row">메뉴설명</div>
							<div class="row" style="color: red;">가격</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 상세로 가는 a태그 -->
		<div class="row mt-4">
			<h5>
				<a href="" class="under-line text-badge"> 메뉴 더보기<i
					class="fa-solid fa-angles-right" style="color: #000000;"></i>
				</a>
			</h5>
		</div>
	</div>



	<!-- 리뷰 -->
	<div
		class="row justify-content-center text-center list-border info-margin"
		id="reviewSection">
		<!-- 제목 -->
		<div class="row text-start">
			<h3>
				<span class="badge rounded-pill bg-warning text-black">리뷰</span>
			</h3>
		</div>
		<!-- 리뷰  : 나중에 반복문으로 수정 / 스푼도... 이거 하드코딩 해야하나..?-->
		<div class="row text-start">
			<div class="row">
				<div class="col">
					<c:choose>
						<c:when test="${customerDto.customerLevel == '어쩌고수저'}">
							<i class="fa-solid fa-spoon" style="color: #b5b5b5;"></i>
						</c:when>
					</c:choose>
					${reviewDto.reviewWriter}
				</div>
				<div class="col">
					<c:choose>
						<c:when test="${reviewDto.reviewStarPoint == 1}">
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
							<i class="fa-regular fa-star" style="color: #ffb416;"></i>
							<i class="fa-regular fa-star" style="color: #ffb416;"></i>
							<i class="fa-regular fa-star" style="color: #ffb416;"></i>
							<i class="fa-regular fa-star" style="color: #ffb416;"></i>
						</c:when>
						<c:when test="${reviewDto.reviewStarPoint == 2}">
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
							<i class="fa-regular fa-star" style="color: #ffb416;"></i>
							<i class="fa-regular fa-star" style="color: #ffb416;"></i>
							<i class="fa-regular fa-star" style="color: #ffb416;"></i>
						</c:when>
						<c:when test="${reviewDto.reviewStarPoint == 3}">
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
							<i class="fa-regular fa-star" style="color: #ffb416;"></i>
							<i class="fa-regular fa-star" style="color: #ffb416;"></i>
						</c:when>
						<c:when test="${reviewDto.reviewStarPoint == 4}">
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
							<i class="fa-regular fa-star" style="color: #ffb416;"></i>
						</c:when>
						<c:when test="${reviewDto.reviewStarPoint == 5}">
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
							<i class="fa-solid fa-star" style="color: #ffb416;"></i>
						</c:when>
					</c:choose>
				</div>
				<div class="col text-end">( ${reivewDto.reviewWriteDate} 작성 )
				</div>
			</div>
			<div class="row">
				<div class="col-4">
					<img src="/images/dummy.png" class="sub-images">
				</div>
				<div class="col-8 text-start">${reviewDto.reviewContent}</div>
			</div>
		</div>

		<!-- 상세로 가는 a태그 -->
		<div class="row mt-4">
			<h5>
				<a href="" class="under-line text-badge"> 리뷰 더보기<i
					class="fa-solid fa-angles-right" style="color: #000000;"></i>
				</a>
			</h5>
		</div>
	</div>
	-----------------------------반복문 지금 못써서 냅둔 자료
	-------------------------------
	<div class="row">
		<c:forEach var="menuListByRes" items="${menuListByRes}">
			<div class="row">${menuListByRes.menuName}-
				${menuListByRes.menuPrice}원 - ${menuListByRes.menuContent}</div>
			<div class="row">
				<button class="btn btn-warning menuSelect"
					data-menu-no="${menuListByRes.menuNo}">선택</button>
			</div>
		</c:forEach>
	</div>
	<div class="row">
		<c:forEach var="reviewByRes" items="${reviewByRes}">
			<div class="row">${reviewByRes.reviewWriter}-
				${reviewByRes.reviewContent} - ${reviewByRes.reviewWriteDate} -
				${reviewByRes.reviewStarPoint}</div>
		</c:forEach>
	</div>


</div>
</div>



<script>
//메뉴 누르면 부드럽게 이동하는거
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();

        const targetSection = document.querySelector(this.getAttribute('href'));

        window.scrollTo({
            top: targetSection.offsetTop - window.innerHeight / 3, 
            behavior: 'smooth'
        });
    });
});

// 스크롤 해도 네비바 고정시키는거
window.addEventListener('scroll', function() {
    const navbar = document.getElementById('navbar'); 
    const scrollThreshold = 800; 

    if (window.scrollY > scrollThreshold) {
        navbar.style.position = 'fixed'; 
        navbar.style.top = '0';
        navbar.style.left = '50%'; 
        navbar.style.transform = 'translateX(-50%)';
    } else {
        navbar.style.position = 'relative'; 
        navbar.style.left = 'auto'; 
        navbar.style.transform = 'none'; 
    }
});

<!-- 메뉴 선택이다 임마 하 다 쓸모가 없어 졋구나 제기럴-->
// $(function(){
//     $(".menuSelect").click(function(e){
//     	e.preventDefault();
        
//         // 상품 번호 가져오기
//         var menuNo = $(this).data("menu-no");
        
//         $.ajax({
//             url: "/rest/reservation/add",
//             method: "post",
//             data: { menuNo: menuNo },
//             success: function(response) {
//                     $("#modalMessage").text(response.message);
// //                     openModal(); // 모달 열기

//             },
//             error: function (xhr) {
//                 // 에러 처리
//                 console.log(arguments);
//                 $("#modalMessage").text(xhr.responseJSON.message);
// //                 openModal(); // 모달 열기
//             },
            
//         });
//     });
//  });
</script>


<!-- 북마크 설정/해제를 위한 -->
<c:if test="${sessionScope.name != null}">
	<script>
        $(function() {
        	var params = new URLSearchParams(location.search);
        	var resNo = params.get("resNo");

        	/* console.log("resNo:", resNo); */

            $.ajax({
                url: "/rest/pick/check",
                method: "post",
                data: { resNo: resNo },
                success: function(response) {
                    if (response.check) {
                        $(".fa-bookmark").removeClass("fa-solid fa-regular").addClass("fa-solid");
                    } else {
                        $(".fa-bookmark").removeClass("fa-solid fa-regular").addClass("fa-regular");
                    }
    				//전달받은 찜 개수를 북마크 뒤의 span에 출력
                    $(".fa-bookmark").next("br").next("span").text(response.count + "명이 찜한 식당!");
                }
            });

            $(".fa-bookmark").click(function() {
            	console.log("북마크 클릭됨");
                $.ajax({
                    url: "/rest/pick/action",
                    method: "post",
                    data: { resNo: resNo },
                    success: function(response) {
                        if (response.check) {
                            $(".fa-bookmark").removeClass("fa-solid fa-regular").addClass("fa-solid");
                        } else {
                            $(".fa-bookmark").removeClass("fa-solid fa-regular").addClass("fa-regular");
                        }
        				//전달받은 찜 개수를 북마크 뒤의 span에 출력
                        $(".fa-bookmark").next("br").next("span").text(response.count + "명이 찜한 식당!");
                    }
                });
            });
        });
    </script>
</c:if>
















<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>