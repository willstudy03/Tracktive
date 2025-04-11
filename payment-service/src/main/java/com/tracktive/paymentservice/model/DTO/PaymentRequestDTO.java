package com.tracktive.paymentservice.model.DTO;

import com.tracktive.paymentservice.model.Enum.PaymentMethod;
import com.tracktive.paymentservice.model.Enum.PaymentStatus;
import com.tracktive.paymentservice.util.annotation.ValidEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

/**
* Description: Payment Request DTO
* @author William Theo
* @date 30/3/2025
*/
public class PaymentRequestDTO {

    @NotBlank(message = "Order ID is required")
    private String orderId;

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Currency is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid currency format (Use ISO 4217, e.g., USD, MYR, EUR)")
    private String currency;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Total Paid Amount is required")
    @DecimalMin(value = "0.00", message = "Total Paid Amount must be at least 0")
    private BigDecimal totalPaidAmount;

    private PaymentMethod paymentMethod;

    @NotNull(message = "Payment Status is required")
    @ValidEnum(enumClass = PaymentStatus.class, message = "Invalid Payment Status")
    private PaymentStatus paymentStatus;

    public PaymentRequestDTO() {
    }

    public PaymentRequestDTO(String orderId, String userId, String currency, BigDecimal amount, BigDecimal totalPaidAmount, PaymentMethod paymentMethod, PaymentStatus paymentStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.currency = currency;
        this.amount = amount;
        this.totalPaidAmount = totalPaidAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public @NotBlank(message = "Order ID is required") String getOrderId() {
        return orderId;
    }

    public void setOrderId(@NotBlank(message = "Order ID is required") String orderId) {
        this.orderId = orderId;
    }

    public @NotBlank(message = "User ID is required") String getUserId() {
        return userId;
    }

    public void setUserId(@NotBlank(message = "User ID is required") String userId) {
        this.userId = userId;
    }

    public @NotBlank(message = "Currency is required") @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid currency format (Use ISO 4217, e.g., USD, MYR, EUR)") String getCurrency() {
        return currency;
    }

    public void setCurrency(@NotBlank(message = "Currency is required") @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid currency format (Use ISO 4217, e.g., USD, MYR, EUR)") String currency) {
        this.currency = currency;
    }

    public @NotNull(message = "Amount is required") @DecimalMin(value = "0.01", message = "Amount must be greater than 0") BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "Amount is required") @DecimalMin(value = "0.01", message = "Amount must be greater than 0") BigDecimal amount) {
        this.amount = amount;
    }

    public @NotNull(message = "Total Paid Amount is required") @DecimalMin(value = "0.00", message = "Total Paid Amount must be at least 0") BigDecimal getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(@NotNull(message = "Total Paid Amount is required") @DecimalMin(value = "0.00", message = "Total Paid Amount must be at least 0") BigDecimal totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public @NotNull(message = "Payment Status is required") PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(@NotNull(message = "Payment Status is required") PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
