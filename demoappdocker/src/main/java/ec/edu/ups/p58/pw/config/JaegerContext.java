package ec.edu.ups.p58.pw.config;

import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;

public class JaegerContext {
    private static final Tracer tracer = JaegerConfig.initTracer();

    public static Tracer getTracer() {
        return tracer;
    }

    public static void init() {
        GlobalTracer.register(tracer);
    }

    public static void close() {
        if (tracer instanceof io.jaegertracing.internal.JaegerTracer) {
            ((io.jaegertracing.internal.JaegerTracer) tracer).close();
        }
    }
}
