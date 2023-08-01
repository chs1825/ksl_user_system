package iirtech.com.test.controller;

import iirtech.com.sample.service.SampleService;
import iirtech.com.test.service.TestSerivce;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@Slf4j
public class TestController {


    @Resource(name = "testService")
    private TestSerivce testSerivce;

    // 로그를 출력할 Logger 생성
    private static final Logger logger = LoggerFactory.getLogger("로그테스트");
    @RequestMapping("/go.do")
    public String go(Model model){




        log.debug("디버그 : {}" , testSerivce.testSpringBoot());
        logger.debug("디버그 : {}" , testSerivce.testSpringBoot());

        model.addAttribute("a", testSerivce.testSpringBoot().getTitle() + "아니!!!!!");

        return "jspgood";
    }

}
