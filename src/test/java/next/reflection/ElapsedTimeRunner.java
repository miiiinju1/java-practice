package next.reflection;

import next.ElapsedTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ElapsedTimeRunner {

    @Test
    public void runner() throws Exception {
        Class<?> clazz = ElapsedTimeTest.class;

        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.getName().startsWith("test")) {
                /**
                 * 기존처럼 clazz.newInstance() 메서드는 deprecated되었는데
                 * 기본 생성자에 접근할 때 발생할 수 있는 다양한 예외 상황을 명확하게 처리하기 위해서입니다.
                 */
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                Object instance = constructor.newInstance();

                if(method.isAnnotationPresent(ElapsedTime.class)) {
                    runMethod(method, instance);
                }
                else {
                    // 메서드 호출
                    method.invoke(instance);
                }
            }
        }

        ElapsedTimeTest elapsedTimeTest = new ElapsedTimeTest();
        runMethod(elapsedTimeTest::test1);
        runMethod(elapsedTimeTest::test2);
        runMethod(elapsedTimeTest::test3);
    }

    @DisplayName("실행 시간을 측정하면 중간에 갑자기 느려지는 현상이 발생한다.")
    @Test
    void testMethodNameHere() throws Exception {
        Class<?> clazz = ElapsedTimeTest.class;

        Method test1 = clazz.getDeclaredMethod("test1");
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        Object instance = constructor.newInstance();
        for(int i= 0;i<30;i++) {
            runMethod(test1, instance);
        }
    }

    private void runMethod(Method method, Object instance) throws Exception {
        if (method.isAnnotationPresent(ElapsedTime.class)) {
            long start = System.nanoTime();
            method.invoke(instance);
            long end = System.nanoTime();
            System.out.println(method.getName() + " : " + (end - start) + " nanoseconds");
        }
    }
    private void runMethod(Runnable runnable) {
        long start = System.nanoTime();
        runnable.run();
        long end = System.nanoTime();
        System.out.println(" : " + (end - start) + " nanoseconds");
    }
}
