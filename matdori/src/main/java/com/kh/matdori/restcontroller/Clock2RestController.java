package com.kh.matdori.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.Clock2Dao;
import com.kh.matdori.dto.Clock2Dto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/clock2")
public class Clock2RestController {

	@Autowired
	private Clock2Dao clock2Dao;
	
//	@GetMapping("/list")
//	public List<Clock2Dto> clock2List(){
//	    return clock2Dao.clock2List();
//	@GetMapping("/list")
//	public List<Clock2Dto> clock2List() {
//	    List<Clock2Dto> list = clock2Dao.clock2List();
//	    // 로그에 결과 출력
//	    list.forEach(item -> System.out.println(item.getClock2No() + " " + item.getClock2_time()));
//	    return list;
//	}
	
	@PostMapping("/insert")
	public void insert(@RequestBody Clock2Dto clock2Dto) {
		int  no = clock2Dao.sequence();
		clock2Dto.setClock2No(no);
		log.debug("dto = {}",clock2Dto);
		
		clock2Dao.insertClock2(clock2Dto);
	}
	
	@DeleteMapping("/delete/{clock2No}")
	public void delete (@PathVariable int clock2No) {
		clock2Dao.deleteClock2(clock2No);
	}
	
	@GetMapping("/resList/{clock2ResNo}")
	public List<Clock2Dto> resClock2List(@PathVariable int clock2ResNo){
		return clock2Dao.clock2List(clock2ResNo);
	}
	@GetMapping("/{clock2No}")
    public ResponseEntity<Clock2Dto> getClock2ByNo(@PathVariable int clock2No) {
        Clock2Dto clock2Dto = clock2Dao.selectOneClock2(clock2No);
        if (clock2Dto != null) {
            return new ResponseEntity<>(clock2Dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
//	  @GetMapping("/workday/list")
//	    public List<WorkdayDto> workdayList(){
//	        return clock2Dao.workdayList();
//	    }
//	    
//	    // 영업일 추가
//	    @PostMapping("/workday/insert")
//	    public void insertWorkday(@RequestBody ResWorkdayDto resWorkdayDto) {
//	        clock2Dao.insertWorkday(resWorkdayDto);
//	    }
//	    
//	    // 영업일 삭제
//	    @DeleteMapping("/workday/delete/{workdayNo}")
//	    public void deleteWorkday(@PathVariable int workdayNo) {
//	        clock2Dao.deleteWorkday(workdayNo);
//	    }
//	    
//	    // 레스토랑별 영업리스트 조회
//	    @GetMapping("/workday/resList/{workdayResNo}")
//	    public List<ResWorkdayDto> resWorkdayList(@PathVariable int workdayResNo){
//	        return clock2Dao.resWorkdayList(workdayResNo);
//	    }
	    
	    
	}

