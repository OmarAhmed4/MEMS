package com.example.omarahmed.msecg;

import java.io.Serializable;

/**
 * Created by Omar Ahmed on 10/4/2018.
 */

public class StoreData implements Serializable{
    public String brand;
    public String contact;
    public String time;
    public String details;
    public String price;
    public String priority;
    public String warranty;
    public String image_url;
    public String name;

    public StoreData(){}

    public StoreData(String name,String brand,String contact,String time,String details,String price,String priority,String warranty,String image_url)
    {
        this.brand=brand;
        this.contact=contact;
        this.time=time;
        this.details=details;
        this.price=price;
        this.priority=priority;
        this.warranty=warranty;
        this.image_url=image_url;
        this.name=name;

    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String detail) {
        this.details = detail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }
}
