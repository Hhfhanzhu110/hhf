package com.exm.demo.yeb.dept.service.impl;

import com.exm.demo.yeb.dept.domain.Dept;
import com.exm.demo.yeb.dept.mapper.DeptMapper;
import com.exm.demo.yeb.dept.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptMapper deptMapper;

    @Override
    public List<Dept> getAllDepts() {
        return deptMapper.getAllDepts();
    }
}
