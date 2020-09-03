package com.jk.wxpay.v3.flux.http.filter.extractor;


import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.ClientRequest;

/**
 * 获取body的内容。
 */
public class RequestExtractor {

    /**
     * 获取到一个插入的对象。
     * @param <T>
     */
    public interface InsertionReceiver<T> {
        T receiveValue(BodyInserter<?, ? extends ReactiveHttpOutputMessage> bodyInserter);

        static <T> InsertionReceiver<T> forClass(Class<T> clazz) {
            return new SimpleValueReceiver(clazz);
        }
    }

    /**
     * 这个方法获取body 的内容。 直接返回，采用同步的方法。
     * @param request
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T extractBody(ClientRequest request, Class<T> clazz) {
        InsertionReceiver<T> receiver = InsertionReceiver.forClass(clazz);
        return receiver.receiveValue(request.body());
    }


}
