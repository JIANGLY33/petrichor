package com.jalinyiel.petrichor.start.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        return "hello,jalinyiel";
    }

}
