package com.exm.demo;

import com.exm.demo.domain.Student;
import com.exm.demo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    StudentService studentService;

    @Test
    void contextLoads() {
        Student stuDentById = studentService.getStuDentById("1");
        System.out.println("-------------------------"+stuDentById.toString());
    }

    @Test
    void insertStudent() {
        Student student = new Student();
        student.setAge(12);
        student.setClasses("3年2班");
        student.setName("小明");
        student.setPerformance(95);
        Integer integer = studentService.insertStudent(student);
        if (integer != null && integer != 0) System.out.println("----------------插入成功--------------");
        else System.out.println("---------------添加失败--------------");
    }

}
