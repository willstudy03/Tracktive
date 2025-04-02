package com.tracktive.orderservice.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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

    public StockManagementResponse validateStock(){
        return null;
    }
}
