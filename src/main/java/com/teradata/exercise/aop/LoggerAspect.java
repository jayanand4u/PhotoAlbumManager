package com.teradata.exercise.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
	private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
	
	/**
	 * This method logs the time taken to complete controller method execution.
	 * @param jp
	 * @return Object
	 * @throws Throwable
	 */
	@Around("(execution(* com.teradata.exercise.controller.*.*(..))"
			+ " && @annotation("
			+ "org.springframework.web.bind.annotation.RequestMapping)) )")
	public Object controllerAdvice(ProceedingJoinPoint jp) throws Throwable {
		String methodFQDN = jp.getTarget().getClass().getName() + "."
				+ jp.getSignature().getName();
		long startTime = System.currentTimeMillis();
		try {
			return jp.proceed();
		} finally {
			logger.info("Time taken for the execution " + methodFQDN+ "() ..! "+ (System.currentTimeMillis() - startTime + " ms"));
		}

	}
}
