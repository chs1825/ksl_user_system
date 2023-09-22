package ksl.xssTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RequestBodyXSSController {

    @RequestMapping("/request")
    public String showForm() {
        return "xss-request-body";
    }


    @PostMapping("/submit-body")
    @ResponseBody
//    public Map<String,String> submitForm(@RequestBody String requestBody, Model model) {
    public Map<String,Object> submitForm(@RequestBody Map<String,Object> requestBody, Model model) {

//        String tt = "dsda";
//        System.out.println("ddd = " + tt);
        System.out.println("requestBody = " + requestBody);
        return requestBody;
    }



}