package com.jalinyiel.petrichor.start;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jalinyiel.petrichor.start.domain.MonitorInfoSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetrichorMonitorController {

    @Autowired
    PetrichorMonitorService petrichorMonitorService;

    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public JSONObject getMonitorData() {
        MonitorInfoSummary monitorInfoSummary = petrichorMonitorService.collectMonitorInfoSummary();
        return (JSONObject)JSON.toJSON(monitorInfoSummary);
    }
}
