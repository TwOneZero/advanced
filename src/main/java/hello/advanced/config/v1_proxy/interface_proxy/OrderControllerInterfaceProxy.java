package hello.advanced.config.v1_proxy.interface_proxy;

import hello.advanced.app.v1.OrderControllerV1;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {
    private final OrderControllerV1 target;
    private final LogTrace trace;

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            //실제 target 호출
            String result = target.request(itemId);
            //log 남기기
            trace.end(status);
            return result;
        }catch (IllegalStateException e){
            trace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
