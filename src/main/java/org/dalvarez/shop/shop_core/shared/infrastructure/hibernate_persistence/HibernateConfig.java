package org.dalvarez.shop.shop_core.shared.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_core.Application;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Configuration
public class HibernateConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Environment env) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        Resource[] resources = loadMappingResources();
        sessionFactoryBean.setMappingLocations(resources);
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setHibernateProperties(hibernateProperties(env));

        return sessionFactoryBean;
    }

    private Properties hibernateProperties(Environment env) {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(AvailableSettings.HBM2DDL_AUTO, env.getProperty("spring.jpa.hibernate.ddl-auto"));
        hibernateProperties.put(AvailableSettings.SHOW_SQL, env.getProperty("spring.jpa.show-sql"));
        hibernateProperties.put(AvailableSettings.DIALECT, env.getProperty("spring.database-platform"));

        return hibernateProperties;
    }

    public static Resource[] loadMappingResources() {
        String dirBase = Application.PACKAGE_BASE.replaceAll("\\.", "/");
        File root = new File(getResource(dirBase));

        System.out.println(root);

        return getFilesRecursively(root, ".hbm.xml")
                .stream()
                .map(FileSystemResource::new)
                .collect(Collectors.toList())
                .toArray(Resource[]::new);
    }

    private static String getResource(String path) {
        return ClassLoader.getSystemClassLoader().getResource(path).getFile();
    }

    private static List<File> getFilesRecursively(File root,
                                                  String extension) {
        return getFilesRecursively(root).stream()
                                        .filter(file -> file.getName()
                                                            .contains(extension))
                                        .collect(Collectors.toList());
    }

    private static List<File> getFilesRecursively(File root) {
        List<File> files = new ArrayList<>();

        getFiles(root)
                .forEach(file -> {
                    if (file.isDirectory()) {
                        files.addAll(getFilesRecursively(file));
                    }

                    files.addAll(getFiles(file));
                });

        return files;
    }

    private static List<File> getFiles(File root) {
        File[] rootFiles = root.listFiles();

        if (rootFiles == null)
            return Collections.emptyList();

        return Arrays.asList(rootFiles);
    }

}
