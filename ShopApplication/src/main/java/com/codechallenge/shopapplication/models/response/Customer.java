package com.codechallenge.shopapplication.models.response;

import lombok.Data;

import java.util.List;

@Data
public class Customer {

    private String customerId;
    private List<Order> orders;
}
