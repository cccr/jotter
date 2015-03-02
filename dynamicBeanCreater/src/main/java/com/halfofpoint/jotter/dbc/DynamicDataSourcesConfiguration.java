package com.halfofpoint.jotter.dbc;

import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

@Configuration
public class DynamicDataSourcesConfiguration implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        File root = Paths.get("./auth").toFile();
        File[] list = root.listFiles();
        if (list==null) return;
        for (File f : list) {
            final String beanName = f.getName().split("\\.")[0];
            createBean(registry, beanName, f);
        }
    }

    private void createBean(BeanDefinitionRegistry registry, String beanName, File file) {
        GenericBeanDefinition beanDefinition = createBeanDefinition(AuthConfigDynamic.class);
        ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
        beanDefinition.setConstructorArgumentValues(constructorArgumentValues);
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        beanDefinition.getPropertyValues().addPropertyValues(properties);
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    private GenericBeanDefinition createBeanDefinition(Class<?> beanClass) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_NAME);
        return beanDefinition;
    }
}