package com.pay.card.view;

import com.pay.card.model.CreditBank;
import com.pay.card.utils.UuidUtils;
import com.pay.card.web.context.CardBuildContext;

public class CreditBankOverrideView {

    private final CreditBank bank;

    private final CardBuildContext overrideBuildContext;

    public CreditBankOverrideView(CreditBank bank, CardBuildContext overrideBuildContext) {
        this.bank = bank;
        this.overrideBuildContext = overrideBuildContext;
    }

    public String getId() {
        return UuidUtils.getUuid(bank.getId());
    }

    public String getName() {
        return bank.getName();
    }

}
