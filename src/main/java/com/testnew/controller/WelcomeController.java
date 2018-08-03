package com.testnew.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;

@Controller
public class WelcomeController {

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @GetMapping("/")
    @Timed(name = "welcome.time")
    @Metered(name = "welcome.count")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "welcome";
    }

    @RequestMapping("/foo")
    @Timed(name = "foo.time")
    @Metered(name = "foo.count")
    @ExceptionMetered(name = "foo.exception.count")
    public String foo(Map<String, Object> model) {
        throw new RuntimeException("foo");
    }

}