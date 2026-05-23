package com.core.arhanto.acceptor.records;

public record OnboardingRequest(int id, String name, String profession,
                                String primaryContactNo, String secondaryContactNo, String emailId
) {
}
