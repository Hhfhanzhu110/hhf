package com.exm.demo.mapper;

import com.exm.demo.domain.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {

    public Student getStuDentById(String id);

    public int insertStudent(Student student);
}
