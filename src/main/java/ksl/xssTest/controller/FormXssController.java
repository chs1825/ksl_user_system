package ksl.xssTest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class FormXssController {

    @RequestMapping("/form")
    public String showForm() {
        log.debug("bugbug");
        return "xss-form";
    }

    @PostMapping("/submit.do")
    public String submitForm(String userInput, Model model) {
        System.out.println("userInput = " + userInput);


        model.addAttribute("userInput", userInput);


        return "xss-result";
    }
}
