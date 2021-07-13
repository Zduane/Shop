package com.codechallenge.shopapplication.controllers;

import com.codechallenge.shopapplication.models.request.CreateOrderRequest;
import com.codechallenge.shopapplication.models.request.ProductBreakdownRequest;
import com.codechallenge.shopapplication.models.response.CreateOrderResponse;
import com.codechallenge.shopapplication.models.response.Order;
import com.codechallenge.shopapplication.models.response.ProductBreakdownResponse;
import com.codechallenge.shopapplication.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/shop")
@RestController
public class ShopController {

    @Autowired
    private ShoppingService shoppingService;

    @PostMapping(value ="/products/breakdown")
    public ResponseEntity<ProductBreakdownResponse> getProducts(@RequestBody ProductBreakdownRequest request) throws Exception {
         ProductBreakdownResponse response = this.shoppingService.getProductsBreakdown(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value ="/orders/{customerId}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable("customerId") String customerId) throws Exception {
        List<Order> response = this.shoppingService.retrieveOrders(customerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value ="/orders/create")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) throws Exception {
        CreateOrderResponse response = this.shoppingService.createOrder(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
