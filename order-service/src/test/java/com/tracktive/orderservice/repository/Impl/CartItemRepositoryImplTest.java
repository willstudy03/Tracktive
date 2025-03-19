package com.tracktive.orderservice.repository.Impl;

import com.tracktive.orderservice.model.DTO.CartItemDTO;
import com.tracktive.orderservice.model.entity.CartItem;
import com.tracktive.orderservice.repository.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
* Description: Cart Item Repository Test Case
* @author William Theo
* @date 19/3/2025
*/
@SpringBootTest
class CartItemRepositoryImplTest {

    @Autowired
    private CartItemRepository cartItemRepository;

    private static final Logger logger = LoggerFactory.getLogger(CartItemRepositoryImplTest.class);

    @Test
    void selectAllCartItems() {
        List<CartItemDTO> cartItemDTOS = cartItemRepository.selectAllCartItems();
        logger.info("Orders:{}", cartItemDTOS);
        assertEquals(cartItemDTOS.isEmpty(),false);
    }

    @Test
    void selectAllByRetailerId() {
        String id = "b01f7a76-caaa-42b3-95e7-fb624b4ddfee";
        List<CartItemDTO> cartItemDTOS = cartItemRepository.selectAllByRetailerId(id);
        logger.info("Orders:{}", cartItemDTOS);
        assertEquals(cartItemDTOS.get(0).getRetailerId(), id);
    }

    @Test
    void selectCartItemById() {
        String id = "443ae89a-428b-40f7-a91b-e7cb8dd04c3d";
        Optional<CartItemDTO> cartItemDTO = cartItemRepository.selectCartItemById(id);
        logger.info("Orders:{}", cartItemDTO);
        assertEquals(cartItemDTO.get().getId(), id);
    }

    @Test
    void lockCartItemById() {
        String id = "443ae89a-428b-40f7-a91b-e7cb8dd04c3d";
        Optional<CartItemDTO> cartItemDTO = cartItemRepository.lockCartItemById(id);
        logger.info("Orders:{}", cartItemDTO);
        assertEquals(cartItemDTO.get().getId(), id);
    }

    @Test
    void addCartItem() {
        CartItemDTO cartItemDTO = new CartItemDTO();

        cartItemDTO.setId("443ae89a-428b-40f7-a91b-e7cb8dd04c3d");
        cartItemDTO.setRetailerId("b01f7a76-caaa-42b3-95e7-fb624b4ddfee");
        cartItemDTO.setSupplierProductId("c1b7e4ae-ecef-4a31-b2b8-c813d235fd58");
        cartItemDTO.setSupplierId(UUID.randomUUID().toString());
        cartItemDTO.setProductId(UUID.randomUUID().toString());
        cartItemDTO.setQuantity((int) (Math.random() * 10) + 1); // Random quantity between 1 and 10
        cartItemDTO.setPriceSnapshot(new BigDecimal(Math.random() * 500).setScale(2, BigDecimal.ROUND_HALF_UP)); // Random price up to 500
        cartItemDTO.setDiscountSnapshot(new BigDecimal(Math.random() * 50).setScale(2, BigDecimal.ROUND_HALF_UP)); // Random discount up to 50
        cartItemDTO.setSubtotal(cartItemDTO.getPriceSnapshot().subtract(cartItemDTO.getDiscountSnapshot())); // Subtotal calculation

        boolean result = cartItemRepository.addCartItem(cartItemDTO);
        assertTrue(result);
    }

    @Test
    void updateCartItem() {
        Optional<CartItemDTO> cartItemDTO = cartItemRepository.selectCartItemById("443ae89a-428b-40f7-a91b-e7cb8dd04c3d");
        CartItemDTO updateCartItem = cartItemDTO.get();
        logger.info("CartItem price:{}", updateCartItem.getPriceSnapshot());
        updateCartItem.setPriceSnapshot(BigDecimal.valueOf(2323.23));
        assertTrue(cartItemRepository.updateCartItem(updateCartItem));
    }

    @Test
    void deleteCartItemById() {
        String id = "443ae89a-428b-40f7-a91b-e7cb8dd04c3d";
        boolean delResult = cartItemRepository.deleteCartItemById(id);
        assertTrue(delResult);
    }
}