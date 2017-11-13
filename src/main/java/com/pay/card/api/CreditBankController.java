package com.pay.card.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.card.model.CreditBank;
import com.pay.card.service.CreditBankService;
import com.pay.card.utils.BeanMapper;
import com.pay.card.view.CreditBankView;
import com.pay.card.view.JsonResultView;

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
        List<CreditBank> rv = bankService.getBanks();
        // CardBuildContext buildContext = apiHelper.getBuildContext();
        // apiHelper.getModelBuilder().buildMulti(rv, buildContext);
        // return new
        // JsonResultView<>().setPayload(apiHelper.getViewMapper().map(rv,
        // buildContext));
        return new JsonResultView<>().setPayload(BeanMapper.mapList(rv, CreditBank.class, CreditBankView.class));
    }

}
