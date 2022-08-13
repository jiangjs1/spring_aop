package proxy;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)//通过order注解来配置不同切面的优先级，数字越小，优先级越高
public class ValidAspect {
    @Before("proxy.LoggerAspect.Pointcut()")
    public void beforeAdciveMethod(){
        System.out.println("ValidAspect 前置通知");
    }
}
