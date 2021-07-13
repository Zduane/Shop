package com.codechallenge.shopapplication.models.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderListResponse {

    private List<Order> orderList;
}
