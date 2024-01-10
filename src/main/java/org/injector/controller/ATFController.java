package org.injector.controller;

import org.injector.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ATFController {
    private final ScenarioContext scenarioContext;

    @Autowired
    public ATFController(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @PostMapping("/runTests")
    @ResponseBody
    public String runTests() {
        return "test are running ...";
    }

}
