package com.codechallenge.shopapplication.helpers;

import com.codechallenge.shopapplication.constants.ShopConstants;
import com.codechallenge.shopapplication.models.request.ProductBreakdownRequest;
import com.codechallenge.shopapplication.models.response.Order;
import com.codechallenge.shopapplication.models.response.Product;
import com.codechallenge.shopapplication.models.response.ProductBreakdownResponse;
import com.codechallenge.shopapplication.util.ShopUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ShopHelper {

    /**
     * Validate SoldPer value
     * */
    public boolean validSoldPerValue(String value) {

        if (!ShopConstants.SOLD_PER_DAY.equalsIgnoreCase(value) && !ShopConstants.SOLD_PER_WEEK.equalsIgnoreCase(value)
                && !ShopConstants.SOLD_PER_MONTH.equalsIgnoreCase(value)) {
            return false;
        }
        return true;
    }

    /**
     * Determine which SoldPer value to use
    * */
    public double getProductsSold(ProductBreakdownRequest request, ProductBreakdownResponse response, List<Product> filteredProducts){
        if (ShopConstants.SOLD_PER_DAY.equalsIgnoreCase(request.getSoldPer())) {
            response.setProductsSoldPerDay(getProductsSoldPerDay(request.getSoldPer(), request.getStartDate(), request.getEndDate(), filteredProducts));
        } else if (ShopConstants.SOLD_PER_WEEK.equalsIgnoreCase(request.getSoldPer())) {
            response.setProductsSoldPerWeek(getProductsSoldPerWeek(request.getSoldPer(), request.getStartDate(), request.getEndDate(), filteredProducts));
        } else if (ShopConstants.SOLD_PER_MONTH.equalsIgnoreCase(request.getSoldPer())) {
            response.setProductsSoldPerMonth(getProductsSoldPerMonth(request.getSoldPer(), request.getStartDate(), request.getEndDate(), filteredProducts));
        }
        return 0;
    }

    public double getProductsSoldPerDay(String soldPer, Date startDate, Date endDate, List<Product> filteredProducts) {
        if (validSoldPerValue(soldPer)) {
                try {
                    long daysBetween = ChronoUnit.DAYS.between(ShopUtil.convertToLocalDate(startDate), ShopUtil.convertToLocalDate(endDate));
                    return getQuantityPerValue(filteredProducts, daysBetween);
                } catch (Exception e) {
                    e.printStackTrace();
            }
        }
        return 0;
    }

    public double getProductsSoldPerWeek(String soldPer, Date startDate, Date endDate, List<Product> filteredProducts) {
        if (validSoldPerValue(soldPer)) {
            try {
                long weeksBetween = ChronoUnit.WEEKS.between(ShopUtil.convertToLocalDate(startDate), ShopUtil.convertToLocalDate(endDate));
                return getQuantityPerValue(filteredProducts, weeksBetween);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public double getProductsSoldPerMonth(String soldPer, Date startDate, Date endDate, List<Product> filteredProducts) {
        if (validSoldPerValue(soldPer)) {
            try {
                long monthsBetween = ChronoUnit.MONTHS.between(ShopUtil.convertToLocalDate(startDate), ShopUtil.convertToLocalDate(endDate));
                return getQuantityPerValue(filteredProducts, monthsBetween);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * Get average quantity sold per date value
     * */
    public double getQuantityPerValue(List<Product> productList, long value) {
        double size = productList.size();
        if(value == 0) {
            return size / 1;
        }
        return ShopUtil.formatTwoDecimalPlaces(size / value);
    }

    /**
     * Filter through orders between start and end dates
     * Filter through products belonging to those orders
     * Return new filtered product list
    * */
    public List<Product> filterProductList(List<Order> orderList, Date startDate, Date endDate) {
        List<Product> filteredProducts = new ArrayList<>();
        List<Order> filteredOrderList = orderList
                .stream()
                .filter(order -> !order.getDate().before(startDate) && !order.getDate().after(endDate))
                .collect(Collectors.toList());

        filteredOrderList
                .stream()
                .forEach(order -> order.getProducts()
                        .stream()
                        .forEach(product -> filteredProducts.add(product)));

        return filteredProducts;
    }
}
