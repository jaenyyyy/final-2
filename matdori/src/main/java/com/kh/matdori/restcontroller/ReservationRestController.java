//package com.kh.matdori.restcontroller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.kh.matdori.dao.ClockDao;
//import com.kh.matdori.dao.CustomerDao;
//import com.kh.matdori.dao.MenuDao;
//import com.kh.matdori.dao.ReservationDao;
//import com.kh.matdori.dao.RestaurantDao;
//import com.kh.matdori.dao.SeatDao;
//import com.kh.matdori.dto.ReservationDto;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/rest/reservation")
//public class ReservationRestController {
//	@Autowired
//	private ReservationDao reservationDao;
//	@Autowired
//	private RestaurantDao restaurantDao;
//	@Autowired
//	private MenuDao menuDao;
//	@Autowired
//	private CustomerDao customerDao;
//	@Autowired
//	private ClockDao clockDao;
//	@Autowired
//	private SeatDao seatDao;
//	
//	@PostMapping("/add")
//	public ResponseEntity<Map<String, Object>> addMenu(@RequestParam int menuNo,
//														HttpSession session){
//		Map<String, Object> response = new HashMap<>();
//		
//		String customerId = (String) session.getAttribute("name"); 
//		if (customerId == null) {
//	        response.put("loggedOut", true);
//	        response.put("message", "로그인이 필요합니다.");
//	        return ResponseEntity.status(401).body(response);
//	    }
//		
//		ReservationDto rezDto = new ReservationDto();
//		rezDto.setRezCustomerId(customerId);
//		rezDto.setRezMenuNo(menuNo);
//		reservationDao.insert(rezDto);
//
//		response.put("message", "메뉴가 성공적으로 추가되었습니다.");
//		return ResponseEntity.ok(response);
//	}
//	
//	
//
//}
