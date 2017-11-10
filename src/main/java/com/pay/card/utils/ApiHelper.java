package com.pay.card.utils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.github.phantomthief.model.builder.ModelBuilder;
import com.github.phantomthief.model.builder.impl.SimpleModelBuilder;
import com.github.phantomthief.view.mapper.ViewMapper;
import com.github.phantomthief.view.mapper.impl.DefaultViewMapperImpl;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
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
        try {
            ImmutableSet<ClassInfo> topLevelClasses = ClassPath.from(ApiHelper.class.getClassLoader())
                    .getTopLevelClassesRecursive(pkg);
            for (ClassInfo classInfo : topLevelClasses) {
                Class<?> type = classInfo.load();
                if (ignoreViews.contains(type)) {
                    continue;
                }
                Constructor<?>[] constructors = type.getConstructors();
                for (Constructor<?> constructor : constructors) {
                    Class<?>[] parameterTypes = constructor.getParameterTypes();
                    if (parameterTypes.length == 2) {
                        viewMapper.addMapper(parameterTypes[0], (buildContext, i) -> {
                            try {
                                return constructor.newInstance(i, buildContext);
                            } catch (Exception e) {
                                logger.error("fail to construct model:{}", i, e);
                                return null;
                            }
                        });
                        logger.info("register view [{}] for model [{}], with buildContext.", type.getSimpleName(),
                                parameterTypes[0].getSimpleName());
                    }
                    if (parameterTypes.length == 1) {
                        viewMapper.addMapper(parameterTypes[0], (buildContext, i) -> {
                            try {
                                return constructor.newInstance(i);
                            } catch (Exception e) {
                                logger.error("fail to construct model:{}", i, e);
                                return null;
                            }
                        });
                        logger.info("register view [{}] for model [{}]", type.getSimpleName(),
                                parameterTypes[0].getSimpleName());
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Ops.", e);
        }
        return viewMapper;
    }

    @PostConstruct
    private void init() {
        buildContext = new CardBuildContext();
        viewMapper = scan(VIEW_PATH, Collections.emptySet());
        modelBuilder = new SimpleModelBuilder<>();
    }
}
