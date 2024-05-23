package hello.advanced.app.v1;

import org.springframework.web.bind.annotation.*;

//@RequestMapping // 스프링은 @Controller 또는 @RequestMapping 이 있어야 컨트롤러로 인식함
//@ResponseBody
@RestController // 스프링 6 & 부트 3 이상에서는 @Controller 나 @RestController 로 해야만 인식됨.
public interface OrderControllerV1 {

    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}
