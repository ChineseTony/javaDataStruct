package com.tom.mybatisstudy.sql;

import org.apache.ibatis.jdbc.SQL;

public class SQLRun {

    public static void main(String[] args) {
        SQL sql = new SQL()
                .INSERT_INTO("user")
                .VALUES("id,name","#{id},#{name}");

        System.out.println(sql.toString());
    }
}
