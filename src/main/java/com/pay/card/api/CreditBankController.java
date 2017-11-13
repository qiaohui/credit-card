package com.pay.card.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.card.model.CreditBank;
import com.pay.card.service.CreditBankService;
import com.pay.card.view.JsonResultView;
import com.pay.card.web.context.CardBuildContext;

/**
 * 
 * @author qiaohui
 * 
 *         测试用例
 *
 */
@RestController
public class CreditBankController extends BaseController {

    @Autowired
    private CreditBankService bankService;

    @RequestMapping(value = "/api/bank")
    public JsonResultView<?> getBanks() {
        List<CreditBank> rv = bankService.getBanks();
        CardBuildContext buildContext = apiHelper.getBuildContext();
        apiHelper.getModelBuilder().buildMulti(rv, buildContext);
        return new JsonResultView<>().setData(apiHelper.getViewMapper().map(rv, buildContext));
    }

}
