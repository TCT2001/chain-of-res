package com.example.pattern.actions.register;

import com.example.pattern.actions.ActionResponse;
import com.example.pattern.actions.RegisterAction;
import com.example.pattern.controllers.TestController;
import com.example.pattern.entities.RequestCa;
import com.example.pattern.repositories.RequestCaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class CustomerCaAction extends RegisterAction {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    public CustomerCaAction(EquipmentStrategy equipmentStrategy) {
        this.equipmentStrategy = equipmentStrategy;
    }

    @Override
    public ActionResponse handle() {
        logger.info("2");
        if (requestCa != null) {
            logger.info(requestCa.toString());
        }
        equipmentStrategy.executeCustomerCaAction();
        return ActionResponse.getSuccess();
    }
}
