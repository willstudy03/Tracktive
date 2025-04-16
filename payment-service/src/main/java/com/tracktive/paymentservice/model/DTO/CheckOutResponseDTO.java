package com.tracktive.paymentservice.model.DTO;

/**
* Description: Payment Processor Response DTO
* @author William Theo
* @date 14/4/2025
*/
public class CheckOutResponseDTO {

    private String sessionId;

    private String checkoutUrl;

    public CheckOutResponseDTO() {
    }

    public CheckOutResponseDTO(String sessionId, String checkoutUrl) {
        this.sessionId = sessionId;
        this.checkoutUrl = checkoutUrl;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCheckoutUrl() {
        return checkoutUrl;
    }

    public void setCheckoutUrl(String checkoutUrl) {
        this.checkoutUrl = checkoutUrl;
    }
}
