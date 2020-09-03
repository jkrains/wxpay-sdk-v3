package com.jk.wxpay.v3.flux.http.filter.extractor;


import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class SimpleValueReceiver<T> implements RequestExtractor.InsertionReceiver<T> {

    private static final Object DUMMY = new Object();

    private final Class<T> clazz;
    private final AtomicReference<Object> reference;

    SimpleValueReceiver(Class<T> clazz) {
        this.clazz = clazz;
        this.reference = new AtomicReference<>(DUMMY);
    }

    @Override
    public T receiveValue(BodyInserter<?, ? extends ReactiveHttpOutputMessage> bodyInserter) {

        BodyInserter<?, ReactiveHttpOutputMessage> inserter = (BodyInserter<?, ReactiveHttpOutputMessage>) bodyInserter;
        inserter.insert(
                MinimalHttpOutputMessage.INSTANCE,
                new SingleWriterContext(new SimpleMessageWriter<>(reference::set))
        );

        Object value = reference.get();
        reference.set(DUMMY);

        T validatedValue;

        if (value == DUMMY) {
            throw new RuntimeException("Value was not received, Check your inserter worked properly");
        } else if (!clazz.isAssignableFrom(value.getClass())) {
            throw new RuntimeException(
                    "Value has unexpected type ("
                            + value.getClass().getTypeName()
                            + ") instead of (" + clazz.getTypeName() + ")");
        } else {
            validatedValue = clazz.cast(value);
        }

        return validatedValue;
    }


    private static class MinimalHttpOutputMessage implements ReactiveHttpOutputMessage {

        public static MinimalHttpOutputMessage INSTANCE = new MinimalHttpOutputMessage();

        private MinimalHttpOutputMessage() {
        }

        @Override
        public HttpHeaders getHeaders() {
            return HttpHeaders.EMPTY;
        }

        @Override
        public DataBufferFactory bufferFactory() {
            return null;
        }

        @Override
        public void beforeCommit(Supplier<? extends Mono<Void>> action) {
        }

        @Override
        public boolean isCommitted() {
            return false;
        }

        @Override
        public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
            return null;
        }

        @Override
        public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
            return null;
        }

        @Override
        public Mono<Void> setComplete() {
            return null;
        }
    }

    private class SingleWriterContext implements BodyInserter.Context {

        private final List<HttpMessageWriter<?>> singleWriteList;
        public SingleWriterContext(HttpMessageWriter<?> writer) {
            this.singleWriteList = Arrays.asList(writer);
        }

        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return singleWriteList;
        }

        @Override
        public Optional<ServerHttpRequest> serverRequest() {
            return Optional.empty();
        }

        @Override
        public Map<String, Object> hints() {
            return null;
        }
    }

}
