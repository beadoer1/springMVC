package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j // lombok 이 제공하는 annotation logger 자동으로 넣어준다.
@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        log.info(" info log = " + name); // 출력은 아래 예시와 같지만 + 연산이 진행되어 연산을 잡아 먹는다. 아래처럼 사용할 것 주의!

        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info(" info log = {}", name);
        log.warn(" warn log = {}", name);
        log.error("error log = {}", name);

        return "ok"; // @RestController 의 경우 return 값을 HTTP 메시지 body에 콱! 넣어 버린다.
    }
}
