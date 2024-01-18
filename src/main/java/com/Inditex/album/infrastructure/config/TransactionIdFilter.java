package com.inditex.album.infrastructure.config;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class TransactionIdFilter implements WebFilter {

    private static final String TRACE_HEADER = "X-Trace-Id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String traceId = exchange.getRequest().getHeaders().getFirst(TRACE_HEADER);
        traceId = (traceId != null && !traceId.isEmpty()) ? traceId : generateTraceId();

        MDC.put(TRACE_HEADER, traceId);

        return chain.filter(exchange);
    }

    private String generateTraceId() {
        return UUID.randomUUID().toString();
    }
}
