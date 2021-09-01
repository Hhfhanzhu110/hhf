package com.hxy.yeb.service.impl;

import com.hxy.yeb.domain.Dept;
import com.hxy.yeb.mapper.DeptMapper;
import com.hxy.yeb.service.DeptService;
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
