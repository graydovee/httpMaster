package cn.graydove.httpmaster.starter.config.core;

import cn.graydove.httpmaster.core.exception.UnsupportedException;
import cn.graydove.httpmaster.starter.annotation.HttpService;
import cn.graydove.httpmaster.starter.proxy.RequestFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

public class HttpClassPathBeanScanner extends ClassPathBeanDefinitionScanner {
    public HttpClassPathBeanScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
        addIncludeFilter(new AnnotationTypeFilter(HttpService.class));
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            GenericBeanDefinition bd = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            String beanClassName = bd.getBeanClassName();
            Class<?> clazz;
            try {
                clazz = Class.forName(beanClassName);
            } catch (ClassNotFoundException e) {
                throw new UnsupportedException(e);
            }
            bd.getConstructorArgumentValues().addIndexedArgumentValue(0, clazz);
            bd.setBeanClass(RequestFactoryBean.class);
        }
        return beanDefinitionHolders;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return metadata.isInterface();
    }
}
