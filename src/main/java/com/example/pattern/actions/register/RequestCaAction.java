package com.example.pattern.actions.register;

import com.example.pattern.PersistenceJPAConfig;
import com.example.pattern.actions.ActionResponse;
import com.example.pattern.actions.RegisterAction;
import com.example.pattern.controllers.TestController;
import com.example.pattern.entities.RequestCa;
import com.example.pattern.repositories.RequestCaRepository;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestCaAction extends RegisterAction {
    Logger logger = LoggerFactory.getLogger(RequestCaAction.class);

    public RequestCaAction(EquipmentStrategy equipmentStrategy) {
        this.equipmentStrategy = equipmentStrategy;
    }

    @Override
    public ActionResponse handle() {
        EntityManager entityManager = PersistenceJPAConfig
                .getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();

        EntityManager entityManager2 = PersistenceJPAConfig
                .getEntityManagerFactory()
                .createEntityManager();
        entityManager2.getTransaction().begin();

        logger.info(entityManager2.toString());
        logger.info(entityManager.toString());

        RequestCaRepository.findById(entityManager, 1L);
        RequestCaRepository.findById(entityManager2, 1L);


        try {
            logger.info("1");
            requestCa = new RequestCa(1);
            logger.info(requestCa.toString());
            equipmentStrategy.executeRequestCaAction();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.clear();
            entityManager.clear();
        }
        return ActionResponse.getSuccess();
    }
}
