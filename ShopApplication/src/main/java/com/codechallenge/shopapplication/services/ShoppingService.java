package com.codechallenge.shopapplication.services;

import com.codechallenge.shopapplication.helpers.ShopHelper;
import com.codechallenge.shopapplication.models.request.CreateOrderRequest;
import com.codechallenge.shopapplication.models.request.ProductBreakdownRequest;
import com.codechallenge.shopapplication.models.response.*;
import com.codechallenge.shopapplication.mappers.ShoppingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ShoppingService {

    @Autowired
    private ShoppingMapper shoppingMapper;
    @Autowired
    private ShopHelper shopHelper;

    /**
     * Endpoint to get list of products sold within a specified date range
     * and quantity sold per specified amount of time
     * */
    public ProductBreakdownResponse getProductsBreakdown(ProductBreakdownRequest request) throws IOException {
        ProductBreakdownResponse response = new ProductBreakdownResponse();
        if (request != null) {
            List<Order> orderList = shoppingMapper.mapOrderJsonInfo();
            List<Product> filteredProducts = shopHelper.filterProductList(orderList, request.getStartDate(), request.getEndDate());
            response = shoppingMapper.mapProductBreakdownResponse(request, response, filteredProducts);
        }
        return response;
    }

    /**
    * Endpoint to retrieve orders based on customerId and sort by date
    * */
    public List<Order> retrieveOrders(String customerId) throws IOException {
        List<Order> orderList = shoppingMapper.mapOrderJsonInfo();
        List<Order> sortedList = new ArrayList<>();
        orderList
                .stream()
                .filter(order -> order.getCustomerId().equalsIgnoreCase(customerId))
                .sorted(Comparator.comparing(Order::getDate))
                .forEach(order -> sortedList.add(order));

        return sortedList;
    }
    /**
     * Create order for customer
     * */
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        if (request != null) {
           return shoppingMapper.mapCreateOrderInfo(request);
        }
        return new CreateOrderResponse();
    }

}


