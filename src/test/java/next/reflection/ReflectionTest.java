package next.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import next.Student;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.*;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void privateFieldAccess() throws NoSuchFieldException, IllegalAccessException {
        Class<Student> clazz = Student.class;
        Student student = new Student();

        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);

        field.set(student, "재성");
        assertThat(field.get(student))
                .isEqualTo("재성");
    }

    @DisplayName("인자를 가진 생성자의 인스턴스 생성")
    @Test
    void constructorWithArgs() throws Exception {
        // given
        Class<Student> clazz = Student.class;
        Constructor<Student> constructor = clazz.getDeclaredConstructor(String.class, int.class);

        // when
        Student student = constructor.newInstance("재성", 28);

        // then
        assertThat(student).extracting("name", "age")
                .containsExactly("재성", 28);
    }



    @Nested
    class 테스트1_리플렉션을_이용해서_클래스와_메소드의_정보를_정확하게_출력해야_한다 {

        @DisplayName(": 클래스의 정보를 확인할 수 있다.")
        @Test
        void getClassInfo() {
            // given
            Class<Question> clazz = Question.class;

            // when
            String name = clazz.getName();
            int modifiers = clazz.getModifiers();

            // then
            assertThat(name).isEqualTo("next.reflection.Question");
            assertThat(modifiers).isEqualTo(1);
        }

        @DisplayName(": 클래스의 필드 정보를 확인할 수 있다.")
        @Test
        void getName() throws NoSuchFieldException, IllegalAccessException {
            // given
            Class<Question> clazz = Question.class;

            // when
            Field[] fields = clazz.getFields();
            Field declaredField = clazz.getDeclaredField("questionId");

            // then
            // 왜냐하면 public field만 가져옴
            assertThat(fields).hasSize(0);

            // declaredField는 private field도 가져옴
            assertThat(declaredField)
                    .extracting("name", "modifiers")
                    .containsExactly("questionId", 0x00000002);
        }


        @DisplayName("setAccessible을 하지 않고 private 필드에 접근하면 IllegalAccessException이 발생한다.")
        @Test
        void testMethodNameHere() throws NoSuchFieldException {
            // given
            Class<Question> clazz = Question.class;

            Question question = new Question("test", "test", "test");

            // when
            Field[] fields = clazz.getFields();
            Field declaredField = clazz.getDeclaredField("questionId");

            // then
        }

        @DisplayName("private 필드를 setAccessible을 이용해서 접근할 수 있다.")
        @Test
        void setAccessible() {
            // given


            // when

            // then
        }

        @DisplayName(": 클래스의 생성자 정보를 확인할 수 있다.")
        @Test
        void getConstructors() {
            // given
            Class<Question> clazz = Question.class;

            // when
            Constructor<?>[] constructors = clazz.getConstructors();
            Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();

            // then


        }

    }
    @Test
    @DisplayName("테스트1: 리플렉션을 이용해서 클래스와 메소드의 정보를 정확하게 출력해야 한다.")
    public void showClass() {
        SoftAssertions s = new SoftAssertions();
        Class<Question> clazz = Question.class;

        assertThat(clazz.getName())
                .isEqualTo("next.reflection.Question");
        assertThat(clazz.getModifiers()).isEqualTo(0x00000001);
        assertThat(clazz.getSuperclass().getName())
                .isEqualTo("java.lang.Object");

        Constructor[] constructors = clazz.getConstructors();


        Field[] filed = clazz.getFields();

        Method[] methods = clazz.getMethods();



    }

    @DisplayName("")
    @Test
    void testMethodNameHere() {
        // given


        // when

        // then
    }

    @Test
    public void constructor() throws Exception {
        Class<Question> clazz = Question.class;
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();
            logger.debug("paramer length : {}", parameterTypes.length);
            for (Class paramType : parameterTypes) {
                logger.debug("param type : {}", paramType);
            }
        }
    }
}
