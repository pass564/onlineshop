package org.example.pojo;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int id;
    private Product product;
    private Customer customer;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
