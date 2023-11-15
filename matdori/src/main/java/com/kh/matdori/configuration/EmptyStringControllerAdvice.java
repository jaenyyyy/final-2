package com.kh.matdori.configuration;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class EmptyStringControllerAdvice {
	//empty String을 컨트롤러가 null로 해석하도록
	
	@InitBinder
	public void coutomBinding(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(List.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				String convert = text.replace(",", "").trim();
				setValue(convert.isEmpty() ? List.of() : Arrays.stream(text.split(",")).toList());
			}
		});
	}
}