package next.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Junit4Runner {
    @Test
    public void run() throws Exception {
        Class clazz = Junit4Test.class;

        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(MyTest.class)) {
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                Object instance = constructor.newInstance();

                // 메서드 호출
                method.invoke(instance);
            }

        }

    }
}


