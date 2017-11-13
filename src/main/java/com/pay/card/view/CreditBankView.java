package com.pay.card.view;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CreditBankView {

    // private final CreditBank bank;
    //
    // private final CardBuildContext buildContext;
    //
    // public CreditBankView(CreditBank bank, CardBuildContext buildContext) {
    // this.bank = bank;
    // this.buildContext = buildContext;
    // }

    // public String getId() {
    // return UuidUtils.getUuid(bank.getId());
    // }
    //
    // public String getName() {
    // return bank.getName();
    // }

    public Long id;
    public String name;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
