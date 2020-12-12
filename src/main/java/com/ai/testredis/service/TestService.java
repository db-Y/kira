package com.ai.testredis.service;

import com.ai.testredis.entity.Organization;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public interface  TestService {

    String list();
    HashSet testhash();
    List<Object> testList();
    String testOrganization();
    List<Organization> testOrganizations();
    HashSet testDeleteHash();

    String testSet();

    String testZSet();
}
