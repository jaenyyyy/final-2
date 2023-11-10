package matdori;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test03 {
	
	@Test
	public void test() {
		DigestUtils utils = new DigestUtils("SHA3-256");
		
		String origin = "hello";
		String encrypt = utils.digestAsHex(origin);
		
		log.debug("origin = {}", origin);
		log.debug("encrypt = {}", encrypt);
		
	}

}
