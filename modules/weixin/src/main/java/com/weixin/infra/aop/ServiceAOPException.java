/*
 * @(#)ControllerAOPException.java 2012-7-18
 * 
 * Copy Right@ chenwt
 */

package com.weixin.infra.aop;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
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
public class ServiceAOPException {

	private static Logger logger = Logger.getLogger("ServiceAOPException");

	// // @Pointcut("execution (* com.wrisc.web.controller..*.*(..))")
	// @Pointcut("execution (* com.wrisc.domain.service.admin.IndexManageService.*(..))")
	// private void pointcut() {
	//
	// }

	// @Before("pointcut()")
	// public void before() {
	// System.out.println("ssbefore");
	//
	// }

	@AfterThrowing(pointcut = "execution(* com.weixin.domain.service..*(..))", throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint, Exception exception) {
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		String stuff = signature.toString();
		String arguments = Arrays.toString(joinPoint.getArgs());

		StringBuffer sb = new StringBuffer();
		sb.append("类:").append(stuff).append("\n");
		sb.append("方法:").append(methodName).append("\n");
		sb.append("参数:").append(arguments).append("\n");
		sb.append("错误:").append("\n")
				.append(Exceptions.getStackTraceAsString(exception))
				.append("\n");

		logger.error(sb.toString());
	}

}
