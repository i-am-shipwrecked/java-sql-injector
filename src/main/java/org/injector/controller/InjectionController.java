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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Tag(name = "Tests Controller", description = "API")
public class InjectionController {
    @Autowired
    private ScenarioContext scenarioContext;
    @Autowired
    private InjectionRepository injectionRepository;

    @Autowired
    public InjectionController(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @ResponseBody
    @PostMapping("/runTests/level1")
    @Operation(summary = "Weak attack")
    public ResponseEntity<String> runTestsLevelOne(@RequestBody Map<String, String> requestBody, HttpServletRequest request) throws URISyntaxException {
        String appUrl = requestBody.get("url");
        String tableName = requestBody.get("tableName");

        scenarioContext.setAppUrl(appUrl);
        scenarioContext.setTableName(tableName);
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("ATF Suite");

        XmlTest test = new XmlTest(suite);
        test.setName("ATFTest");

        XmlClass xmlClass = new XmlClass("org.injector.tests.SqlInjectorTestOne");
        test.getXmlClasses().add(xmlClass);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("tableName", tableName);
        parameters.put("appUrl", appUrl);
        test.setParameters(parameters);

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testNG.setXmlSuites(suites);

        testNG.run();

        Injection injection = new Injection();
        injection.setUrl(appUrl);
        injection.setIpAddress(request.getRemoteAddr());
        injection.setTableName(tableName);
        injectionRepository.save(injection);

        return ResponseEntity.ok("Sql injection with EASY load is - DONE");
    }


    @ResponseBody
    @PostMapping("/runTests/level2")
    @Operation(summary = "Medium attack")
    public ResponseEntity<String> runTestsLevelTwo(@RequestBody Map<String, String> requestBody, HttpServletRequest request) {
        String appUrl = requestBody.get("url");
        String tableName = requestBody.get("tableName");

        scenarioContext.setAppUrl(appUrl);
        scenarioContext.setTableName(tableName);
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("ATF Suite");

        XmlTest test = new XmlTest(suite);
        test.setName("ATFTest");

        XmlClass xmlClass = new XmlClass("org.injector.tests.SqlInjectorTestTwo");
        test.getXmlClasses().add(xmlClass);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("tableName", tableName);
        parameters.put("appUrl", appUrl);
        test.setParameters(parameters);

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testNG.setXmlSuites(suites);

        testNG.run();

        Injection injection = new Injection();
        injection.setUrl(appUrl);
        injection.setIpAddress(request.getRemoteAddr());
        injection.setTableName(tableName);
        injectionRepository.save(injection);

        return ResponseEntity.ok("Sql injection with MID load is - DONE");
    }

    @ResponseBody
    @PostMapping("/runTests/level3")
    @Operation(summary = "Strong attack")
    public ResponseEntity<String> runTestsLevelThree(@RequestBody Map<String, String> requestBody, HttpServletRequest request) {
        String appUrl = requestBody.get("url");
        String tableName = requestBody.get("tableName");

        scenarioContext.setAppUrl(appUrl);
        scenarioContext.setTableName(tableName);
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("ATF Suite");

        XmlTest test = new XmlTest(suite);
        test.setName("ATFTest");

        XmlClass xmlClass = new XmlClass("org.injector.tests.SqlInjectorTestThree");
        test.getXmlClasses().add(xmlClass);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("tableName", tableName);
        parameters.put("appUrl", appUrl);
        test.setParameters(parameters);

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testNG.setXmlSuites(suites);

        testNG.run();

        Injection injection = new Injection();
        injection.setUrl(appUrl);
        injection.setIpAddress(request.getRemoteAddr());
        injection.setTableName(tableName);
        injectionRepository.save(injection);

        return ResponseEntity.ok("Sql injection with HARD load is - DONE");
    }
}
