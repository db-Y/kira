package com.ai.testredis.controller;

import com.ai.testredis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping(value = "/testuser", consumes = "application/json",produces = "application/json")
    public  void getUser(@RequestBody Map<String,String> paramIn){

        testService.list();
    }

    @PostMapping(value = "/testlist", consumes = "application/json",produces = "application/json")
    public  void getUserList(@RequestBody Map<String,String> paramIn){

        testService.testList();
    }

    @PostMapping(value = "/testorg", consumes = "application/json",produces = "application/json")
    public  void getOrgStr(@RequestBody Map<String,String> paramIn){

        testService.testOrganization();
    }

    @PostMapping(value = "/testorgList", consumes = "application/json",produces = "application/json")
    public  void getOrgList(@RequestBody Map<String,String> paramIn){

        testService.testOrganizations();
    }

    @PostMapping(value = "/testhash", consumes = "application/json",produces = "application/json")
    public  void gettesthash(@RequestBody Map<String,String> paramIn){

        testService.testhash();
    }
    @PostMapping(value = "/testDeleteHash", consumes = "application/json",produces = "application/json")
    public  void testDeleteHash(@RequestBody Map<String,String> paramIn){

        testService.testDeleteHash();
    }

    @PostMapping(value = "/testset", consumes = "application/json",produces = "application/json")
    public  void testset(@RequestBody Map<String,String> paramIn){
        testService.testSet();
    }

    @PostMapping(value = "/testzset", consumes = "application/json",produces = "application/json")
    public  void testzset(@RequestBody Map<String,String> paramIn){
        testService.testZSet();
    }
}
