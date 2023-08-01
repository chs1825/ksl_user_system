package iirtech.com.sample.controller;

import iirtech.com.sample.service.SampleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class SampleController {

    @Resource(name = "sampleService")
    private SampleService sampleService;

    @RequestMapping("/sample")
    public String sample(){
        return sampleService.sample();
    }
}
