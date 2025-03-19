package com.tracktive.orderservice.repository.Impl;

import com.tracktive.orderservice.exception.DatabaseOperationException;
import com.tracktive.orderservice.exception.OrderAlreadyExistException;
import com.tracktive.orderservice.model.DAO.CartItemDAO;
import com.tracktive.orderservice.model.entity.CartItem;
import com.tracktive.orderservice.repository.CartItemRepository;
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
    public List<CartItem> selectAllCartItems() {
        return cartItemDAO.selectAllCartItems();
    }

    @Override
    public List<CartItem> selectAllByRetailerId(String id) {
        return cartItemDAO.selectAllByRetailerId(id);
    }

    @Override
    public Optional<CartItem> selectCartItemById(String id) {
        return cartItemDAO.selectCartItemById(id);
    }

    @Override
    public Optional<CartItem> lockCartItemById(String id) {
        return  cartItemDAO.lockCartItemById(id);
    }

    @Override
    public boolean addCartItem(CartItem cartItem) {
        try {
            return cartItemDAO.addCartItem(cartItem) > 0;
        } catch (DuplicateKeyException e) {
            throw new OrderAlreadyExistException("Order with id " + cartItem.getId() + " already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add order to the database", e);
        }
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) {
        return cartItemDAO.updateCartItem(cartItem) > 0;
    }

    @Override
    public boolean deleteCartItemById(String id) {
        return cartItemDAO.deleteCartItemById(id) > 0;
    }
}
