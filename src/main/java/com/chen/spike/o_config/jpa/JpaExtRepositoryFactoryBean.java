package com.chen.spike.o_config.jpa;

import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;

public class JpaExtRepositoryFactoryBean<R extends Repository<T, ID>, T, ID> extends JpaRepositoryFactoryBean<R, T, ID> {
    public JpaExtRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new JpaExtRepositoryFactory<T, ID>(entityManager);
    }

    private static class JpaExtRepositoryFactory<T, ID> extends RepositoryFactorySupport {

        private final EntityManager entityManager;

        public JpaExtRepositoryFactory(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T, ID> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
            return (EntityInformation<T, ID>) JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager);
        }

        @Override
        @SuppressWarnings("unchecked")
        protected Object getTargetRepository(RepositoryInformation metadata) {
            return new JpaExtSimpleRepository<T, ID>((Class<T>) metadata.getDomainType(), entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return JpaExtSimpleRepository.class;
        }
    }
}
