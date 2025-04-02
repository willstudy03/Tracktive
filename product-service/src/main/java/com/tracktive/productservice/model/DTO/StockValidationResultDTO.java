package com.tracktive.productservice.model.DTO;

/**
* Description: Stock Validation Result DTO
* @author William Theo
* @date 1/4/2025
*/
public class StockValidationResultDTO {

    private SupplierProductDTO supplierProductDTO;

    private boolean valid;

    private String resultMessage;

    public StockValidationResultDTO() {
    }

    public StockValidationResultDTO(SupplierProductDTO supplierProductDTO, boolean valid, String resultMessage) {
        this.supplierProductDTO = supplierProductDTO;
        this.valid = valid;
        this.resultMessage = resultMessage;
    }

    public SupplierProductDTO getSupplierProductDTO() {
        return supplierProductDTO;
    }

    public void setSupplierProductDTO(SupplierProductDTO supplierProductDTO) {
        this.supplierProductDTO = supplierProductDTO;
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
