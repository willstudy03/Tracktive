package com.tracktive.orderservice.service.Impl;

import com.tracktive.orderservice.exception.CartItemNotFoundException;
import com.tracktive.orderservice.exception.LockAcquisitionException;
import com.tracktive.orderservice.model.DTO.CartItemDTO;
import com.tracktive.orderservice.repository.CartItemRepository;
import com.tracktive.orderservice.service.CartItemService;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
* Description: Cart Item Service Implementation
* @author William Theo
* @date 20/3/2025
*/
@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private static final Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public List<CartItemDTO> selectAllCartItems() {
        return cartItemRepository.selectAllCartItems();
    }

    @Override
    public List<CartItemDTO> selectAllByRetailerId(String id) {
        validateId(id);
        return cartItemRepository.selectAllByRetailerId(id);
    }

    @Override
    public CartItemDTO selectCartItemById(String id) {
        validateId(id);
        return cartItemRepository.selectCartItemById(id)
                .orElseThrow(() -> {
                    logger.warn("Cart Item not found with id: {}", id);
                    return new CartItemNotFoundException("Cart Item not found with id: " + id);
                });
    }

    @Override
    @Transactional
    public CartItemDTO lockCartItemById(String id) {
        validateId(id);
        try {
            return cartItemRepository.lockCartItemById(id)
                    .orElseThrow(() -> {
                        logger.warn("Failed to lock product, product not found with id: {}", id);
                        return new CartItemNotFoundException("Product not found with id: " + id);
                    });
        } catch (PersistenceException e) {
            logger.error("Persistence error occurred during lock acquisition for product id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for product with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during product lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public void addCartItem(CartItemDTO cartItemDTO) {
        validateCartItemDTO(cartItemDTO);
        boolean result = cartItemRepository.addCartItem(cartItemDTO);
        if (!result) {
            logger.error("Failed to add cart item with id: {}", cartItemDTO.getId());
            throw new RuntimeException("Failed to add cart item with id: " + cartItemDTO.getId());
        }
        logger.info("Cart Item [{}] added successfully", cartItemDTO.getId());
    }

    @Override
    @Transactional
    public void updateCartItem(CartItemDTO cartItemDTO) {
        validateCartItemDTO(cartItemDTO);
        boolean result = cartItemRepository.updateCartItem(cartItemDTO);
        if (!result) {
            logger.error("Failed to update cart item with id: {}", cartItemDTO.getId());
            throw new CartItemNotFoundException("Failed to update cart item with id: " + cartItemDTO.getId());
        }
        logger.info("Cart Item [{}] updated successfully", cartItemDTO.getId());
    }

    @Override
    @Transactional
    public void deleteCartItemById(String id) {
        validateId(id);
        boolean result = cartItemRepository.deleteCartItemById(id);
        if (!result) {
            logger.error("Failed to delete cart item with id: {}", id);
            throw new CartItemNotFoundException("Failed to delete cart item with id: " + id);
        }
        logger.info("Cart Item [{}] deleted successfully", id);
    }

    private void validateId(String id) {
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Cart Item ID cannot be null or empty");
        }
    }

    private void validateCartItemDTO(CartItemDTO cartItemDTO) {

        if (Objects.isNull(cartItemDTO)) {
            throw new IllegalArgumentException("cartItemDTO cannot be null");
        }

        //@TODO: PARAMETERS VALIDATION
    }
}
