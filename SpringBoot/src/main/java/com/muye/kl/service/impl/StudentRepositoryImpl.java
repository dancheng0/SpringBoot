package com.muye.kl.service.impl;

import com.mongodb.client.result.UpdateResult;
import com.muye.kl.entity.Student;
import com.muye.kl.service.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public StudentRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 创建对象
     * @param student
     */
    @Override
    public void saveUser(Student student) {
        mongoTemplate.save(student);
    }

    /**
     * 根据用户名查询对象
     * @param userName
     * @return
     */
    @Override
    public Student findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        Student student =  mongoTemplate.findOne(query , Student.class);
        return student;
    }

    /**
     * 更新对象
     * @param student
     */
    @Override
    public long updateUser(Student student) {
        Query query=new Query(Criteria.where("id").is(student.getId()));
        Update update= new Update().set("userName", student.getUserName()).set("passWord", student.getPassWord());
        //更新查询返回结果集的第一条
        UpdateResult result =mongoTemplate.updateFirst(query,update,Student.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        if(result!=null)
            return result.getMatchedCount();
        else
            return 0;
    }

    /**
     * 删除对象
     * @param id
     */
    @Override
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,Student.class);
    }
}