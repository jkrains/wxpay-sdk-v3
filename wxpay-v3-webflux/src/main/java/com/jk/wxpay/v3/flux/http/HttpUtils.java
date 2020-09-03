package com.jk.wxpay.v3.flux.http;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.List;

public class HttpUtils {

    private static DefaultDataBufferFactory defaultDataBufferFactory;

    public static DefaultDataBufferFactory getDefaultDataBufferFactory() {
        if (defaultDataBufferFactory == null) {
            defaultDataBufferFactory = new DefaultDataBufferFactory();
        }
        return defaultDataBufferFactory;
    }

    /**
     * 将data buffer 数据转换成json 字符串对象
     * @param dataBuffer
     * @return
     */
    public static String dataBufferToJson(DataBuffer dataBuffer) {
        String bodyString = dataBuffer.toString(Charset.defaultCharset());
        return bodyString;
    }

    /**
     * 将返回的ClientResponse的body 转换成JsonString
     * @param response
     * @return
     */
    public static Mono<String> toJson(ClientResponse response) {
        return toDataBuffer(response).map(db -> dataBufferToJson(db));
    }

    /**
     * 获取到 data buffer list 可读入的数据字节数。
     * @param dataBufferList
     * @return
     */
    public static int dataBuffersReadableBytes(List<DataBuffer> dataBufferList) {
        int length = 0;
        for (DataBuffer dataBuffer : dataBufferList) {
            length += dataBuffer.readableByteCount();
        }
        return length;
    }

    public static Mono<DataBuffer> listToDataBuffer(Mono<List<DataBuffer>> collectList) {
        return collectList.map(blist -> {
            DefaultDataBufferFactory bufferFactory = getDefaultDataBufferFactory();
            DefaultDataBuffer dataBuffer = bufferFactory.allocateBuffer(dataBuffersReadableBytes(blist));
            blist.forEach(db -> {
                dataBuffer.write(db);
            });
            return dataBuffer;
        });
    }

    /**
     *
     * 将 clientResponse 内容转换成DataBuffer.
     * @param response
     * @return
     */
    public static Mono<DataBuffer> toDataBuffer(ClientResponse response) {
        return listToDataBuffer(response.body(BodyExtractors.toDataBuffers()).collectList());
    }
}
