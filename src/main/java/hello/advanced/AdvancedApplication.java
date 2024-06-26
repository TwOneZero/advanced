package hello.advanced;

import hello.advanced.config.AppV1Config;
import hello.advanced.config.AppV2Config;
import hello.advanced.config.v1_proxy.ConcreteProxyConfig;
import hello.advanced.config.v1_proxy.InterfaceProxyConfig;
import hello.advanced.config.v2_dynamicproxy.DynamicProxyBasicConfig;
import hello.advanced.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import hello.advanced.config.v3_proxyfacotory.ProxyFactoryConfigV1;
import hello.advanced.config.v4_postprocessor.BeanPostProcessorConfig;
import hello.advanced.config.v5_autoproxy.AutoProxyConfig;
import hello.advanced.config.v6_aop.aspect.AopConfig;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import({AppV1Config.class, AppV2Config.class})
//@Import(ConcreteProxyConfig.class)
//@Import(DynamicProxyFilterConfig.class)
//@Import(ProxyFactoryConfigV1.class)
//@Import(BeanPostProcessorConfig.class)
//@Import(AutoProxyConfig.class)
@Import(AopConfig.class)
@SpringBootApplication(scanBasePackages = "hello.advanced.app.v3")
public class AdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedApplication.class, args);
    }

    @Bean
    public LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }

}
