package com.codechallenge.shopapplication.models.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {

    private double amount;
    private String productId;
    private String category;
}
