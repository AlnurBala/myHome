package com.startup.myhome.repository;

import com.startup.myhome.dto.request.PropertyRequest;
import com.startup.myhome.entity.Property;
import com.startup.myhome.enumeration.BillOfSale;
import com.startup.myhome.enumeration.Renovation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PropertyCriteria {
    @PersistenceContext
    EntityManager entityManager;
    public Page<Property> filterProperties(PropertyRequest request, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        Root<Property> propertyRoot = criteriaQuery.from(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        if (request.getBillOfSale() != null) {
            predicates.add(criteriaBuilder.equal(propertyRoot.get("billOfSale"), request.getBillOfSale()));
        }
        if (request.getTarget() != null) {
            predicates.add(criteriaBuilder.equal(propertyRoot.get("target"), request.getTarget()));
        }
        if (request.getRenovation() != null) {
            predicates.add(criteriaBuilder.equal(propertyRoot.get("renovation"), request.getRenovation()));
        }
        if (request.getPropertyCategory() != null) {
            predicates.add(criteriaBuilder.equal(propertyRoot.get("propertyCategory"), request.getPropertyCategory()));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Property> query = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());

        List<Property> properties = query.getResultList();
        return new PageImpl<>(properties, pageable, properties.size());
    }


    private double calculateFinalPrice(Property property, double averagePricePerSquareMeter) {
        return averagePricePerSquareMeter * property.getArea();
    }
    public Page<Property> getPropertiesByArea(Double area, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        Root<Property> propertyRoot = criteriaQuery.from(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(propertyRoot.get("area"), area));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Property> query = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());

        List<Property> properties = query.getResultList();
        return new PageImpl<>(properties, pageable, properties.size());
    }
    public Page<Property> getPropertiesByBuildingFloor(Integer buildingFloor, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        Root<Property> propertyRoot = criteriaQuery.from(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(propertyRoot.get("buildingFloor"), buildingFloor));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Property> query = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());

        List<Property> properties = query.getResultList();
        return new PageImpl<>(properties, pageable, properties.size());
    }

    public Page<Property> getPropertiesByBillOfSale(BillOfSale billOfSale, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        Root<Property> propertyRoot = criteriaQuery.from(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(propertyRoot.get("billOfSale"), billOfSale));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Property> query = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());

        List<Property> properties = query.getResultList();
        return new PageImpl<>(properties, pageable, properties.size());
    }
    public Page<Property> getPropertiesByRenovation(Renovation renovation, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        Root<Property> propertyRoot = criteriaQuery.from(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(propertyRoot.get("renovation"), renovation));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Property> query = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());

        List<Property> properties = query.getResultList();
        return new PageImpl<>(properties, pageable, properties.size());
    }

    public Page<Property> getPropertiesByAdvertDate(LocalDate date, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        Root<Property> propertyRoot = criteriaQuery.from(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(propertyRoot.get("advertDate"), date));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Property> query = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());

        List<Property> properties = query.getResultList();
        return new PageImpl<>(properties, pageable, properties.size());
    }
    public Page<Property> getPropertiesByUpdatedDate(LocalDate date, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        Root<Property> propertyRoot = criteriaQuery.from(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(propertyRoot.get("updatedDate"), date));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Property> query = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());

        List<Property> properties = query.getResultList();
        return new PageImpl<>(properties, pageable, properties.size());
    }
    public Page<Property> getPropertiesByMetro(String metro, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        Root<Property> propertyRoot = criteriaQuery.from(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(propertyRoot.get("metro"), metro));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Property> query = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());

        List<Property> properties = query.getResultList();
        return new PageImpl<>(properties, pageable, properties.size());
    }
    public Page<Property> getPropertiesByCity(String city, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        Root<Property> propertyRoot = criteriaQuery.from(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(propertyRoot.get("city"), city));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Property> query = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());

        List<Property> properties = query.getResultList();
        return new PageImpl<>(properties, pageable, properties.size());
    }


}
