package com.codechallenge.shopapplication.models.request;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class ProductBreakdownRequest implements Serializable {

    //Setting date format and central time zone
    @JsonFormat(pattern="yyyy-MM-dd", timezone= "America/Chicago")
    @NotNull
    private Date startDate;
    //Setting date format and central time zone
    @JsonFormat(pattern="yyyy-MM-dd", timezone= "America/Chicago")
    @NotNull
    private Date endDate;
    @NotNull
    private String soldPer;

}
