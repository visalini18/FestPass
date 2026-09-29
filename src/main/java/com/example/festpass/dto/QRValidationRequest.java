package com.example.festpass.dto;

import jakarta.validation.constraints.NotBlank;

public class QRValidationRequest {

    @NotBlank(message = "QR code is required")
    private String qrCode;

    public QRValidationRequest() {
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}