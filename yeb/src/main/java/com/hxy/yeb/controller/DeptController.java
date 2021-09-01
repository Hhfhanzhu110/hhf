package com.hxy.yeb.controller;

import com.hxy.yeb.domain.Dept;
import com.hxy.yeb.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    @RequestMapping("/departs")
    public List<Dept> getAllDepts(){
       return deptService.getAllDepts();
    }
}
