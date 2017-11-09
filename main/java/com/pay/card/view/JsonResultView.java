/**
 *
 */
package com.pay.card.view;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * @author qiaohui
 * 
 *         注意，这个 view 才是所有 api 封装返回的 class
 */
public class JsonResultView<T> {

    private int code;

    private T payload;

    private Object payload2;

    public int getCode() {
        return code;
    }

    @JsonUnwrapped
    public T getPayload() {
        return payload;
    }

    public Object getPayload2() {
        return payload2;
    }

    public JsonResultView<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public JsonResultView<T> setPayload(T payload) {
        this.payload = payload;
        return this;
    }

    public JsonResultView<T> setPayload2(Object payload2) {
        this.payload2 = payload2;
        return this;
    }

}
