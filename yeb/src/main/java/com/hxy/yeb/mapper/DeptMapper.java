package com.hxy.yeb.mapper;


import com.hxy.yeb.domain.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from system_dept")
    List<Dept> getAllDepts();
}
