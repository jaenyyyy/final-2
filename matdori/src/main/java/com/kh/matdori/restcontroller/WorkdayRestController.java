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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.WorkdayDao;
import com.kh.matdori.dto.WorkdayDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/workday")
public class WorkdayRestController {

	@Autowired
	private WorkdayDao workdayDao;
	
	@PostMapping("/insert")
	public void insert(@RequestBody WorkdayDto workdayDto) {
		int no = workdayDao.sequence();
		workdayDto.setWorkdayNo(no);
		
		workdayDao.insert(workdayDto);
	}
	
	@DeleteMapping("/delete/{workdayResNo}")
	public void delete(@PathVariable int workdayResNo) {
		workdayDao.deleteWorkday(workdayResNo);
	}
	
	@GetMapping("/resList/{workdayResNo}")
	public List<WorkdayDto> selectList(@PathVariable int workdayResNo){
		return workdayDao.selectList(workdayResNo);
	}
	
	@GetMapping("/{workdayNo}")
	 public ResponseEntity<WorkdayDto> selectOne(@PathVariable int workdayNo) {
        WorkdayDto workdayDto = workdayDao.selectOne(workdayNo);
        if (workdayDto != null) {
            return new ResponseEntity<>(workdayDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@PutMapping("/update")
	public ResponseEntity<WorkdayDto> toggleWorkday(@RequestBody WorkdayDto workdayDto) {
	    // 서버에서 값을 토글
	    String currentNotWorkday = workdayDto.getNotWorkday();
	    String newNotWorkday = "N".equals(currentNotWorkday) ? "Y" : "N";
	    workdayDto.setNotWorkday(newNotWorkday);

	    // 데이터베이스 업데이트
	    workdayDao.updateWorkday(workdayDto);

	    // 업데이트된 객체 반환
	    return ResponseEntity.ok(workdayDto);
	}
}
