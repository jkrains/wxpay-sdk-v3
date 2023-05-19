package com.jk.wxpay.v3.block.api.direct.query;

import com.jk.wxpay.v3.commons.bean.direct.result.OrderQueryResult;
import com.jk.wxpay.v3.commons.exception.WxErrorException;

public interface OrderQuery {
    /**
     * 根据id 进行查询。
     * @param id
     * @return
     */
    OrderQueryResult query(String mchId, String id) throws WxErrorException;
}
