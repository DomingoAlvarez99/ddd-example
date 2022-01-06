package org.dalvarez.ddd_example.shared.infrastructure.rest_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class HealthCheckGetController {

    @GetMapping("/health-check")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok().build();
    }

}
