package SynchronizedExample;

import java.lang.reflect.Field;

public class ClassObject {
    private int a = 5;
    private int b = 10;
    public static void main(String[] args) {
        Class klass = ClassObject.class;

        for (Field f : klass.getDeclaredFields()) {
            System.out.println("a " + f);
        }
    }

    private void test() {}
}
