package org.dalvarez.shop.shop_core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = Application.PACKAGE_BASE)
public class Application {

    public static final String PACKAGE_BASE = "org.dalvarez.shop";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
