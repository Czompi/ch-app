package hu.czompi.ch_app;

import com.google.gson.*;
import hu.czompi.ch_app.inventory.orders.OrderManager;
import hu.czompi.ch_app.inventory.products.Product;
import hu.czompi.ch_app.inventory.products.ProductManager;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class Main {
    public static void main(String[] args) {
        // Loading configurations
        ProductManager productManager = new ProductManager();
        OrderManager orderManager = new OrderManager();
        try {
            productManager.reload();
            orderManager.reload();
        } catch (IOException ex) {
            LOGGER.error("Cannot load configurations. Error: " + ex.getLocalizedMessage());
        }
        // Handle arguments
        String orderJson = "";
        if(args.length >= 1) {
            orderJson = String.join(" ", args);
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Reading data using readLine
            try {
                LOGGER.info("Input (JSON array required): ");
                orderJson = reader.readLine();
            } catch (IOException ex) {
                LOGGER.error(ex);
            }
        }
        var order = new Gson().fromJson(orderJson, String[].class);
        List<Product> currentOrder = new ArrayList<>();
        try {
            int id = orderManager.add(order);
            LOGGER.info("Your order (#" + id + ") contains the following products:");
            for (String o : order) {
                var p = productManager.get(o);
                currentOrder.add(p);
                LOGGER.info("  " + p.toString());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}