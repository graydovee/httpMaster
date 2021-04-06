package cn.graydove.httpmaster.starter.config.core;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author graydove
 */
public class HttpRequestBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String className = importingClassMetadata.getClassName();
        String packageName = className.substring(0, className.lastIndexOf('.'));
        HttpClassPathBeanScanner scanner = new HttpClassPathBeanScanner(registry);
        scanner.doScan(packageName);
    }
}
