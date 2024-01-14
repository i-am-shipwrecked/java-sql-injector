package org.injector.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.injector.entities.Injection;
import org.injector.repositories.InjectionRepository;
import org.injector.utils.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@Tag(name = "Tests Controller", description = "API")
public class ATFController {
    @Autowired
    private ScenarioContext scenarioContext;
    @Autowired
    private InjectionRepository injectionRepository;

    @Autowired
    public ATFController(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @ResponseBody
    @PostMapping("/runTests/level1")
    @Operation(summary = "Weak attack")
    public ResponseEntity<String> runTestsLevelOne(@RequestBody Map<String, String> requestBody, HttpServletRequest request) throws URISyntaxException {
        String appUrl = requestBody.get("url");
        scenarioContext.setAppUrl(appUrl);
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("ATF Suite");

        XmlTest test = new XmlTest(suite);
        test.setName("ATFTest");

        XmlClass xmlClass = new XmlClass("org.injector.tests.SqlInjectorTestOne");
        test.getXmlClasses().add(xmlClass);

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testNG.setXmlSuites(suites);

        testNG.run();

        Injection injection = new Injection();
        injection.setUrl(appUrl); // appUrl уже является строкой
        injection.setIpAddress(request.getRemoteAddr());
        injectionRepository.save(injection);


        return ResponseEntity.ok("Sql injection with EASY load is - DONE");
    }


    @ResponseBody
    @PostMapping("/runTests/level2")
    @Operation(summary = "Medium attack")
    public ResponseEntity<String> runTestsLevelTwo(@RequestBody Map<String, String> requestBody) {
        String appUrl = requestBody.get("url");
        scenarioContext.setAppUrl(appUrl);
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

        return ResponseEntity.ok("Sql injection with MID load is - DONE");
    }

    @ResponseBody
    @PostMapping("/runTests/level3")
    @Operation(summary = "Strong attack")
    public ResponseEntity<String> runTestsLevelThree(@RequestBody Map<String, String> requestBody) {
        String appUrl = requestBody.get("url");
        scenarioContext.setAppUrl(appUrl);
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

        return ResponseEntity.ok("Sql injection with HARD load is - DONE");
    }
}
