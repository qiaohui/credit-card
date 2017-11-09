package com.pay.card.web.context;

import com.github.phantomthief.model.builder.context.impl.SimpleBuildContext;

/**
 * 
 * @author qiaohui
 *
 *         自定义BuildContext
 */
public class CardBuildContext extends SimpleBuildContext {

    private int visitor;

    public int getVisitor() {
        return visitor;
    }

    public CardBuildContext setVisitor(int visitor) {
        this.visitor = visitor;
        return this;
    }

}
