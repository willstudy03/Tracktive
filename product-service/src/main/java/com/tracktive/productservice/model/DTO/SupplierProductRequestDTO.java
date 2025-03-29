package com.tracktive.productservice.model.DTO;

import com.tracktive.productservice.model.Enum.ProductStatus;
import com.tracktive.productservice.util.annotation.ValidEnum;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
* Description: Supplier Product Request DTO
* @author William Theo
* @date 28/3/2025
*/
public class SupplierProductRequestDTO {

    @NotBlank(message = "Product ID is required")
    private String supplierId;

    @NotBlank(message = "Supplier ID is required")
    private String productId;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater or equal than 0")
    private BigDecimal price;

    @NotNull(message = "Discount percentage is required")
    @DecimalMin(value = "0.0", message = "Discount percentage cannot be negative")
    @DecimalMax(value = "100.0", message = "Discount percentage cannot exceed 100")
    private BigDecimal discountPercentage;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

    @NotNull(message = "Product status is required")
    @ValidEnum(enumClass = ProductStatus.class, message = "Invalid Product Status")
    private ProductStatus productStatus;

    public SupplierProductRequestDTO() {
    }

    public SupplierProductRequestDTO(String supplierId, String productId, BigDecimal price, BigDecimal discountPercentage, Integer stockQuantity, ProductStatus productStatus) {
        this.supplierId = supplierId;
        this.productId = productId;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.stockQuantity = stockQuantity;
        this.productStatus = productStatus;
    }

    public @NotBlank(message = "Product ID is required") String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(@NotBlank(message = "Product ID is required") String supplierId) {
        this.supplierId = supplierId;
    }

    public @NotBlank(message = "Product ID is required") String getProductId() {
        return productId;
    }

    public void setProductId(@NotBlank(message = "Product ID is required") String productId) {
        this.productId = productId;
    }

    public @NotNull(message = "Price is required") @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater or equal than 0") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Price is required") @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater or equal than 0") BigDecimal price) {
        this.price = price;
    }

    public @NotNull(message = "Discount percentage is required") @DecimalMin(value = "0.0", message = "Discount percentage cannot be negative") @DecimalMax(value = "100.0", message = "Discount percentage cannot exceed 100") BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(@NotNull(message = "Discount percentage is required") @DecimalMin(value = "0.0", message = "Discount percentage cannot be negative") @DecimalMax(value = "100.0", message = "Discount percentage cannot exceed 100") BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public @NotNull(message = "Stock quantity is required") @Min(value = 0, message = "Stock quantity cannot be negative") Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(@NotNull(message = "Stock quantity is required") @Min(value = 0, message = "Stock quantity cannot be negative") Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public @NotNull(message = "Product status is required") ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(@NotNull(message = "Product status is required") ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
}
