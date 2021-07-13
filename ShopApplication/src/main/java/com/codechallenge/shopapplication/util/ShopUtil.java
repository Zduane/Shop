package com.codechallenge.shopapplication.util;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class ShopUtil {



    /**
     * Convert Date to LocalDate
     * */
    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


    public static double formatTwoDecimalPlaces(double value) {
        DecimalFormat df = new DecimalFormat("#.00");
        return new Double(df.format(value));
    }
}
