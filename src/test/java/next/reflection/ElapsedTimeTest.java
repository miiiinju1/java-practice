package next.reflection;

import next.ElapsedTime;

public class ElapsedTimeTest {

    @ElapsedTime
    public void test1()  {
//        System.out.println("Running Test1");
    }

    @ElapsedTime
    public void test2() {
        System.out.println("Running Test2");
    }

    @ElapsedTime
    public void test3() {
        System.out.println("Running Test3");
    }
}
