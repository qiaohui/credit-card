package com.pay.card.utils;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.github.phantomthief.model.builder.ModelBuilder;
import com.github.phantomthief.model.builder.impl.SimpleModelBuilder;
import com.github.phantomthief.view.mapper.ViewMapper;
import com.github.phantomthief.view.mapper.impl.DefaultViewMapperImpl;
import com.github.phantomthief.view.mapper.impl.OverrideViewMapper;
import com.pay.card.model.CreditBank;
import com.pay.card.view.CreditBankOverrideView;
import com.pay.card.view.CreditBankView;
import com.pay.card.web.context.CardBuildContext;

/**
 * 
 * @author qiaohui
 *
 *         扫描 view，数据绑定，存入 id、value命名空间
 */
@Service
public class ApiHelper {

    private SimpleModelBuilder<CardBuildContext> modelBuilder;
    private ViewMapper viewMapper;
    private OverrideViewMapper overrideViewMapper;
    private CardBuildContext buildContext;
    private CardBuildContext overrideBuildContext;

    public ViewMapper getViewMapper() {
        return viewMapper;
    }

    public OverrideViewMapper getOverrideViewMapper() {
        return overrideViewMapper;
    }

    public CardBuildContext getBuildContext() {
        return buildContext;
    }

    public CardBuildContext getOverrideBuildContext() {
        return overrideBuildContext;
    }

    public ModelBuilder<CardBuildContext> getModelBuilder() {
        return modelBuilder;
    }

    /**
     * 这个viewmapper存放临时的View映射定制
     * 
     * @param viewMapper
     * @return
     */
    public static final OverrideViewMapper ovMapper(ViewMapper viewMapper) {
        OverrideViewMapper overrideViewMapper = new OverrideViewMapper(viewMapper);
        overrideViewMapper.addMapper(CreditBank.class,
                (creditcard, overrideBuildContext) -> new CreditBankOverrideView(creditcard,
                        (CardBuildContext) overrideBuildContext));

        return overrideViewMapper;
    }

    /**
     * 这里是正常的Model到View的映射关系
     * 
     * @param pkg
     * @param ignoreViews
     * @return
     */
    public static final ViewMapper mapper() {
        DefaultViewMapperImpl viewMapper = new DefaultViewMapperImpl();
        viewMapper.addMapper(CreditBank.class,
                (buildContext, creditBank) -> new CreditBankView(creditBank, (CardBuildContext) buildContext));

        return viewMapper;
    }

    @PostConstruct
    private void init() {
        viewMapper = mapper();
        overrideViewMapper = ovMapper(viewMapper);
        buildContext = new CardBuildContext();
        overrideBuildContext = new CardBuildContext();
        modelBuilder = new SimpleModelBuilder<CardBuildContext>();
    }
}
