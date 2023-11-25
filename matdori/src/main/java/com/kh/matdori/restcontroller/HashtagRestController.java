package com.kh.matdori.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.HashtagDao;
import com.kh.matdori.dto.HashtagDto;
import com.kh.matdori.dto.HashtagListDto;
import com.kh.matdori.dto.ResHashtagDto;

@CrossOrigin
@RestController
@RequestMapping("/hashtag")
public class HashtagRestController {
	
	@Autowired
	private HashtagDao hashtagDao;
	
	
	@GetMapping("/list")
	public List<HashtagDto> hashList(){
	    return hashtagDao.hashList();
	}
	
	@PostMapping("/insert")
	public void insert(@RequestBody ResHashtagDto resHashtagDto) {
		hashtagDao.insert(resHashtagDto);
	}
	
	@DeleteMapping("/delete/{hashNo}")
	public void delete(@PathVariable int hashNo) {
		hashtagDao.delete(hashNo);
	}
	
	@GetMapping("/resList/{resNo}")
	public List<HashtagListDto> resHashList(@PathVariable int resNo){
		return hashtagDao.resHashList(resNo);
	}
	
	@GetMapping("/count/{resNo}")
	public List<ResHashtagDto> count(@PathVariable int resNo){
		return hashtagDao.resHashtagCount(resNo);
	}
}
