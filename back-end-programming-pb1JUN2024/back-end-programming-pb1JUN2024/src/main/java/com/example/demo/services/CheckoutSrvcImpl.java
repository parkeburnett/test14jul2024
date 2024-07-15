package com.example.demo.services;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import com.example.demo.services.Purchase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutSrvcImpl implements CheckoutSrvc{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Transactional
    @Override
    public PurchaseResponse placeOrder(Purchase purchase){
        // pull the cart and the customer info from the
        // purchase object
        Cart cart = purchase.getCart();
        Customer customer = purchase.getCustomer();
        Set<CartItem> cartItms = purchase.getCartItem();

        // generate a unique order number tracking number
        String orderTrackingNbr = UUID.randomUUID().toString();
        // set the order tracking number
        cart.setOrderTrackingNumber(orderTrackingNbr);
        // set the status of the order
        cart.setStatus(StatusType.ordered);
        // loop through all the cart items and add them to the cart obj
        cartItms.forEach(cartItem -> {
            cart.add(cartItem);
            //cartItem.setCart(cart);
        });

        // if we have gotten this far then we need to save the data to the db
        cartRepository.save(cart);

        // now return the Purchase reponse opbject
        return new PurchaseResponse(orderTrackingNbr);
    }

    // consturctor of the class
    public CheckoutSrvcImpl(CartRepository cartRepository, CartItemRepository cartItemRepository){
        this.cartRepository = cartRepository;
        this.cartItemRepo = cartItemRepository  ;
    }

}
