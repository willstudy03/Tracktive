package com.tracktive.productservice.grpc;

import com.tracktive.productservice.model.DTO.StockItemDTO;
import com.tracktive.productservice.model.DTO.StockManagementRequestDTO;
import com.tracktive.productservice.model.DTO.StockManagementResponseDTO;
import com.tracktive.productservice.model.DTO.StockValidationResultDTO;
import com.tracktive.productservice.service.StockManagementService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import stock_management.*;

import java.util.List;
import java.util.stream.Collectors;

/**
* Description: Stock Management Service GRPC Server
* @author William Theo
* @date 1/4/2025
*/
@GrpcService
public class StockManagementGrpcService extends StockManagementServiceGrpc.StockManagementServiceImplBase {

    private StockManagementService stockManagementService;

    private static final Logger log = LoggerFactory.getLogger(StockManagementGrpcService.class);

    @Autowired
    public StockManagementGrpcService(StockManagementService stockManagementService) {
        this.stockManagementService = stockManagementService;
    }

    @Override
    public void validateStock(StockManagementRequest stockManagementRequest,
                              StreamObserver<StockManagementResponse> responseObserver){

        log.info("validateStock request received {}", stockManagementRequest.toString());

        // Convert gRPC request to DTO
        List<StockItemDTO> stockItems = stockManagementRequest.getStockItemsList().stream()
                .map(stockItem -> new StockItemDTO(stockItem.getSupplierProductId(), stockItem.getQuantity()))
                .collect(Collectors.toList());

        StockManagementRequestDTO requestDTO = new StockManagementRequestDTO(stockItems);

        // Call service method to validate stock
        StockManagementResponseDTO responseDTO = stockManagementService.validateStock(requestDTO);

        // Convert DTO response to gRPC response
        StockManagementResponse.Builder responseBuilder = StockManagementResponse.newBuilder();

        for (StockValidationResultDTO dto : responseDTO.getResults()) {
            // Convert SupplierProductDTO to gRPC SupplierProduct
            SupplierProduct supplierProduct = SupplierProduct.newBuilder()
                    .setSupplierProductId(dto.getSupplierProductDTO().getSupplierProductId())
                    .setSupplierId(dto.getSupplierProductDTO().getSupplierId())
                    .setProductId(dto.getSupplierProductDTO().getProductId())
                    .setPrice(dto.getSupplierProductDTO().getPrice().doubleValue())  // Convert BigDecimal to double
                    .setDiscountPercentage(dto.getSupplierProductDTO().getDiscountPercentage().doubleValue())
                    .setStockQuantity(dto.getSupplierProductDTO().getStockQuantity())
                    .setProductStatus(ProductStatus.valueOf(dto.getSupplierProductDTO().getProductStatus().name())) // Enum mapping
                    .setUpdatedAt(dto.getSupplierProductDTO().getUpdatedAt().toString()) // Convert LocalDateTime to String
                    .setCreatedAt(dto.getSupplierProductDTO().getCreatedAt().toString())
                    .build();

            // Build gRPC StockValidationResult
            StockValidationResult grpcResult = StockValidationResult.newBuilder()
                    .setSupplierProduct(supplierProduct)
                    .setValid(dto.isValid())
                    .setResultMessage(dto.getResultMessage())
                    .build();

            responseBuilder.addResults(grpcResult);
        }

        StockManagementResponse response = responseBuilder.build();

        // Send response
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deductStock(StockManagementRequest stockManagementRequest,
                            StreamObserver<StockManagementResponse> responseObserver) {

        log.info("deductStock request received {}", stockManagementRequest.toString());

        // Convert gRPC request to DTO
        List<StockItemDTO> stockItems = stockManagementRequest.getStockItemsList().stream()
                .map(stockItem -> new StockItemDTO(stockItem.getSupplierProductId(), stockItem.getQuantity()))
                .collect(Collectors.toList());

        StockManagementRequestDTO requestDTO = new StockManagementRequestDTO(stockItems);

        // Call service method to validate stock
        StockManagementResponseDTO responseDTO = stockManagementService.deductStock(requestDTO);

        // Convert DTO response to gRPC response
        StockManagementResponse.Builder responseBuilder = StockManagementResponse.newBuilder();

        for (StockValidationResultDTO dto : responseDTO.getResults()) {
            // Convert SupplierProductDTO to gRPC SupplierProduct
            SupplierProduct supplierProduct = SupplierProduct.newBuilder()
                    .setSupplierProductId(dto.getSupplierProductDTO().getSupplierProductId())
                    .setSupplierId(dto.getSupplierProductDTO().getSupplierId())
                    .setProductId(dto.getSupplierProductDTO().getProductId())
                    .setPrice(dto.getSupplierProductDTO().getPrice().doubleValue())  // Convert BigDecimal to double
                    .setDiscountPercentage(dto.getSupplierProductDTO().getDiscountPercentage().doubleValue())
                    .setStockQuantity(dto.getSupplierProductDTO().getStockQuantity())
                    .setProductStatus(ProductStatus.valueOf(dto.getSupplierProductDTO().getProductStatus().name())) // Enum mapping
                    .setUpdatedAt(dto.getSupplierProductDTO().getUpdatedAt().toString()) // Convert LocalDateTime to String
                    .setCreatedAt(dto.getSupplierProductDTO().getCreatedAt().toString())
                    .build();

            // Build gRPC StockValidationResult
            StockValidationResult grpcResult = StockValidationResult.newBuilder()
                    .setSupplierProduct(supplierProduct)
                    .setValid(dto.isValid())
                    .setResultMessage(dto.getResultMessage())
                    .build();

            responseBuilder.addResults(grpcResult);
        }



        StockManagementResponse response = responseBuilder.build();

        // Send response
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
