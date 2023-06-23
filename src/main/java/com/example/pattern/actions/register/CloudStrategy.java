package com.example.pattern.actions.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloudStrategy implements EquipmentStrategy {
    Logger logger = LoggerFactory.getLogger(ToolStrategy.class);

    @Override
    public void executeRequestCaAction() {
        logger.info("executeRequestCaAction");
    }

    @Override
    public void executeCustomerCaAction() {
        logger.info("executeCustomerCaAction");
    }
}
