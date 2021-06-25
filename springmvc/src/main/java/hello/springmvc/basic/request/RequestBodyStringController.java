package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity : HTTP header, body 정보를 편리하게 조회 / 응답에도 사용 가능
     * 요청 파라미터 조회 기능과는 관계 없다. @RequestParam X, @ModelAttribute X
     * <p>
     * HttpEntity 상속 받은 아래 객체들도 같은 기능을 제공한다. + 추가 기능
     * RequestEntity : HttpMethod, url 정보가 추가, 요청에서 사용
     * ResponseEntity : HTTP 상태 코드 설정 가능.
     * <p>
     * ※ SpringMVC 내부에서 HTTP 메시지 바디를 읽어서 문자나 객체로 변환해서 전달해주는데,
     * 이때 HTTP 메시지 컨버터('HttpMessageConverter')라는 기능을 사용한다.
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}", messageBody);
        return new HttpEntity<>("ok");
    }

    @PostMapping("/request-body-string-v3/extends")
    public ResponseEntity<String> requestBodyStringV3Extends(RequestEntity<String> requestEntity) {
        String messageBody = requestEntity.getBody();
        HttpMethod method = requestEntity.getMethod();
        URI url = requestEntity.getUrl();

        log.info("messageBody = {}", messageBody);
        log.info("method = {}", method);
        log.info("url = {}", url);

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    /**
     * '@RequestBody','@ResponseBody'를 사용하면 HTTP 메시지 바디 정보를 편리하게 조회/조작할 수 있다.
     * 헤더 정보가 필요하다면 'HttpEntity'를 사용하거나 '@RequestHeader'를 사용하면 된다.
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        log.info("messageBody = {}", messageBody);
        return "ok";
    }

}
