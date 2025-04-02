package com.tracktive.productservice.util.handler;

import com.tracktive.productservice.exception.*;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.stereotype.Component;

/**
* Description: Global Exception Handler of GRPC service
* @author William Theo
* @date 2/4/2025
*/
@GrpcAdvice
@Component
public class GlobalGrpcExceptionHandler {

    @GrpcExceptionHandler(Exception.class)
    public StatusRuntimeException handleException(Exception e) {
        return Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException();
    }

    @GrpcExceptionHandler(IllegalArgumentException.class)
    public StatusRuntimeException handleIllegalArgumentException(IllegalArgumentException e) {
        return Status.INVALID_ARGUMENT.withDescription(e.getMessage()).asRuntimeException();
    }

    @GrpcExceptionHandler(ProductNotFoundException.class)
    public StatusRuntimeException handleProductNotFoundException(ProductNotFoundException e) {
        return Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException();
    }

    @GrpcExceptionHandler(StockDeductionException.class)
    public StatusRuntimeException handleStockDeductionException(StockDeductionException e) {
        return Status.FAILED_PRECONDITION.withDescription(e.getMessage()).asRuntimeException();
    }

    @GrpcExceptionHandler(StockValidationException.class)
    public StatusRuntimeException handleStockValidationException(StockValidationException e) {
        return Status.INVALID_ARGUMENT.withDescription(e.getMessage()).asRuntimeException();
    }

    @GrpcExceptionHandler(InsufficientStockException.class)
    public StatusRuntimeException handleInsufficientStockException(InsufficientStockException e) {
        return Status.FAILED_PRECONDITION.withDescription(e.getMessage()).asRuntimeException();
    }

    @GrpcExceptionHandler(LockAcquisitionException.class)
    public StatusRuntimeException handleAcquisitionException(LockAcquisitionException e) {
        return Status.ABORTED.withDescription(e.getMessage()).asRuntimeException();
    }
}
