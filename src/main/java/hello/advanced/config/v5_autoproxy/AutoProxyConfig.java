package hello.advanced.config.v5_autoproxy;


import hello.advanced.config.AppV1Config;
import hello.advanced.config.AppV2Config;
import hello.advanced.config.v3_proxyfacotory.advice.LogTraceAdvice;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {

//    @Bean
    public Advisor advisor1(LogTrace logTrace) {
        //pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        //advisor = pointcut + advice
        return new DefaultPointcutAdvisor(pointcut, advice);
    }


    @Bean
    public Advisor advisor2(LogTrace logTrace) {
        //pointcut -> PointCut Expression
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* hello.advanced.app..*(..))");

        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        //advisor = pointcut + advice
        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    @Bean
    public Advisor advisor3(LogTrace logTrace) {
        //pointcut -> PointCut Expression
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //nolog이름이 들어간 메소드 요청은 제외 추가
        pointcut.setExpression("execution(* hello.advanced.app..*(..)) && !execution(* hello.advanced.app..nolog(..))");

        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        //advisor = pointcut + advice
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
