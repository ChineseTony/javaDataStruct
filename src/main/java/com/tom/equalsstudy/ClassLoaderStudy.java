package com.tom.equalsstudy;


import java.io.IOException;
import java.io.InputStream;

/**
 * @author WangTao
 */
public class ClassLoaderStudy {


    public static void main(String[] args) throws Exception {
        ClassLoader my = new MyClassLoader();
        StringEquals obj = (StringEquals)my.loadClass(StringEquals.class.getName())
                .newInstance();
        System.out.println(obj);
        System.out.println(obj instanceof StringEquals);
        StringEquals obj2 = (StringEquals) ClassLoader.getSystemClassLoader()
                .loadClass(StringEquals.class.getName()).newInstance();
        System.out.println(obj == obj2);

    }

}

class MyClassLoader extends ClassLoader{

    @Override
    public Class<?> loadClass(String name)
            throws ClassNotFoundException {
        String localPath = this.getClass().getResource("").getPath();
        String fileName = localPath + name.substring(name.lastIndexOf(".") + 1) + ".class";
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        if (inputStream == null) {
            return super.loadClass(name);
        }
        try {
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }
}
