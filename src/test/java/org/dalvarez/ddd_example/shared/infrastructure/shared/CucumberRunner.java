package org.dalvarez.ddd_example.shared.infrastructure.shared;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = CucumberRunner.CUCUMBER_CONTEXT_ANNOTATION_PACKAGE, features = CucumberRunner.FEATURES_ROOT_DIR)
public final class CucumberRunner {

    public static final String CUCUMBER_CONTEXT_ANNOTATION_PACKAGE = "org.dalvarez.ddd_example.shared.infrastructure.rest_api";

    public static final String FEATURES_ROOT_DIR = "src/test/java/org/dalvarez/ddd_example";

}