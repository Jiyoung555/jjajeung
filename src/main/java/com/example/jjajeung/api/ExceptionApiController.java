package com.example.jjajeung.api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice("com.example.jjajeung.api")
public class ExceptionApiController {

    @ResponseBody // 뷰 페이지 없이, 아래 리턴값을 있는 그대로 리턴해주겠다
    @ResponseStatus(HttpStatus.NOT_FOUND) // 리턴값에다가 + 추가로 not found에 해당하는 404 코드도 리턴하겠다
    @ExceptionHandler(IllegalArgumentException.class) // 괄호 속 예외 발생 시, 아래 메소드를 수행하겠다
    public String notFound(Exception exception) {
        return "{}"; // 비어있는 JSON 객체 반환
    }

}
