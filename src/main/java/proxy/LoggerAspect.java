package proxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {

    //公共切入点表达式
    @Pointcut("execution(* proxy.*.*(..))")
    public void Pointcut(){}



    //前置通知，目标方法执行前的通知
    @Before("Pointcut()")
    //@Before("execution(public int proxy.CalculatorImpl.add(int,int))")
    public void beforeAdviceMethod(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("方法执行前:  方法名:"+signature.getName()+"  参数:"+ Arrays.toString(args));
    }
    //后置通知，方法成功与否都会进行的通知
    @After("Pointcut()")
    public void AfterAdviceMethod(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("方法执行结束  方法名:"+signature.getName()+"  参数:"+ Arrays.toString(args));
    }
    //返回通知，方法成功返回后的通知
    @AfterReturning(value = "Pointcut()",returning="result")
    public void AfterReturningAdviceMethod(JoinPoint joinPoint,Object result){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        //System.out.println("方法返回后  方法名:"+signature.getName()+"  参数:"+ Arrays.toString(args));
        System.out.println("方法返回后  方法名:"+signature.getName()+"  结果:"+ result);
    }
    //异常通知，方法在只从执行过程中出现异常的通知
    @AfterThrowing(value = "Pointcut()",throwing = "ex")
    public void afterThrowingAdviceMethod(JoinPoint joinPoint,Throwable ex){
        System.out.println("方法执行过程中抛出异常 :  "+ex);
    }
    //环绕通知
    @Around(value = "Pointcut()")
    public Object around(ProceedingJoinPoint joinPoint){
        Object result=null;
        try {
            System.out.println("前置通知");
            result = joinPoint.proceed();
            System.out.println("返回通知");
        } catch (Throwable e) {
            System.out.println(e);
            System.out.println("异常通知");
        }finally {
            System.out.println("后置通知");
        }
        return result;
    }
    //一般环绕通知和上面那四种单独的通知不会同时使用，要么只使用环绕通知，要么使用上面4种通知中的某几种。





}
