package com.example.demo.controllers;

import com.example.demo.services.CheckoutSrvc;
import com.example.demo.services.Purchase;
import com.example.demo.services.PurchaseResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin("http://localhost:4200")
public class CheckoutController {


    @Autowired
    private CheckoutService checkoutService;

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@Valid @RequestBody Purchase purchase) {

        PurchaseResponse savedPurchase = checkoutService.placeOrder(purchase);

        return savedPurchase;
    }


}

