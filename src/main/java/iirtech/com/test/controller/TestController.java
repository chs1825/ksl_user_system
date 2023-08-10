package iirtech.com.test.controller;

import egov.com.annotaion.LogTrace;
import iirtech.com.test.service.TestSerivce;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class TestController {



    @Resource(name = "testService")
    private TestSerivce testSerivce;

    // 로그를 출력할 Logger 생성
    private static final Logger logger = LoggerFactory.getLogger("로그테스트");

    @LogTrace(path = "/home.do")
//    @RequestMapping("/home.do")
    @GetMapping("/home.do")
    public String homeStart(){
//        log.info("Controller 해보기 {}",request.getSession().getId());
        return "home";
    }


    @LogTrace(path="/go.do")
    @RequestMapping("/go.do")
    public String go(Model model) {


        log.debug("디버그 : {}", testSerivce.testSpringBoot());
        logger.debug("디버그 : {}", testSerivce.testSpringBoot());

        model.addAttribute("a", testSerivce.testSpringBoot().getTitle());

        return "/jspgood";
    }


//    @LogTrace
    @RequestMapping("/goRedirect.do")
    public String goRedirect (HttpServletResponse response, RedirectAttributes redirectAttributes){

        // 리다이렉션을 수행
//        response.setStatus(302);
//        response.setHeader("Location", "/go.do");
        redirectAttributes.addFlashAttribute("redirectMessage", "Redirecting to URL: /targetPage");

        return "redirect:go.do";
    }






}
