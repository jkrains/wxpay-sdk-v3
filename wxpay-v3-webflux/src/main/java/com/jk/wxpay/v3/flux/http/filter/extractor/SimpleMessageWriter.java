package com.jk.wxpay.v3.flux.http.filter.extractor;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.core.ResolvableType;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.HttpMessageWriter;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class SimpleMessageWriter<T> implements HttpMessageWriter<T> {

    private final Consumer<T> consumer;
    private final List<MediaType> mediaTypes;

    SimpleMessageWriter(Consumer<T> consumer) {
        this.consumer = consumer;
        this.mediaTypes = Collections.singletonList(MediaType.ALL);
    }

    @Override
    public List<MediaType> getWritableMediaTypes() {
        return mediaTypes;
    }

    @Override
    public boolean canWrite(ResolvableType elementType, MediaType mediaType) {
        return true;
    }

    /**
     * 这个函数会接收到body 数据，可以在这里获取body 数据，并传递给consumer.
     * @param inputStream
     * @param elementType
     * @param mediaType
     * @param message
     * @param hints
     * @return
     */
    @Override
    public Mono<Void> write(
            Publisher<? extends T> inputStream,
            ResolvableType elementType,
            MediaType mediaType,
            ReactiveHttpOutputMessage message,
            Map<String, Object> hints
    ) {
        inputStream.subscribe(new OneValueConsumption<>(consumer));
        return Mono.empty();
    }

    private class OneValueConsumption<T> implements Subscriber<T> {

        private final Consumer<T> consumer;
        private int remainedAccepts;

        public OneValueConsumption(Consumer<T> consumer) {
            this.consumer = Objects.requireNonNull(consumer);
            this.remainedAccepts = 1;
        }

        @Override
        public void onSubscribe(Subscription s) {
            s.request(1);
        }

        @Override
        public void onNext(T t) {
            if (remainedAccepts > 0) {
                consumer.accept(t);
                remainedAccepts -= 1;
            } else {
                throw new RuntimeException("No more values can be consumed");
            }
        }

        @Override
        public void onError(Throwable t) {
            throw new RuntimeException("Single value was not consumed", t);
        }

        @Override
        public void onComplete() {
            // nothing
        }
    }
}
