package hello.advanced.trace.threadlocal;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            fieldService.logic("userA");
        };
        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");


        threadA.start();
        fieldService.sleep(2000); // 동시성 문제가 발생 X, 충분히 간격이 있음
        fieldService.sleep(100); // 동시성 문제가 발생 O, Thread A 실행 중 B도 0.1 초 뒤에 실행
        threadB.start();
        fieldService.sleep(3000); // 메인 쓰레드 종료 대기
        log.info("main exit");

    }

}
