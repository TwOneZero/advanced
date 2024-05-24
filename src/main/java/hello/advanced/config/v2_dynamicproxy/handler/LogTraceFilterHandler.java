package hello.advanced.config.v2_dynamicproxy.handler;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;
    private final String[] patterns;

    public LogTraceFilterHandler(Object target, LogTrace logTrace, String[] patterns) {
        this.target = target;
        this.logTrace = logTrace;
        this.patterns = patterns;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //메서드 이름 필터
        String methodName = method.getName();
        if (!PatternMatchUtils.simpleMatch(patterns,methodName)){
            return method.invoke(target, args);
        }

        TraceStatus status = null;
        try {
            //각 레이어의 메소드 메타정보 얻기
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = logTrace.begin(message);
            //target 호출
//            String result = target.request(itemId);

            //동적 프록시를 통해 target.request() 를 호출한다.
            Object result = method.invoke(target,args);
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);

            throw e;
        }
    }



}
