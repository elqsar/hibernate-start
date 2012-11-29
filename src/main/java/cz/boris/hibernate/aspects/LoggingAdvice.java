package cz.boris.hibernate.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAdvice {

	Logger log = LoggerFactory.getLogger("Logging Aspect...");

	@Pointcut("execution(* cz.boris.hibernate.repository.*Repository+.*(..))")
	public void loggingAspect() {
	}

	@Around("loggingAspect()")
	public Object log(ProceedingJoinPoint pjp) {
		StopWatch sw = new StopWatch(pjp.getTarget().getClass().getName());
		sw.start(pjp.getSignature().getName());
		try {
			Object o = pjp.proceed();
			sw.stop();
			log.info(sw.prettyPrint());
			return o;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

}
