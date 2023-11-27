package com.kh.matdori.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.ClockDao;
import com.kh.matdori.dao.HolidayDao;
import com.kh.matdori.dao.WorkdayDao;
import com.kh.matdori.dto.ClockDto;
import com.kh.matdori.dto.HolidayDto;
import com.kh.matdori.dto.WorkdayDto;
import com.kh.matdori.vo.ClockByResVO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/clock")
public class ClockRestController {

	@Autowired
	private ClockDao clockDao;
	
	@Autowired
	private WorkdayDao workdayDao;
	

	@PostMapping("/")
	public void insert(@RequestBody ClockDto clockDto) {
		clockDao.insert(clockDto);
	}
	 // 특정 Clock 데이터 조회
    @GetMapping("/clock/one/{clockNo}")
    public ClockDto getOneClock(@PathVariable int clockNo) {
        return clockDao.getOneClock(clockNo);
    }

    // 특정 매장의 모든 Clock 데이터 조회
    @GetMapping("/clock/list/{clockResNo}")
    public List<ClockDto> clockList(@PathVariable int clockResNo) {
        return clockDao.clockList(clockResNo);
    }

    // Workday 데이터 추가
    @PostMapping("/workday/add")
    public void addWorkday(@RequestBody WorkdayDto workdayDto) {
        clockDao.addWorkday(workdayDto);
    }


    // 매장별 특정 시간 선택
//    @GetMapping("/clock/selectOneAvailableTime/{resNo}/{selectedDate}")
//    public ResponseEntity<ClockByResVO> selectOneAvailableTime(@PathVariable int resNo, @PathVariable String selectedDate) {
//        ClockByResVO clockByResVO = clockDao.selectOneAvailableTime(resNo, selectedDate);
//        if (clockByResVO != null) {
//            return ResponseEntity.ok().body(clockByResVO);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}

