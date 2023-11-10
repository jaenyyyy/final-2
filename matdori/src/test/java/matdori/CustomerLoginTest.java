package matdori;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dto.CustomerDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CustomerLoginTest {

   @Autowired
   private CustomerDao dao;
   
   @Test
   public void test() {
      //로그인 테스트
      CustomerDto dto = CustomerDto.builder()
                     .customerId("hello1234")
                     .customerPw("password1234")
                     .build();
      
      CustomerDto target = dao.login(dto);
      log.debug("target = {}", target);
      
      assertNotNull(target); // target이 not null이면 테스트 성공 
      // Assertions.assertNotNull(target); 

   }
}