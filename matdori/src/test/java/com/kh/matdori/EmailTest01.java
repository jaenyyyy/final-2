package com.kh.matdori;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class EmailTest01 {
	
	@Test
	public void test() {
		//이메일 발송 코드
		//- 발송 가능한 계정이 필요(ex : gmail)
		//- 프로젝트 생성 시 java mail sender라는 도구가 추가되어야 함
		
		//전송 도구 - 업체와 계정관련 정보 설정
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtp.gmail.com");//업체주소
		sender.setPort(587);//업체포트
		sender.setUsername("appae6072");
		sender.setPassword("wcfcgezbzhkxzqyd");
		
		//통신과 관련된 추가 설정
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");//인증 후 이용 설정(필수)
		props.setProperty("mail.smtp.debug", "true");//디버깅 기능 이용 설정(선택)
		props.setProperty("mail.smtp.starttls.enable", "true");//TLS 사용 설정(필수)
		props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");//TLS 버전 설정(필수)
		props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");//신뢰할 수 있는 대상 설정(필수)
		sender.setJavaMailProperties(props);
		
		
		//전송 메세지 - 상대방의 정보와 메일 내용을 설정
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("appae6772@naver.com");
		message.setSubject("야");
		message.setText("호");
		
		//전송
		sender.send(message);
	}
	
}





