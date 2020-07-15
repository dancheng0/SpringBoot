package com.muye.kl;

import com.muye.kl.entity.Student;
import com.muye.kl.service.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentDaoTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSaveUser() {
        Student student = new Student();
        student.setId(2l);
        student.setUserName("小明");
        student.setPassWord("fffooo123");
        studentRepository.saveUser(student);
    }

    @Test
    public void findUserByUserName() {
        Student user = studentRepository.findUserByUserName("小明");
        System.out.println("user is " + user);
    }

    @Test
    public void updateUser() {
        Student student = new Student();
        student.setId(2l);
        student.setUserName("天空");
        student.setPassWord("fffxxxx");
        studentRepository.updateUser(student);
    }

    @Test
    public void deleteUserById() {
        studentRepository.deleteUserById(1l);
    }

}