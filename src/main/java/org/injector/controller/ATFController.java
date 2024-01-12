package org.injector.controller;

import org.injector.utils.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@RestController
public class ATFController {
    @Autowired
    private ScenarioContext scenarioContext;

    @Autowired
    public ATFController(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @ResponseBody
    @PostMapping("/runTests/level1")
    public ResponseEntity<String> runTestsLevelOne(@RequestBody Map<String, String> requestBody) {
        String appUrl = requestBody.get("url");
        scenarioContext.setAppUrl(appUrl);
        System.out.println(("Received appUrl: " + appUrl));
        String urlVerification = scenarioContext.getAppUrl();
        System.out.println("scenario context: i got this url " + urlVerification);
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("ATF Suite");

        XmlTest test = new XmlTest(suite);
        test.setName("ATFTest");

        XmlClass xmlClass = new XmlClass("org.injector.tests.SqlInjectorTest");
        test.getXmlClasses().add(xmlClass);

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testNG.setXmlSuites(suites);

        testNG.run();

        return ResponseEntity.ok("Tests are running...1");
    }



    @ResponseBody
    @PostMapping("/runTests/level2")
    public ResponseEntity<String> runTestsLevelTwo() {
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("ATF Suite");

        XmlTest test = new XmlTest(suite);
        test.setName("ATFTest");

        XmlClass xmlClass = new XmlClass("org.injector.tests.SqlInjectorTestTwo");
        test.getXmlClasses().add(xmlClass);

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testNG.setXmlSuites(suites);

        testNG.run();

        return ResponseEntity.ok("Tests are running...2");
    }

    @ResponseBody
    @PostMapping("/runTests/level3")
    public ResponseEntity<String> runTestsLevelThree() {
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("ATF Suite");

        XmlTest test = new XmlTest(suite);
        test.setName("ATFTest");

        XmlClass xmlClass = new XmlClass("org.injector.tests.SqlInjectorTestThree");
        test.getXmlClasses().add(xmlClass);

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testNG.setXmlSuites(suites);

        testNG.run();

        return ResponseEntity.ok("Tests are running...3");
    }


}
