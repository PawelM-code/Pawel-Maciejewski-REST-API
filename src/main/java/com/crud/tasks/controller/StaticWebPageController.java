package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

@ApiIgnore
@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String inedx(Map<String, Object> model){
        model.put("variable", "My Thymeleaf variable");
        model.put("one", 1);
        model.put("two", 2);
        return "index";
    }
}
