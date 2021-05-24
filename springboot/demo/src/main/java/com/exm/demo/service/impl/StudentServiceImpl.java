package com.exm.demo.service.impl;

import com.exm.demo.domain.Student;
import com.exm.demo.mapper.StudentMapper;
import com.exm.demo.service.StudentService;
import com.mchange.v2.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student getStuDentById(String id) {
        if (StringUtils.nonEmptyString(id)) {
            return studentMapper.getStuDentById(id);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Integer insertStudent(Student student) {
        int result = 0;
        if (student != null) {
            result = studentMapper.insertStudent(student);
        }
        return result;
    }


}
