package ksl.xssTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

@Controller
public class BeanSearchController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/bean")
    @ResponseBody
    public String sss(MultipartRequest mmmmm){

        return "{'t':'1'}";
    }

}
