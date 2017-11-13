package com.pay.card.utils;

import java.util.Collections;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.github.phantomthief.model.builder.ModelBuilder;
import com.github.phantomthief.model.builder.impl.SimpleModelBuilder;
import com.github.phantomthief.view.mapper.ViewMapper;
import com.github.phantomthief.view.mapper.impl.DefaultViewMapperImpl;
import com.pay.card.model.CreditBank;
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

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ApiHelper.class);
    private static final String VIEW_PATH = "com.pay.card.view";

    private SimpleModelBuilder<CardBuildContext> modelBuilder;
    private ViewMapper viewMapper;
    private CardBuildContext buildContext;

    public ViewMapper getViewMapper() {
        return viewMapper;
    }

    public CardBuildContext getBuildContext() {
        return buildContext;
    }

    public ModelBuilder<CardBuildContext> getModelBuilder() {
        return modelBuilder;
    }

    public static final ViewMapper scan(String pkg, Set<Class<?>> ignoreViews) {
        DefaultViewMapperImpl viewMapper = new DefaultViewMapperImpl();
        viewMapper.addMapper(CreditBank.class,
                (buildContext, creditBank) -> new CreditBankView(creditBank, (CardBuildContext) buildContext));

        return viewMapper;
    }

    @PostConstruct
    private void init() {
        viewMapper = scan(VIEW_PATH, Collections.emptySet());
        buildContext = new CardBuildContext();
        modelBuilder = new SimpleModelBuilder<CardBuildContext>();
    }
}
