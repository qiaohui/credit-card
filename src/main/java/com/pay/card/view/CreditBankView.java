package com.pay.card.view;

import com.pay.card.model.CreditBank;
import com.pay.card.utils.UuidUtils;
import com.pay.card.web.context.CardBuildContext;

public class CreditBankView {

    private final CreditBank bank;

    private final CardBuildContext buildContext;

    public CreditBankView(CreditBank bank, CardBuildContext buildContext) {
        this.bank = bank;
        this.buildContext = buildContext;
    }

    public String getId() {
        return UuidUtils.getUuid(bank.getId());
    }

    public String getName() {
        return bank.getName();
    }

}
