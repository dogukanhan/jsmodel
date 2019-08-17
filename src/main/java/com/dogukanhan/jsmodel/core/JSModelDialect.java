package com.dogukanhan.jsmodel.core;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.HashSet;
import java.util.Set;

public class JSModelDialect extends AbstractProcessorDialect {

    private static final String DIALECT_NAME = "Score Dialect";


    public JSModelDialect() {
        super(DIALECT_NAME, "", StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new BodyJsElementTagProcessor(dialectPrefix));
        processors.add(new ScriptElementTagProcessor(dialectPrefix));
        return processors;
    }
}
