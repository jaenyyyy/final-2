package matdori;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test01 {
	/**
	 - 암호화(Encrypition)
	 - // 비밀번호 관리할 때 유용하게 사용 
	 - 데이터를 의도적으로 원하는 대상 외에는 못 알아보게 하는 작업을 의미
	 - 반대 작업을 복호화(Decryption) 라고 부름 
	 - 복호화가 있을 수도 없을 수도 있음 (있으면 양방향 암호화, 없으면 단방향 암호화)
	 */
	
	// @Test
	public void caesar() {
		// 시저(caesar) 암호화는 글자에 번호가 있다는 사실에 착안해 + 또는 - 변조를 한다
		// - hello = 108+101+108+108+111 --(+3) --> khoor(107+104+111+111+114)
		
		
		String word = "hello";
		
		StringBuffer buffer = new StringBuffer(word);
		for(int i=0; i < buffer.length(); i++) {
			char ch = buffer.charAt(i); // 현재 위치 글자 추출
			ch += 3;
			buffer.setCharAt(i,ch); // buffer의 현재 위치 글자를 변경
		}
		
		String encrypt = buffer.toString();
		
		log.debug("word = {}", word);
		log.debug("encrypt = {}", encrypt);
	}
	
	@Test
	public void xor() {
		// XOR 암호화
		// - xor 연산을 이용하여 값을 변조하는 방법
		// - 7 ^ 4= 3 ^ 4= 7
		// - ? ^ ? = 0 
		
		String word = "hello";
		
		StringBuffer buffer = new StringBuffer(word);
		for(int i=0; i < buffer.length(); i++) {
			char ch = buffer.charAt(i); // 현재 위치 글자 추출
			ch ^= 3;
			buffer.setCharAt(i,ch); // buffer의 현재 위치 글자를 변경
		}
		
		String encrypt = buffer.toString();
		
		log.debug("word = {}", word);
		log.debug("encrypt = {}", encrypt);
		
		
	}

}
