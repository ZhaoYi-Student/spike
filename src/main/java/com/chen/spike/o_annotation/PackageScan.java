package com.chen.spike.o_annotation;

import com.chen.spike.o_config.jpa.JpaExtSimpleRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.annotation.AliasFor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EntityScan
@EnableJpaRepositories
public @interface PackageScan {

    @AliasFor(
            annotation = EntityScan.class,
            attribute = "basePackages"
    )
    String[] entity();

    @AliasFor(
            annotation = EnableJpaRepositories.class,
            attribute = "basePackages"
    )
    String[] jpa();

    @AliasFor(
            annotation = EnableJpaRepositories.class,
            attribute = "repositoryBaseClass"
    )
    Class<?> jpaRepositoryBaseClass() default JpaExtSimpleRepository.class;

}
