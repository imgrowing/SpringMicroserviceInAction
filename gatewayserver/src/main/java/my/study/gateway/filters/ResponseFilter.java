package my.study.gateway.filters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

import static my.study.gateway.filters.FilterUtils.CORRELATION_ID;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ResponseFilter {

    private final FilterUtils filterUtils;

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                String correlationId = filterUtils.getCorrelationId(requestHeaders);

                log.warn("Adding the correlation id to the outbound headers. {}", correlationId);
                exchange.getResponse().getHeaders().add(CORRELATION_ID, correlationId);

                log.warn("Completing outgoing request for {}.", exchange.getRequest().getURI());
            }));
        };
    }

}
