package com.tracktive.productservice.model.DTO;

/**
* Description: Stock Validation Result DTO
* @author William Theo
* @date 1/4/2025
*/
public class StockValidationResultDTO {

    private String supplierProductId;

    private boolean valid;

    private String resultMessage;

    public StockValidationResultDTO() {
    }

    public StockValidationResultDTO(String supplierProductId, boolean valid, String resultMessage) {
        this.supplierProductId = supplierProductId;
        this.valid = valid;
        this.resultMessage = resultMessage;
    }

    public String getSupplierProductId() {
        return supplierProductId;
    }

    public void setSupplierProductId(String supplierProductId) {
        this.supplierProductId = supplierProductId;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
