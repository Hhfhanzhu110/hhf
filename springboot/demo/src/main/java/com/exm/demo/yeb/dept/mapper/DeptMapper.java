package com.exm.demo.yeb.dept.mapper;

import com.exm.demo.yeb.dept.domain.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from system_dept")
    List<Dept> getAllDepts();
}
