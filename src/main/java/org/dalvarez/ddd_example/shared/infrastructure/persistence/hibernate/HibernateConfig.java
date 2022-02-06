package org.dalvarez.ddd_example.shared.infrastructure.persistence.hibernate;

import org.dalvarez.ddd_example.shared.infrastructure.Application;
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
    public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource,
                                                           Environment env) {
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

    private Resource[] loadMappingResources() {
        String dirBase = Application.PACKAGE_BASE.replaceAll("\\.", "/");
        File root = new File(getResource(dirBase));

        return getFilesRecursively(root, ".hbm.xml")
                .stream()
                .map(FileSystemResource::new)
                .collect(Collectors.toList())
                .toArray(Resource[]::new);
    }

    private String getResource(String path) {
        return this.getClass().getClassLoader()
                   .getResource(path)
                   .getFile();
    }

    private List<File> getFilesRecursively(File root,
                                           String extension) {
        return getFilesRecursively(root).stream()
                                        .filter(file -> file.getName().contains(extension))
                                        .collect(Collectors.toList());
    }

    private List<File> getFilesRecursively(File root) {
        List<File> files = new ArrayList<>();

        getFiles(root).forEach(file -> {
            if (file.isDirectory()) {
                files.addAll(getFilesRecursively(file));
            }

            files.addAll(getFiles(file));
        });

        return files;
    }

    private List<File> getFiles(File root) {
        File[] rootFiles = root.listFiles();

        if (rootFiles == null)
            return Collections.emptyList();

        return Arrays.asList(rootFiles);
    }

}
