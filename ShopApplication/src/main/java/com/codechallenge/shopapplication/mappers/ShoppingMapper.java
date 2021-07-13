package com.codechallenge.shopapplication.mappers;

import com.codechallenge.shopapplication.constants.ShopConstants;
import com.codechallenge.shopapplication.filewriters.ShopFileWriter;
import com.codechallenge.shopapplication.helpers.ShopHelper;
import com.codechallenge.shopapplication.models.request.CreateOrderRequest;
import com.codechallenge.shopapplication.models.request.ProductBreakdownRequest;
import com.codechallenge.shopapplication.models.response.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Component
@RequiredArgsConstructor
public class ShoppingMapper {

    @Autowired
    private ShopHelper shopHelper;
    @Autowired
    private ShopFileWriter fileWriter;

    /**
     * Read data from jsonfle
     * Map data to OrderListResponse
     * */
    public List<Order> mapOrderJsonInfo() throws IOException {
        List<Order> orderList = new ArrayList<>();
        //create ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        //Set default time zone as JVM timezone due to one day difference between original date and formatted date.
        objectMapper.setTimeZone(TimeZone.getDefault());
        //read json file and convert to customer object
        OrderListResponse orderListResponse = objectMapper.readValue(new ClassPathResource(ShopConstants.ORDERS_FILE).getInputStream(), OrderListResponse.class);
        if (orderListResponse != null) {
            orderList = orderListResponse.getOrderList();
        }
        return orderList;
    }

    /**
     * Map request info to response
     * */
    public ProductBreakdownResponse mapProductBreakdownResponse(ProductBreakdownRequest request, ProductBreakdownResponse response, List<Product> products){
        if (request != null){
            if (request.getStartDate() != null) {
                response.setStartDate(request.getStartDate());
            }
            if (request.getEndDate() != null) {
                response.setEndDate(request.getEndDate());
            }
            response.setProducts(products);
            shopHelper.getProductsSold(request, response, products);
        }
        return response;
    }

    /**
     * Update json file with createOrderRequest info
     * Map and return createOrderResponse
     * */
    public CreateOrderResponse mapCreateOrderInfo(CreateOrderRequest request) {
        CreateOrderResponse response = new CreateOrderResponse();
        Order order = new Order();
        order.setOrderId(request.getOrderId());
        order.setCustomerId(request.getCustomerId());
        order.setDate(request.getDate());
        order.setStatus(request.getStatus());
        order.setProducts(request.getProducts());
        try {
            fileWriter.updateOrdersFile(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setOrder(order);
        return response;
    }
}
