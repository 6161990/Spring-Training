package com.sp.fc.web.config;

import com.sp.fc.web.controller.PaperController;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

public class CustomMethodSecurityMetadataSource implements MethodSecurityMetadataSource {

    //애노테이션 기반
    @Override
    public Collection<ConfigAttribute> getAttributes(Method method, Class<?> targetClass) {
        CustomSecurityTag annotation = findAnnotation(method, targetClass, CustomSecurityTag.class);
        if(annotation != null){
            return List.of(new SecurityConfig(annotation.value()));
        }
        return null;
    }

/*    @Override
    public Collection<ConfigAttribute> getAttributes(Method method, Class<?> targetClass) {
        if(method.getName().equals("getPaper")&& targetClass == PaperController.class){
            return List.of(new SecurityConfig("SCHOOL_PRIMARY"));
        }
        return null;
    }*/

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return (MethodInvocation.class.isAssignableFrom(clazz));
    }

    //PrePostAnnotationMetadataSource.class 에서 가져옴
    private <A extends Annotation> A findAnnotation(Method method, Class<?> targetClass, Class<A> annotationClass) {
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        A annotation = AnnotationUtils.findAnnotation(specificMethod, annotationClass);
        if (annotation != null) {
            return annotation;
        }
        return annotation;
    }
}