package hu.czompi.ch_app;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import hu.czompi.ch_app.inventory.OrderManager;
import hu.czompi.ch_app.inventory.Product;
import hu.czompi.ch_app.inventory.ProductImpl;
import hu.czompi.ch_app.inventory.ProductManager;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;


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
        try {
            orderManager.add(order);
            LOGGER.info("You ordered the following items:");
            for (String o : order) {
                Product p = productManager.get(o);
                LOGGER.info("  " + p.toString());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}