package com.core.arhanto.acceptor.controller;

import com.core.arhanto.acceptor.records.OnboardingRequest;
import com.core.arhanto.acceptor.records.OnboardingResponse;
import com.core.arhanto.acceptor.service.OnboardingService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arhanto/onboard/v1")
@Data
@Slf4j
public class OnboardingController {
    private final OnboardingService onboardingService;

    @PostMapping("/customers")
    public ResponseEntity<OnboardingResponse> onboardNewCustomer(@RequestBody
                                                                 @Valid OnboardingRequest request) {
        log.info("OnboardingController onboardNewCustomer");
        //onboardingService.addCustomer(request);
        return null;
    }

}
