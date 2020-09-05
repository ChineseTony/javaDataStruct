package com.tom.msgpack;

import org.msgpack.MessagePack;
import org.msgpack.template.Template;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WangTao
 */
public class MsgPackUtil {

    public static void main(String[] args) throws Exception {
        List<String> source = new ArrayList<>();
        source.add("test");
        source.add("ab");
        MessagePack msgpack = new MessagePack();
        byte[] tmp = msgpack.write(source);

        List<String> reuslt =  msgpack.read(tmp, Templates.tList(Templates.TString));
        reuslt.forEach(System.out::println);


        User user = new User();
        user.setName("wangtao");
        user.setPassword("12345");

        byte[] tmpBytes = msgpack.write(user);

        User tmpUser =  msgpack.read(tmpBytes,new User());
        System.out.println(tmpUser);
    }
}
