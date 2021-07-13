package com.codechallenge.shopapplication.models.request;

import com.codechallenge.shopapplication.models.response.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;


@Data
public class CreateOrderRequest {

    @NotNull
    private String customerId;
    @NotNull
    private String orderId;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd", timezone= "America/Chicago")
    private Date date;
    @NotNull
    private String status;
    @NotNull
    private List<Product> products;
}
