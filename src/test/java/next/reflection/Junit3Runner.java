package next.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Junit3Runner {

    @Test
    public void runner() throws Exception {
        Class clazz = Junit3Test.class;

        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.getName().startsWith("test")) {
                /**
                 * 기존처럼 clazz.newInstance() 메서드는 deprecated되었는데
                 * 기본 생성자에 접근할 때 발생할 수 있는 다양한 예외 상황을 명확하게 처리하기 위해서입니다.
                 */
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                Object instance = constructor.newInstance();

                // 메서드 호출
                method.invoke(instance);
            }
        }

    }
}
