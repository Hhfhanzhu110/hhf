package com.exm.demo.controller;

import com.exm.demo.domain.Student;
import com.exm.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取对应键的值，redisTemplate操作
     * @param key
     * @return
     */
    @RequestMapping(value = "get")
    public Object getValue(String key){
        Object value = redisTemplate.opsForValue().get(key);
        return value;
    }

    /**
     * 添加键值
     * @param key
     * @param value
     * @return
     */
    @RequestMapping(value = "set")
    public boolean set(String key, String value){
        return redisUtil.set(key, value);
    }

    /**
     * 添加List对象数据到redis中
     * @return
     */
    @RequestMapping(value = "/setList")
    public boolean setList(){
        List<Object> stuList = new ArrayList<>();
        Student stu = new Student();
        Student stu1 = new Student();
        stu.setName("张三1");
        stu.setAge(18);
        stu.setAddress("北京昌平");
        stu.setClasses("3年4班");
        stu.setPerformance(95);
        stu1.setName("王五");
        stu1.setAge(19);
        stu1.setAddress("北京昌平");
        stu1.setClasses("3年5班");
        stu1.setPerformance(23);
        stuList.add(stu);
        stuList.add(stu1);
        return redisUtil.lSetList("stu", stuList);
    }

    /**
     * 获取全部数据
     * @return
     */
    @RequestMapping(value = "getList")
    public Object getList(){
        return redisUtil.lGet("stu", 0, -1);
    }

}

