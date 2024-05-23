package hello.advanced.config.v1_proxy.interface_proxy;

import hello.advanced.app.v1.OrderRepositoryV1;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

    private final OrderRepositoryV1 target;
    private final LogTrace trace;


    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");
            //실제 target 호출
            target.save(itemId);
            //log 남기기
            trace.end(status);
        }catch (IllegalStateException e){
            trace.exception(status, e);
            throw e;
        }
    }
}
