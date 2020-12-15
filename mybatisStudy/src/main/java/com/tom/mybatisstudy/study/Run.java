package com.tom.mybatisstudy.study;

import com.tom.mybatisstudy.entity.User;
import com.tom.mybatisstudy.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class Run {

    private static final Logger logger = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) {
        try {
            InputStream reader = Resources.
                    getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new
                    SqlSessionFactoryBuilder().build(reader);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            for (User allUser : mapper.getAllUsers()) {
                logger.info("users:{}",allUser);
            }
            logger.info("user--->{}",mapper.getUserById(1));
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
