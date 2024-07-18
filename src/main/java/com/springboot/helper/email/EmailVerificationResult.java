package com.springboot.helper.email;

import lombok.Getter;

@Getter
public class EmailVerificationResult {
    private final boolean success;

    private EmailVerificationResult(boolean success) {
        this.success = success;
    }

    public static EmailVerificationResult of(boolean success) {
        return new EmailVerificationResult(success);
    }
}
