import org.openjdk.jol.info.ClassLayout;

/**
 * @author wangtao
 * @date 2021/8/13
 */
public class ClassLength {

    public static void main(String[] args) {
        Person person = new Person();
        System.out.println(ClassLayout.parseInstance(person).toPrintable());
    }

    static class Person{

    }
}
