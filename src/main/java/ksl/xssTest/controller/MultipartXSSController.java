package ksl.xssTest.controller;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MultipartXSSController {
    @RequestMapping("/multipart")
    public String showForm() {
        return "xss-multipart";
    }

    @PostMapping("/submit-multipart")
    public String submitForm(@RequestParam("file") MultipartFile file, Model model) {

        String userInput = file.getOriginalFilename();
        System.out.println("userInput = " + userInput);
        userInput = StringEscapeUtils.escapeHtml4(userInput);
        System.out.println("userInput = " + userInput);
        model.addAttribute("userInput", userInput);
        return "xss-result"; // xss-result.jsp를 생성해야 합니다.
    }
}