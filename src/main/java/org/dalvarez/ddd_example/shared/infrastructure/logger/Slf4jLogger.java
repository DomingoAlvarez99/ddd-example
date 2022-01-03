package org.dalvarez.ddd_example.shared.infrastructure.logger;

import org.dalvarez.ddd_example.shared.domain.log.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Method;
import java.util.Optional;

public final class Slf4jLogger implements Logger {

    private final org.slf4j.Logger logger;

    public Slf4jLogger(final InjectionPoint ip) {
        this.logger = LoggerFactory.getLogger(getClassName(ip));
    }

    private String getClassName(final InjectionPoint ip) {
        return Optional.ofNullable(getMethodOrElseNull(ip.getMethodParameter()))
                       .map(m -> m.getReturnType().getName())
                       .orElseGet(() -> getMethodParameterOfDeclaredClass(ip));
    }

    private Method getMethodOrElseNull(final MethodParameter methodParameter) {
        try {
            return methodParameter.getMethod();
        } catch (Exception e) {
            return null;
        }
    }

    private String getMethodParameterOfDeclaredClass(final InjectionPoint ip) {
        return Optional.ofNullable(ip.getMethodParameter())
                       .map(mp -> mp.getDeclaringClass().getName())
                       .orElseGet(() -> getFieldParameterOfDeclaredClass(ip));
    }

    private String getFieldParameterOfDeclaredClass(final InjectionPoint ip) {
        return Optional.ofNullable(ip.getField())
                       .map(f -> f.getDeclaringClass().getName())
                       .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void info(final String format,
                     final Object... arguments) {
        logger.info(format, arguments);
    }

    @Override
    public void warn(final String format,
                     final Object... arguments) {
        logger.warn(format, arguments);
    }

    @Override
    public void error(final String format,
                      final Object... arguments) {
        logger.error(format, arguments);
    }

    @Override
    public void debug(final String format,
                      final Object... arguments) {
        logger.debug(format, arguments);
    }

}
