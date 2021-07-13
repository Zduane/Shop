package com.codechallenge.shopapplication.models.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetOrdersRequest {

    @NotNull
    private String customerId;
}
