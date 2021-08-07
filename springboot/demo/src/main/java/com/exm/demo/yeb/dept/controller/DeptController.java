package com.exm.demo.yeb.dept.controller;

import com.exm.demo.yeb.dept.domain.Dept;
import com.exm.demo.yeb.dept.service.DeptService;
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
