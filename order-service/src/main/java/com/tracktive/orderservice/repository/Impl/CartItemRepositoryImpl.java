package com.tracktive.orderservice.repository.Impl;

import com.tracktive.orderservice.exception.CartItemAlreadyExistException;
import com.tracktive.orderservice.exception.DatabaseOperationException;
import com.tracktive.orderservice.model.DAO.CartItemDAO;
import com.tracktive.orderservice.model.DTO.CartItemDTO;
import com.tracktive.orderservice.model.entity.CartItem;
import com.tracktive.orderservice.repository.CartItemRepository;
import com.tracktive.orderservice.util.converter.CartItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* Description: Cart Item Repository Implementation
* @author William Theo
* @date 19/3/2025
*/
@Repository
public class CartItemRepositoryImpl implements CartItemRepository {

    private final CartItemDAO cartItemDAO;

    @Autowired
    public CartItemRepositoryImpl(CartItemDAO cartItemDAO) {
        this.cartItemDAO = cartItemDAO;
    }

    @Override
    public List<CartItemDTO> selectAllCartItems() {
        return cartItemDAO.selectAllCartItems()
                .stream()
                .map(CartItemConverter::toDTO)
                .toList();
    }

    @Override
    public List<CartItemDTO> selectAllByRetailerId(String id) {
        return cartItemDAO.selectAllByRetailerId(id)
                .stream()
                .map(CartItemConverter::toDTO)
                .toList();
    }

    @Override
    public Optional<CartItemDTO> selectCartItemById(String id) {
        return cartItemDAO.selectCartItemById(id).map(CartItemConverter::toDTO);
    }

    @Override
    public Optional<CartItemDTO> lockCartItemById(String id) {
        return  cartItemDAO.lockCartItemById(id).map(CartItemConverter::toDTO);
    }

    @Override
    public boolean addCartItem(CartItemDTO cartItemDTO) {
        try {
            CartItem cartItem = CartItemConverter.toEntity(cartItemDTO);
            return cartItemDAO.addCartItem(cartItem) > 0;
        } catch (DuplicateKeyException e) {
            throw new CartItemAlreadyExistException("Cart Item with id " + cartItemDTO.getId() + " already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add Cart Item to the database", e);
        }
    }

    @Override
    public boolean updateCartItem(CartItemDTO cartItemDTO) {
        CartItem cartItem = CartItemConverter.toEntity(cartItemDTO);
        return cartItemDAO.updateCartItem(cartItem) > 0;
    }

    @Override
    public boolean deleteCartItemById(String id) {
        return cartItemDAO.deleteCartItemById(id) > 0;
    }
}
