package org.dalvarez.ddd_example.shared.infrastructure.shared;

import org.dalvarez.ddd_example.shared.infrastructure.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

@SpringBootTest(classes = Application.class)
@TestPropertySource("classpath:test.properties")
public @interface TestConfig {

}
