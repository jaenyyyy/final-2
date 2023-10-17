package com.kh.matdori.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfiguration {
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Bean
	public JavaMailSender sender() {
		//전송도구 - 업체와 계정관련 정보 설정
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(emailProperties.getHost()); //업체 정보(주소)
		sender.setPort(emailProperties.getPort());  //업체 포트
		sender.setUsername(emailProperties.getUsername()); //나의 이메일 계정
		sender.setPassword(emailProperties.getPassword());  //나의 앱 비밀번호 (일반 비밀번호는 보안상 x)
		
		//통신과 관련된 추가설정
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");  //인증 후 이용 설정 (필수)
		props.setProperty("mail.smtp.debug", "true"); //디버깅 기능 이용 설정 (선택)
		props.setProperty("mail.smtp.starttls.enable", "true"); //TLS 사용 설정(필수)
		props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2"); //TLS 버전 설정
		props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com"); //신뢰할 수 있는 대상설정
		
		sender.setJavaMailProperties(props);
		
		return sender;
	}
}
