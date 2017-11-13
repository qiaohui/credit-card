/**
 *
 */
package com.pay.card.view;

/**
 * @author qiaohui
 * 
 *         注意，这个 view 才是所有 api 封装返回的 class
 */
public class JsonResultView<T> {

    /**
     * code
     */
    private int code;

    /**
     * 这里存放返回的主要数据
     */
    private T data;

    /**
     * 这里可以存放需要返回的 token ，可以为空
     */
    private Object payload;

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public Object getPayload() {
        return payload;
    }

    public JsonResultView<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public JsonResultView<T> setData(T data) {
        this.data = data;
        return this;
    }

    public JsonResultView<T> setPayload(Object payload) {
        this.payload = payload;
        return this;
    }

}
