package org.dalvarez.ddd_example.shared.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = Application.PACKAGE_BASE)
public class Application {

    public static final String PACKAGE_BASE = "org.dalvarez.ddd_example";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
