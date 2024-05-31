package ec.edu.ups.p58.pw.config;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;

public class JaegerConfig {
    private static final String SERVICE_NAME = "demoappdocker";

    public static JaegerTracer initTracer() {
        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv()
                .withType(System.getenv("JAEGER_SAMPLER_TYPE"))
                .withParam(Integer.parseInt(System.getenv("JAEGER_SAMPLER_PARAM")));

        Configuration.SenderConfiguration senderConfig = Configuration.SenderConfiguration.fromEnv()
                .withAgentHost(System.getenv("JAEGER_AGENT_HOST"))
                .withAgentPort(Integer.parseInt(System.getenv("JAEGER_AGENT_PORT")));

        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv()
                .withSender(senderConfig);

        Configuration config = new Configuration(SERVICE_NAME)
                .withSampler(samplerConfig)
                .withReporter(reporterConfig);

        return config.getTracer();
    }
}
