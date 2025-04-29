package com.tracktive.productservice.service.Impl;

import com.tracktive.productservice.aws.S3Service;
import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductManagementDTO;
import com.tracktive.productservice.model.DTO.ProductManagementRequestDTO;
import com.tracktive.productservice.service.ProductManagementService;
import com.tracktive.productservice.service.ProductService;
import com.tracktive.productservice.util.converter.Impl.ProductConverter;
import com.tracktive.productservice.util.factory.ProductManagementFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* Description: Product Management Service Implementation
* @author William Theo
* @date 25/3/2025
*/
@Service
public class ProductManagementServiceImpl implements ProductManagementService {

    private final S3Service s3Service;

    private final ProductService productService;

    private final ProductManagementFactory productManagementFactory;

    private static final Logger logger = LoggerFactory.getLogger(ProductManagementServiceImpl.class);

    @Autowired
    public ProductManagementServiceImpl(S3Service s3Service, ProductService productService, ProductManagementFactory productManagementFactory) {
        this.s3Service = s3Service;
        this.productService = productService;
        this.productManagementFactory = productManagementFactory;
    }

    @Override
    public List<ProductManagementDTO> selectAllProducts() {
        return productService.selectAllProducts()
                .stream()
                .map(ProductConverter::toProductManagementDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductManagementDTO selectProductById(String productId) {
        ProductDTO product = productService.selectProductById(productId);
        return productManagementFactory.selectProduct(product);
    }

    @Override
    @Transactional
    public ProductManagementDTO createProduct(ProductManagementRequestDTO productManagementRequestDTO, MultipartFile image) {
        ProductManagementDTO productManagementDTO = productManagementFactory.addProduct(productManagementRequestDTO);
        if (!image.isEmpty()){

            if (!image.getContentType().startsWith("image/")) {
                throw new IllegalArgumentException("Only images are allowed");
            }

            productManagementDTO.setImageUrl(s3Service.getFileUrl(s3Service.uploadFile(image,productManagementDTO.getProductId())));
        }
        return productManagementFactory.updateProduct(productManagementDTO);
    }

    @Override
    @Transactional
    public ProductManagementDTO updateProduct(ProductManagementDTO productManagementDTO) {
        return productManagementFactory.updateProduct(productManagementDTO);
    }

    @Override
    @Transactional
    public ProductManagementDTO deleteProduct(String productId) {
        ProductDTO product = productService.selectProductById(productId);
        return productManagementFactory.deleteProduct(product);
    }
}
