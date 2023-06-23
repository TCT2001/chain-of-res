package com.example.pattern.repositories;

import com.example.pattern.entities.RequestCa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Optional;

public class RequestCaRepository {
    private RequestCaRepository(){}

    public  static Optional<RequestCa> findById(EntityManager entityManager, Long id) {
        try {
            Query query = entityManager.createNamedQuery("RequestCa.findById")
                    .setParameter("id", id);
            return Optional.of((RequestCa) query.getSingleResult());
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
