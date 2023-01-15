package com.me.dao;

import com.me.domain.Car;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarDao extends SqlSessionDaoSupport {

    @Autowired
    public void setFactory(SqlSessionFactory factory){
        super.setSqlSessionFactory(factory);
    }

    public void save(Car car){
        //原来自己写jdbc
        //现在让mybatis实现jdbc
        //原来自己管理mybatis，自己造工厂，要sqlSession
        //现在让spring注入工厂
        SqlSession session = super.getSqlSession();
        session.insert("save",car);
    }
}
