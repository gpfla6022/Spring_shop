package com.yhr.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){ // 데이터를 실어서 view로 보냄

        model.addAttribute("data", "hello!!"); // (이름, 값)
        return "hello"; // 화면 이름
    }

}



