package com.kh.matdori;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test04 {
	
	@Test
	public void test() {
		// spring-security에서 제공하는 라이브러리를 사용하여 암호화를 진행 (bcrypt)
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		
		String origin = "hello";
		String encrypt = encoder.encode(origin);
		
		log.debug("origin={}", origin);
		log.debug("encrypt={}", encrypt);
		
		
		// 주의할 점은 매번 암호화 결과가 달라지기 때문에 라이브러리를 통해서만 비교가 가능하다
		String input = "hello";
		boolean result = encoder.matches(input, encrypt); // 순서 바뀌면 실행 x 
		log.debug("result={}", result);
		assertTrue(result); // result가 true라면 테스트 성공, 아니면 실패 !!!!!! 
	}

}
