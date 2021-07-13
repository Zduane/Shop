package com.codechallenge.shopapplication.controllers;

import com.codechallenge.shopapplication.constants.ShopConstants;
import com.codechallenge.shopapplication.models.request.ProductBreakdownRequest;
import com.codechallenge.shopapplication.models.response.CreateOrderResponse;
import com.codechallenge.shopapplication.models.response.Order;
import com.codechallenge.shopapplication.models.response.Product;
import com.codechallenge.shopapplication.models.response.ProductBreakdownResponse;
import com.codechallenge.shopapplication.services.ShoppingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static groovy.json.JsonOutput.toJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ShopController.class)
class ShopControllerTest {

    @MockBean
    private ShoppingService shoppingService;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getValidProductsResponse() throws Exception {
        //mock response
        ProductBreakdownResponse mockProdBDResp = new ProductBreakdownResponse();
        mockProdBDResp.setProducts(new ArrayList<>());
        mockProdBDResp.setStartDate(new Date());
        mockProdBDResp.setEndDate(new Date());
        mockProdBDResp.setProductsSoldPerDay(Mockito.anyDouble());

        mvc.perform(post("/shop/products/breakdown")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(mockProdBDResp)))
                .andExpect(status().isOk());
    }

    @Test
    void getOrders_Status200() throws Exception {
        String customerId = "0034229";
        mvc.perform(MockMvcRequestBuilders.get("/shop/orders/" + customerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createOrder_Status200() throws Exception {

        CreateOrderResponse orderResponse = createMockOrderResponse();

        mvc.perform(post("/shop/orders/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(orderResponse)))
                .andExpect(status().isOk());
    }

    CreateOrderResponse createMockOrderResponse() {
        CreateOrderResponse or = new CreateOrderResponse();
        Order order = new Order();
        Product product = new Product();
        product.setProductId("8383");
        product.setAmount(2.3);
        product.setCategory("SEAFOOD");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        order.setOrderId("8494");
        order.setDate(new Date());
        order.setStatus(ShopConstants.STATUS_ON_THE_WAY);
        order.setCustomerId("83839");
        order.setProducts(productList);
        or.setOrder(order);
        return or;
    }
}
