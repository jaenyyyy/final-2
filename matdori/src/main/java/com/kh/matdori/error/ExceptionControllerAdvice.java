package com.kh.matdori.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@ControllerAdvice(annotations = {Controller.class})
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public String error(Exception e) {
		return "error/500";
	}
	

	@ExceptionHandler(NoTargetException.class)
	public String noTarget(NoTargetException e) {
		return "error/noTarget";
	}
	
	@ExceptionHandler(AuthorityException.class)
	public String authority(AuthorityException e) {
		e.printStackTrace();
		return "error/authority";
	}
}


