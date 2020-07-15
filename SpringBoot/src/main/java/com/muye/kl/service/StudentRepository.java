package com.muye.kl.service;

import com.muye.kl.entity.Student;

public interface StudentRepository {

    void saveUser(Student student);

    Student findUserByUserName(String userName);

    long updateUser(Student student);

    void deleteUserById(Long id);
}
