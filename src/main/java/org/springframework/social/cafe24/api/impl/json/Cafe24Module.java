package org.springframework.social.cafe24.api.impl.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.cafe24.api.Product;

public class Cafe24Module extends SimpleModule {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(Cafe24Module.class);

    public Cafe24Module() {
        super("Cafe24Module");
    }

    @Override
    public void setupModule(SetupContext setupContext) {
        logger.info("setupModule called...");
        logger.info("setupContext.getMapperVersion().getArtifactId(): "  + setupContext.getMapperVersion().getArtifactId());
        logger.info("setupContext.getMapperVersion().getGroupId(): "  + setupContext.getMapperVersion().getGroupId());
        logger.info("setupContext.getMapperVersion().toFullString(): "  + setupContext.getMapperVersion().toFullString());
        logger.info("setupContext.getMapperVersion().isSnapshot(): "  + setupContext.getMapperVersion().isSnapshot());

        setupContext.setMixInAnnotations(Product.class, ProductMixin.class);

    }
}
