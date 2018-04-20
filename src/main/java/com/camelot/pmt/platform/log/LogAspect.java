package com.camelot.pmt.platform.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gnerv
 * @Description 基础平台操作日志记录切面类
 * @date 2018年4月19日
 */
@Aspect
@Component
public class LogAspect {
	
    @Pointcut("execution(* com.camelot.pmt.platform.service..*.add*(..))")  
    public void logPlatformAdd(){} 
	
    @Pointcut("execution(* com.camelot.pmt.platform.service..*.query*(..))")  
    public void logPlatformQuery(){} 
	
    @Pointcut("execution(* com.camelot.pmt.platform.service..*.update*(..))")  
    public void logPlatformUpdate(){} 
    
	@Before("logPlatformAdd() || logPlatformQuery()")
	public void beforeService(JoinPoint joinPoint) {
		System.out.println("前置通知。。。");
	}
	
	@After("logPlatformAdd()")
	public void afterService(JoinPoint joinPoint) {
		System.out.println("后置通知。。。");
	}
	
	@Around("logPlatformUpdate()")
	public Object aroundService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("环绕前置通知。。。");
		Object proceed = proceedingJoinPoint.proceed();
		System.out.println("环绕后置通知。。。");
		return proceed;
	}
}
