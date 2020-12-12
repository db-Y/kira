package com.ai.testredis.service.impl;

import com.ai.testredis.entity.MyUser;
import com.ai.testredis.entity.Organization;
import com.ai.testredis.service.TestService;

import com.ai.testredis.utils.BeanMapUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;


import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public String list() {
        MyUser myuser = new MyUser(1, "JAVA架构师", "男");
        MyUser myUser2 = new MyUser(2, "java高级开发工程师", "男");
        ValueOperations<String, MyUser> valueOperations = redisTemplate.opsForValue();
        ValueOperations<String, String> valueOperations1 = stringRedisTemplate.opsForValue();
        valueOperations.set("zhangsan", myuser);
        boolean isExists = redisTemplate.hasKey("zhangsan");
        System.out.println("存在key值" + isExists);
        MyUser getUser = (MyUser) redisTemplate.opsForValue().get("zhangsan");
        System.out.println("从redis" + getUser.toString());
        valueOperations1.set("lisi", myUser2.toString(), 10, TimeUnit.SECONDS);
        System.out.println("lisi是否存在" + stringRedisTemplate.hasKey("lisi"));

        /*----------------------------*/
        stringRedisTemplate.opsForValue().append("lisi", "processor");
        System.out.println(stringRedisTemplate.opsForValue().get("lisi"));

        return "SUCCESS";
    }


    @Override
    public List<Object> testList() {
        List<MyUser> list1 = new ArrayList<>();
        for (int i = 5; i < 10; i++) {
            MyUser itemUser = new MyUser();
            itemUser.setId(i);
            itemUser.setSex("女");
            itemUser.setUserName("Lily" + i);
            list1.add(itemUser);
        }
        //  redisTemplate.opsForList().leftPush("user1",list1);
//        List<MyUser> userList =(List<MyUser>) redisTemplate.opsForList().leftPop("user1");
//        System.out.println(userList.size() +"--"+userList.get(0).toString());

        // long n = redisTemplate.opsForList().remove("user1",1,list1.get(0));

        System.out.println(redisTemplate.opsForList().size("user1"));
        MyUser myUser2 = new MyUser(10, "Lucy", "男");
        // redisTemplate.opsForList().leftPush("user1",myUser2);
        System.out.println(redisTemplate.opsForList().size("user1"));
        // redisTemplate.opsForList().rightPop("user1");
        System.out.println(redisTemplate.opsForList().size("user1"));
        // redisTemplate.opsForList().leftPushAll("user1",list1);
        System.out.println(redisTemplate.opsForList().size("user1"));
        // redisTemplate.opsForList().leftPush("user1",new MyUser(10,"Lucy","女"));
        redisTemplate.opsForList().remove("user1", 0, new MyUser(10, "Lucy", "女"));
        return null;
    }

    @Override
    public String testOrganization() {

        Organization organization = new Organization(1, "001001", "亚信移动事业部", "BJ");
        stringRedisTemplate.opsForValue().set("org1", organization.toString());

        return null;
    }

    @Override
    public List<Organization> testOrganizations() {

        List<Organization> organizations = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Organization itemOrg = new Organization();
            itemOrg.setId(i);
            itemOrg.setOrgCode("00100" + i);
            itemOrg.setOrgName("事业部" + i);
            organizations.add(itemOrg);
        }
        redisTemplate.opsForList().leftPushAll("orglist1", organizations);

        long size = redisTemplate.opsForList().size("orglist1");
        System.out.println(size);
        Organization organization = (Organization) redisTemplate.opsForList().index("orglist1", 1);
        System.out.println(organization.getId());

        return null;
    }


    @Override
    public HashSet testhash() {

        Organization organization = new Organization(6, "001006", "亚信移动事业部", "BJ");
        //  redisTemplate.opsForHash().putAll(String.valueOf(organization.getId()), BeanMapUtils.beanToMap(organization));
        Map<String, Object> map = new HashMap<>();
        String orgCode = (String) redisTemplate.opsForHash().get("6", "orgCode");
        List<Object> list = new ArrayList<Object>();
        for (Field field : Organization.class.getDeclaredFields()) {
            if (!field.equals("serialVersionUID")) {
                list.add(field.getName().toString());
            }
        }
        List<String> result = redisTemplate.opsForHash().multiGet("6", list);
        System.out.println(result);
        return null;
    }

    @Override
    public HashSet testDeleteHash() {

        Map<String, Object> map = redisTemplate.opsForHash().entries("6");
        System.out.println("通过entries(H key)方法获取变量中的键值对:" + map);
        Organization organization = new Organization();
        BeanMapUtils.mapToBean(map, organization);
        System.out.println(organization.getOrgCode());

        Set<String> keys = redisTemplate.opsForHash().keys("6");
        System.out.println(keys);
        for (String key : keys) {
            System.out.println(redisTemplate.opsForHash().get("6", key));
        }
        Cursor<Map.Entry<Object, Object>> curosr = redisTemplate.opsForHash().scan("6", ScanOptions.NONE);
        while (curosr.hasNext()) {
            Map.Entry<Object, Object> entry = curosr.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        return null;
    }

    @Override
    public String testSet() {
//        String[] setItems = new String[]{"001001","001002","001003","001002","亚信","普元","德勤"};
//        long num =redisTemplate.opsForSet().add("setItem",setItems);
//        System.out.println("setItem成功加入--"+num);
//
//        String[] removeItems = new String[]{"001003","亚信"};
//        num = redisTemplate.opsForSet().remove("setItem",removeItems);
//        System.out.println("setItem移除成功后返回--"+num);
//
//
        /*
        String[] removeItems = new String[]{"001003", "亚信"};
        long num = redisTemplate.opsForSet().add("setItem", removeItems);
        System.out.println("setItem成功添加后返回--" + num);
        String[] popAfterItem = (String[]) redisTemplate.opsForSet().pop("setItem");
        System.out.println(popAfterItem);
        System.out.println("setItem现有成员:=="+redisTemplate.opsForSet().members("setItem"));

        String[] setItem2 = new String[]{"盈科","IBM"};
        redisTemplate.opsForSet().add("setItem2",setItem2);
       */

        Set<String> ls =  redisTemplate.opsForSet().difference("setItem","setItem2");
        System.out.println("difference"+ls);

        redisTemplate.opsForSet().add("setItem3",new String[]{"阿里","腾讯","百度"});
        redisTemplate.opsForSet().differenceAndStore("setItem","setItem2","setItem3");
        System.out.println("setItem3="+redisTemplate.opsForSet().members("setItem3"));

        Set<String> intersetLS = redisTemplate.opsForSet().intersect("setItem","setItem2");
        System.out.println("setItem和setItem2的intersect="+intersetLS);

        Set<String> unionset = redisTemplate.opsForSet().union("setItem","setItem2");
        System.out.println("setItem和setItem2的unionset="+unionset);
        return null;
    }

    @Override
    public String testZSet() {

       boolean isAdd =  redisTemplate.opsForZSet().add("zset1","工作",1.0);
       System.out.println("添加zset1="+isAdd);


//        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("生活",9.6);
//        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("娱乐",9.9);
//        redisTemplate.opsForZSet().add("zsetlist",new HashSet<>(Arrays.asList(objectTypedTuple1,objectTypedTuple2)));



        return null;
    }
}
