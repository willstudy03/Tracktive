package com.tracktive.orderservice.grpc;

import com.tracktive.orderservice.model.DTO.StockItemDTO;
import com.tracktive.orderservice.model.DTO.StockValidationResultDTO;
import com.tracktive.orderservice.model.DTO.SupplierProductDTO;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import stock_management.StockItem;
import stock_management.StockManagementRequest;
import stock_management.StockManagementResponse;
import stock_management.StockManagementServiceGrpc;

@Service
public class StockManagementServiceGrpcClient {

    private static final Logger log = LoggerFactory.getLogger(StockManagementServiceGrpcClient.class);
    private final StockManagementServiceGrpc.StockManagementServiceBlockingStub blockingStub;

    @Autowired
    public StockManagementServiceGrpcClient(
            @Value("${stock.management.service.host}") String serverAddress,
            @Value("${stock.management.service.port}") int serverPort) {

        log.info("Connecting to Stock Management Service GRPC service at {}:{}", serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort)
                .usePlaintext().build();

        blockingStub = StockManagementServiceGrpc.newBlockingStub(channel);
    }

    public StockValidationResultDTO validateStock(StockItemDTO stockItem){

        log.info("Sending stock validation request for item: {}", stockItem.getSupplierProductID());

        // Construct gRPC request
        StockManagementRequest request = StockManagementRequest.newBuilder()
                .addStockItems(StockItem.newBuilder()
                        .setSupplierProductId(stockItem.getSupplierProductID())
                        .setQuantity(stockItem.getQuantity())
                        .build())
                .build();

        StockManagementResponse stockManagementResponse = blockingStub.validateStock(request);

        // Get the first (and only) validation result
        stock_management.StockValidationResult grpcResult = stockManagementResponse.getResults(0);

        // Convert gRPC SupplierProduct to DTO
        stock_management.SupplierProduct grpcSupplierProduct = grpcResult.getSupplierProduct();
        SupplierProductDTO supplierProductDTO = new SupplierProductDTO(
                grpcSupplierProduct.getSupplierProductId(),
                grpcSupplierProduct.getSupplierId(),
                grpcSupplierProduct.getProductId(),
                grpcSupplierProduct.getPrice(),
                grpcSupplierProduct.getDiscountPercentage(),
                grpcSupplierProduct.getStockQuantity(),
                grpcSupplierProduct.getProductStatus()
        );

        log.info("Stock validation completed for SupplierProductID: {} - Valid: {}, Message: {}",
                supplierProductDTO.getSupplierProductId(), grpcResult.getValid(), grpcResult.getResultMessage());

        // Return the converted result
        return new StockValidationResultDTO(supplierProductDTO, grpcResult.getValid(), grpcResult.getResultMessage());
    }
}
