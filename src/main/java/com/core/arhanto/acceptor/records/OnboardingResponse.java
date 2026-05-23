package com.core.arhanto.acceptor.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OnboardingResponse(@NotNull int id, @NotBlank String name, @NotBlank String profession,
                                String primaryContactNo, String secondaryContactNo, @Email String emailId
) {
}
