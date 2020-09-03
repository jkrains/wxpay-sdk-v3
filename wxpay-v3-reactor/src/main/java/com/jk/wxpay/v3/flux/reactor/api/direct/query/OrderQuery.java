package com.jk.wxpay.v3.flux.reactor.api.direct.query;

import com.jk.wxpay.v3.commons.bean.direct.result.OrderQueryResult;
import reactor.core.publisher.Mono;

public interface OrderQuery {
    /**
     * 根据id 进行查询。
     * @param id
     * @return
     */
    Mono<OrderQueryResult> query(String mchId, String id);
}
