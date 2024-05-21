package hello.advanced.trace.strategy;


import hello.advanced.trace.strategy.code.strategy.ContextV2;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    /**
     * 전랽 패턴 사용
     */
    @Test
    void strategyV1() {
        ContextV2 context1 = new ContextV2();

        context1.execute(new StrategyLogic1());
        context1.execute(new StrategyLogic2());

    }

    /**
     * 전략 패턴 사용 -> 람다
     */
    @Test
    void strategyV2() {
        ContextV2 context1 = new ContextV2();

        context1.execute(() -> log.info("비즈니스 로직 1 실행"));
        context1.execute(() -> log.info("비즈니스 로직 2 실행"));

    }

}
