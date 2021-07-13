package com.codechallenge.shopapplication.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Order implements Serializable {
    private String orderId;
    private String customerId;
    //Setting date format and central time zone
    @JsonFormat(pattern="yyyy-MM-dd", timezone= "America/Chicago")
    private Date date;
    private String status;
    private List<Product> products;

}
