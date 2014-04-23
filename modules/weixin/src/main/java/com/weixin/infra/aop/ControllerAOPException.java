package com.weixin.infra.aop;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.weixin.infra.Exceptions;

/**
 * <pre>
 *     Spring Controller类的意外处理
 * </pre>
 * 
 * @version 1.0, 2012-7-18
 * @author chenwentao
 * 
 */
@Aspect
@Component
public class ControllerAOPException {

	// Obtain a suitable logger.
	private static Logger logger = Logger.getLogger("ControllerAOPException");

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void anyRequestMappingMethod() {
	}

	@Pointcut("execution(* com.weixin.web.controller.*.*(..))")
	public void anyControllerMethod() {
	}

	@Pointcut("execution(public * *(..))")
	public void anyPublicMethod() {
	}

	@Pointcut("anyControllerMethod() && anyPublicMethod() && anyRequestMappingMethod()")
	public void anyInteractionMethod() {
	}

	@Before("anyInteractionMethod()")
	public void recordIP(JoinPoint pjp) {
		System.out.println("123");
	}

	//@AfterThrowing(value = "anyInteractionMethod()", throwing = "e")
	@AfterThrowing(pointcut = "execution(* com.weixin.web.controller..*(..))", throwing = "e")
	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		String stuff = signature.toString();
		String arguments = Arrays.toString(joinPoint.getArgs());

		StringBuffer sb = new StringBuffer();
		sb.append("类:").append(stuff).append("\n");
		sb.append("方法:").append(methodName).append("\n");
		sb.append("参数:").append(arguments).append("\n");
		sb.append("错误:").append("\n")
				.append(Exceptions.getStackTraceAsString(e)).append("\n");

		logger.error(sb.toString());
	}

}
