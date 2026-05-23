package com.core.arhanto.acceptor.records;

import jakarta.validation.constraints.*;

public record OnboardingRequest(@NotNull int id, @NotBlank String name, @NotBlank String profession,
                                String primaryContactNo, String secondaryContactNo, @Email String emailId
) {
}
