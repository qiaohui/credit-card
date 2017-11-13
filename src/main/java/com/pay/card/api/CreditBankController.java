package com.pay.card.api;

import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.card.model.CreditBank;
import com.pay.card.service.CreditBankService;
import com.pay.card.view.JsonResultView;
import com.pay.card.web.context.CardBuildContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author qiaohui
 * 
 *         测试用例
 *
 */
@Api("测试 swagger api doc")
@RestController
public class CreditBankController extends BaseController {

    @Autowired
    private CreditBankService bankService;

    @ApiOperation(value = "获取所有银行，银行 id 转 uuid", httpMethod = HttpGet.METHOD_NAME)
    @RequestMapping(value = "/api/bank")
    public JsonResultView<?> getBanks() {
        List<CreditBank> rv = bankService.getBanks();
        CardBuildContext buildContext = apiHelper.getBuildContext();
        apiHelper.getModelBuilder().buildMulti(rv, buildContext);
        return new JsonResultView<>().setData(apiHelper.getViewMapper().map(rv, buildContext));
    }

}
