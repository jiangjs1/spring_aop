import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import proxy.Calculator;


public class testAop {
    @Test
     public void test1(){
        ApplicationContext ioc=new ClassPathXmlApplicationContext("sprinf_aop_annoation.xml");
        Calculator bean = ioc.getBean(Calculator.class);//这里是get不到CalculatorImpl这个bean的，只能get它实现的接口的bean
        bean.add(1,0);
    }

}
