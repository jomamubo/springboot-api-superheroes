package com.w2m.springboot.api.superheroes.annotation;

import org.slf4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
// @Slf4j
@Aspect
public class CronometradoImpl {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(CronometradoImpl.class);
	
	@Around("@annotation(com.w2m.springboot.api.superheroes.annotation.Cronometrado)")
	public Object medirTiempo(ProceedingJoinPoint proceedingJoinPoint) {
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object proceed=null;
		try {
			proceed = proceedingJoinPoint.proceed();
			stopWatch.stop();
			log.info("Metodo [" + proceedingJoinPoint.getSignature().getName() +"] ejecutado en [" + 
					stopWatch.getTotalTimeMillis() + "] msegs.");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return proceed;
		
	}
}
