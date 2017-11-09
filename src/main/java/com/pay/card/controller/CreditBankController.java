package com.pay.card.controller;

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
 */
@RestController
public class CreditBankController extends BaseController {

    public static final String JSON_UTF_8 = "application/json; charset=UTF-8";

    @Autowired
    private CreditBankService bankService;

    @RequestMapping(value = "/api/bank")
    public JsonResultView<?> getBanks() {

        System.out.println("----------");
        List<CreditBank> rv = bankService.getBanks();
        CardBuildContext buildContext = apiHelper.getBuildContext();
        apiHelper.getModelBuilder().buildMulti(rv, buildContext);
        return new JsonResultView<>().setPayload(apiHelper.getViewMapper().map(rv, buildContext));
    }

}
