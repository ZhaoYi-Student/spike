package com.chen.spike.o_config.jpa;

import com.chen.spike.o_toolkits.ReflectUtils;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.Date;

public class JpaExtSimpleRepository<T, PK> extends SimpleJpaRepository<T, PK> {

    private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null!";

    private static final String CREATE = "CREATE";
    private static final String UPDATE = "UPDATE";

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;
    private final PersistenceProvider provider;

    public JpaExtSimpleRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        Assert.notNull(entityInformation, "JpaEntityInformation must not be null!");
        Assert.notNull(entityManager, "EntityManager must not be null!");

        this.entityInformation = entityInformation;
        this.em = entityManager;
        this.provider = PersistenceProvider.fromEntityManager(entityManager);
    }

    public JpaExtSimpleRepository(Class<T> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
    }

    @Override
    public <S extends T> S save(S entity) {
        Assert.notNull(entity, "Entity must not be null.");

        if (entityInformation.isNew(entity)) {
            processEntity(entity, CREATE);
            em.persist(entity);
            return entity;
        } else {
            processEntity(entity, UPDATE);
            return em.merge(entity);
        }
    }

    private <S extends T> void processEntity(S entity, String status) {
        if (CREATE.equals(status)) {
            ReflectUtils.setFieldValue(entity, "creator", 1);
            ReflectUtils.setFieldValue(entity, "createTime", new Date());
        }
        ReflectUtils.setFieldValue(entity, "modify", 1);
        ReflectUtils.setFieldValue(entity, "modifyTime", new Date());
        ReflectUtils.setFieldValue(entity, "version", 1);
    }
}
