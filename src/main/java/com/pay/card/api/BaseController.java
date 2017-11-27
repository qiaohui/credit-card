/**
 *
 */
package com.pay.card.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pay.card.utils.ApiHelper;

/**
 * @author qiaohui
 */
@Controller
public abstract class BaseController {

    protected final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    protected ApiHelper apiHelper;

    public static final long timeout = 10L * 1000; // 自定义超时时间 1秒

}
