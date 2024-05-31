package hello.advanced.config.v6_aop.aspect;


import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Slf4j
@Aspect
public class LogTraceAspect {

    private final LogTrace logTrace;


    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    @Around("execution(* hello.advanced.app..*(..))") //pointcut
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;

//        // 실제 호출 대상
//        log.info("target={}", joinPoint.getTarget());
//        //전달인자
//        log.info("getArgs={}", joinPoint.getArgs());
//        //join point 시그 니처
//        log.info("getSignature={}", joinPoint.getSignature());

        try {
//            Method method = invocation.getMethod();
//            String message = method.getDeclaringClass().getSimpleName() + "."
//                    + method.getName() + "()";
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            //로직 호출
            Object result = joinPoint.proceed();
            logTrace.end(status);

            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
