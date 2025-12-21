//package com.Jonathan.lobengine; // Note: your package name might be different

import java.math.BigDecimal; // You need this for financial precision

public class Order {
    long id;
    BigDecimal price;
    int quantity;
    boolean isBuy; // True for Buy orders, False for Sell orders
    long timestamp; // When the order was placed
}
     