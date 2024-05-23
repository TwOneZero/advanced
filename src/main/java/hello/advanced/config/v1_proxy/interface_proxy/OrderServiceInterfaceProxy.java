package hello.advanced.config.v1_proxy.interface_proxy;

import hello.advanced.app.v1.OrderRepositoryV1;
import hello.advanced.app.v1.OrderServiceV1;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceV1 {

    private final OrderServiceV1 target;
    private final LogTrace trace;

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            //실제 target 호출
            target.orderItem(itemId);
            //log 남기기
            trace.end(status);
        }catch (IllegalStateException e){
            trace.exception(status, e);
            throw e;
        }
    }
}
