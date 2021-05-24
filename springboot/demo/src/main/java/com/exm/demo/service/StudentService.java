package com.exm.demo.service;

import com.exm.demo.domain.Student;

public interface StudentService {

    public Student getStuDentById(String id);

    public Integer insertStudent(Student student);

}
