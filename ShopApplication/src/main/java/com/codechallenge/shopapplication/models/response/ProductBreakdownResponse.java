package com.codechallenge.shopapplication.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ProductBreakdownResponse implements Serializable {

    private List<Product> products;
    //Setting date format and central time zone
    @JsonFormat(pattern="yyyy-MM-dd", timezone= "America/Chicago")
    private Date startDate;
    //Setting date format and central time zone
    @JsonFormat(pattern="yyyy-MM-dd", timezone= "America/Chicago")
    private Date endDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double productsSoldPerDay;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double productsSoldPerWeek;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double productsSoldPerMonth;
}
