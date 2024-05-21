package hello.advanced.helloTrace;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import org.junit.jupiter.api.Test;


class HelloTraceV2Test {


    @Test
    void begin_end() {
        HelloTraceV2 traceV1 = new HelloTraceV2();

        TraceStatus status1 = traceV1.begin("hello1");
        TraceStatus status2 = traceV1.beginSync(status1.getTraceId(),"hello2");

        traceV1.end(status2);
        traceV1.end(status1);
    }

    @Test
    void begin_exception() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}