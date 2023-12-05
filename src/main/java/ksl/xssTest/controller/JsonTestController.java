package ksl.xssTest.controller;

import ksl.xssTest.service.ExceptionTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JsonTestController {

    private ExceptionTestService exceptionTestService;

    public JsonTestController(ExceptionTestService exceptionTestService) {
        this.exceptionTestService = exceptionTestService;
    }

    @RequestMapping("/jsonTest")
    public String returnJson(Model model) {

        model.addAttribute("joo", "hi");
        return "jsonView";
    }

    @RequestMapping("/api/errorTest")
    public String returnApiError(Model model)  {
        throw new NullPointerException();
    }

    @RequestMapping("/api/serviceError")
    public String returnServiceApiError(Model model)  {
        exceptionTestService.makeNull();
        return "";
    }

    @RequestMapping("/eroor")
    public String returnError(Model model)  {
        exceptionTestService.makeNull();
        return "";
    }




}
