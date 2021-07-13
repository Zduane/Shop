package com.codechallenge.shopapplication.filewriters;

import com.codechallenge.shopapplication.constants.ShopConstants;
import com.codechallenge.shopapplication.models.response.Order;
import com.codechallenge.shopapplication.models.response.OrderListResponse;
import com.codechallenge.shopapplication.models.response.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Component
public class ShopFileWriter {

    public void updateOrdersFile(Order order) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type orderType = new TypeToken<OrderListResponse>(){}.getType();
        FileReader fr = new FileReader(new ClassPathResource(ShopConstants.ORDERS_FILE).getFile());
        OrderListResponse orders = gson.fromJson(fr, orderType);
        fr.close();

        // If it was an empty one create initial list
        if(orders != null && orders.getOrderList() == null) {
            orders.setOrderList(new ArrayList<>());
        }
        // Add new item to the list
        orders.getOrderList().add((order));
        // No append replace the whole file
        FileWriter fw  = new FileWriter(new ClassPathResource(ShopConstants.ORDERS_FILE).getFile());
        gson.toJson(orders, fw);
        fw.close();

    }

}
