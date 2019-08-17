package com.dogukanhan.jsmodel.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.context.request.RequestContextHolder;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class BodyJsElementTagProcessor extends AbstractElementTagProcessor {

    private static final String TAG_NAME = "body";

    private static final int PRECEDENCE = 1000;


    BodyJsElementTagProcessor(final String dialectPrefix) {
        super(
                TemplateMode.HTML, // This processor will apply only to HTML mode
                dialectPrefix,     // Prefix to be applied to name for matching
                TAG_NAME,          // Tag name: match specifically this tag
                true,              // Apply dialect prefix to tag name
                null,              // No attribute name: will match by tag name
                false,             // No prefix to be applied to attribute name
                PRECEDENCE);       // Precedence (inside dialect's own precedence)
    }


    @Override
    protected void doProcess(
            final ITemplateContext context, final IProcessableElementTag tag,
            final IElementTagStructureHandler structureHandler) {

        JsModel jsModel = (JsModel) RequestContextHolder.getRequestAttributes().getAttribute("jsModel", 0);

        if (jsModel != null) {

            try {

                String json = new ObjectMapper().writeValueAsString(jsModel.asMap());

                final IModelFactory modelFactory = context.getModelFactory();

                final IModel iModel = modelFactory.createModel();

                iModel.add(modelFactory.createOpenElementTag("script", false));
                iModel.add(modelFactory.createText("var jsModel = " + json + ";"));
                iModel.add(modelFactory.createCloseElementTag("script"));

                structureHandler.insertImmediatelyAfter(iModel, false);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
    }

}