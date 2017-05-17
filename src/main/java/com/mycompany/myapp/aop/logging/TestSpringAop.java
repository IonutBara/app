package com.mycompany.myapp.aop.logging;

import com.mycompany.myapp.domain.Company;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by ibara on 4/26/2017.
 */
@Aspect
public class TestSpringAop {
    private final Logger LOGGER = LoggerFactory.getLogger(TestSpringAop.class);

    @Before("execution(* com.mycompany.myapp.web.rest.controllers.JobResource.*(..))")
    public void test() {
        LOGGER.debug("===========================>");
        LOGGER.debug("TestSpringAop executed!");
        LOGGER.debug("TestSpringAop executed!");
    }

    @After("execution(* com.mycompany.myapp.web.rest.controllers.JobResource.listAllJobs(..))")
    public void afterTest(JoinPoint joinPoint) {
        LOGGER.debug("Jointpoint afterTest: {}", joinPoint.getSignature().getName());
        joinPoint.getTarget();
    }

    //execution -> parametrul este numele metodei
    @Pointcut("execution(* get*(..))")
    public void allGetters() {
    }

    /*@Before("allGetters()")
    public void adviceOne() {
        LOGGER.debug("callled!");
    }

    @Pointcut("execution(* * com.mycompany.myapp.web.rest.controllers.*(..))")
    public void allcontrollersMehods() {
    }

    //within -> parametrul este numele clasei
    @Pointcut("within(com.mycompany.myapp.web.rest.controllers.JobResource)")
    public void allJobResourceControllerMethods() {
    }

    @Pointcut("args(name)")
    public void stringAsArgument(String name) {
        LOGGER.debug("A method which takes String type arguments has been called");
        LOGGER.debug("Name: {}", name);
    }

    @Before("allGetters() && allcontrollersMehods()")
    public void all1() {
    }

    @After("args(String)")
    public void afterAdvice(JoinPoint joinPoint) {
        LOGGER.info("after {}", joinPoint.toString());
    }

    @AfterReturning(pointcut = "args(name)", returning = "returnString")
    public void afterReturningAdvice(String name, Object returnString) {
        LOGGER.debug("Executing advice if pointcut not throw an Exception");
    }

    @AfterThrowing(pointcut = "args(name)", throwing = "ex")
    public void afterThrowAdvice(String name, Exception ex) {
        LOGGER.debug("Executing if pointcut throw an Exception");
        LOGGER.info("We are using the return parameter: {}", name);
        LOGGER.error("Throwing exception : {}", ex);
    }*/

    @Pointcut("execution(* com.mycompany.myapp.repository.CompanyRepository.findAll(..))")
    public void companyResourcePointcut() {
    }

    @Around("companyResourcePointcut()")
    public void myAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        LOGGER.info("before executing myAroundAdvice..");
        try {
            @SuppressWarnings("unchecked")
            ArrayList<Company> lista = (ArrayList<Company>) proceedingJoinPoint.proceed();
            for (Company company : lista) {
                LOGGER.debug("Company information: {}", company.toString());
            }
            lista.forEach(s -> LOGGER.debug("value = {}", s.toString()));
            lista.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
            LOGGER.info("after executing myAroundAdvice..");
        } catch (Throwable throwable) {
            LOGGER.error("Throwable occurred while executing around advice example");
        }
    }

    @Pointcut("within(com.mycompany.myapp.web.rest.controllers.CompanyResource)")
    public void companyPointcut() {}

    @Pointcut("execution(* com.mycompany.myapp.web.rest.controllers.CompanyResource.createCompany(..))")
    public void newCompanyCreated() {}

    @Before("companyPointcut()")
    public void companyResourceCalled(JoinPoint joinPoint) {
        LOGGER.debug("The Company REST Controller has been called.");
        LOGGER.debug("Method : {} will be executed!", joinPoint.getSignature().getName());
    }

    @AfterReturning("newCompanyCreated()")
    public void newCompanyAdded(JoinPoint joinPoint) {
        LOGGER.info("INFO : The COMPANY : {} was created", joinPoint.getArgs()[0]);
    }

    @AfterThrowing(pointcut = "companyPointcut()", throwing = "ex")
    public void exceptionThrownInCompanies(Exception ex) {
        LOGGER.error("EXCEPTION HAS BEEN THROWN : Stacktrace => {}", ex);
    }
}
