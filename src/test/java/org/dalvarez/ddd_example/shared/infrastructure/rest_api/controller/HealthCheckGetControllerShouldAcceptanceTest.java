package org.dalvarez.ddd_example.shared.infrastructure.rest_api.controller;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.dalvarez.ddd_example.shared.infrastructure.rest_api.ApiTestCase;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;

public final class HealthCheckGetControllerShouldAcceptanceTest extends ApiTestCase {

    @When("^I send a GET request to \\/health-check$")
    public void i_send_a_get_request_to_health_check() throws Exception {
        getBy("/health-check", Void.class);
    }

    @Then("^the status code should be: (\\d+)$")
    public void the_status_code_should_be(Integer statusCode) {
        assertEquals(HttpStatus.OK, HttpStatus.valueOf(statusCode));
    }

}
