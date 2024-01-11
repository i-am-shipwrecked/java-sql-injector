package org.injector.controller;

import org.injector.utils.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ATFController {
    private final ScenarioContext scenarioContext;

    @Autowired
    public ATFController(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @ResponseBody
    @PostMapping("/runTests")
    public String runTests() {
        TestNG testNG = new TestNG();

        XmlSuite suite = new XmlSuite();
        suite.setName("ATFSuite");

        XmlTest test = new XmlTest(suite);
        test.setName("ATFTest");

        XmlClass xmlClass = new XmlClass("org.injector.tests.SqlInjectorTest");

        test.getXmlClasses().add(xmlClass);

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testNG.setXmlSuites(suites);

        testNG.run();

        return "Tests are running...";
    }
}
