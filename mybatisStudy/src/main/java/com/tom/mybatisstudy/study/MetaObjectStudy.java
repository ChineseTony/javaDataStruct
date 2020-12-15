package com.tom.mybatisstudy.study;

import com.tom.mybatisstudy.entity.User;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

public class MetaObjectStudy {

    public static void main(String[] args) {
        User user = new User(1,"name",3,"21");
        MetaObject metaObject = SystemMetaObject.forObject(user);

        System.out.println(metaObject.getValue("age"));
    }
}
