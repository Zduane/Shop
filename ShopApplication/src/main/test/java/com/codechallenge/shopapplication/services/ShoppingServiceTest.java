package com.codechallenge.shopapplication.services;

import com.codechallenge.shopapplication.constants.ShopConstants;
import com.codechallenge.shopapplication.helpers.ShopHelper;
import com.codechallenge.shopapplication.mappers.ShoppingMapper;
import com.codechallenge.shopapplication.models.request.CreateOrderRequest;
import com.codechallenge.shopapplication.models.response.CreateOrderResponse;
import com.codechallenge.shopapplication.models.response.Order;
import com.codechallenge.shopapplication.models.response.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class ShoppingServiceTest {

    @MockBean
    private ShoppingService shoppingService;
    @MockBean
    private ShopHelper helper;
    @MockBean
    private ShoppingMapper mapper;


    @BeforeEach
    void setUp() {
    }

    @Test
    @Disabled
    public void shouldReturnValidProductsBreakdownResponse() {
    }

    @Test
    @Disabled
    public void shouldRetrieveValidOrdersResponse() {
    }

    @Test
    public void shouldCreateValidOrder() throws ParseException {
        CreateOrderRequest or = createMockOrderRequest();
        given(shoppingService.createOrder(or)).willReturn(createMockOrderResponse());
        CreateOrderResponse response = shoppingService.createOrder(or);

        assertEquals(createMockOrderResponse(), response);
    }

    CreateOrderRequest createMockOrderRequest() throws ParseException {
        CreateOrderRequest or = new CreateOrderRequest();
        Product product = new Product();
        product.setProductId("83873");
        product.setAmount(2.3);
        product.setCategory("SEAFOOD");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        or.setOrderId("84594");
        or.setDate(getDate());
        or.setStatus(ShopConstants.STATUS_ON_THE_WAY);
        or.setCustomerId("838739");
        or.setProducts(productList);
        return or;
    }

    CreateOrderResponse createMockOrderResponse() throws ParseException {
        CreateOrderResponse or = new CreateOrderResponse();
        Order order = new Order();
        Product product = new Product();
        product.setProductId("83873");
        product.setAmount(2.3);
        product.setCategory("SEAFOOD");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        order.setOrderId("84594");
        order.setDate(getDate());
        order.setStatus(ShopConstants.STATUS_ON_THE_WAY);
        order.setCustomerId("84594");
        order.setProducts(productList);
        or.setOrder(order);
        return or;
    }

    Date getDate() throws ParseException {
        String date = "2021-06-15";
        Date date1 = new SimpleDateFormat("YYYY-MM-dd").parse(date);
        return date1;
    }

    //TODO Add negative case tests
}
