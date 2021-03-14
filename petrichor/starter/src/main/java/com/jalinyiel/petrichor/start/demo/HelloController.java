package com.jalinyiel.petrichor.start.demo;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        return "hello,jalinyiel";
    }

    @RequestMapping(value = "monitor", method = RequestMethod.GET)
    public JSONObject getMonitorData() {
        Map<String, Object> map = new HashMap<>();
        map.put("hello","jalinyiel");
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject;
    }
}
