
package com.innovus.nearestpoi.repository;

import com.innovus.nearestpoi.entity.PointOfInterest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class PointOfInterestRepository {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;

    public List<PointOfInterest> findAll() {
        return em.createNativeQuery(
                "SELECT id, name, ST_X(location) AS latitude, ST_Y(location) AS longitude, request_count AS requestCount FROM points_of_interest",
                    PointOfInterest.class)
                  .getResultList();
    }


    public List<PointOfInterest> findByRequestCountGreaterThan(int count) {
        return em.createNativeQuery(
                "SELECT id, name, ST_X(location) AS latitude, ST_Y(location) AS longitude, request_count AS requestCount FROM points_of_interest WHERE request_count  > ?",
                    PointOfInterest.class)
                  .setParameter(1, count)
                  .getResultList();
    }
    
    public void incrementRequestCountForNearestPoint(Long poiId) {
        em.createNativeQuery("UPDATE points_of_interest SET request_count = request_count + 1 WHERE id = ? ")
                .setParameter(1, poiId)
                .executeUpdate();
    }
    
}

