package iirtech.com.search.controller;

import egov.com.annotaion.LogTrace;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchWordController {

    @RequestMapping("/first.do")
    @LogTrace(path = "/first.do")
//    @GetMapping("first.do")
    public String searchFirstPage(){
        return "first";
    }


    @LogTrace(path = "/second.do")
    @RequestMapping("/second.do")
    public String searchSecondPage(){
        return "second";
    }


}
