package com.example.order;

public class UserHelperClass {
    String Quantity,Price,DelieveryCharges,Total;

    public UserHelperClass() {
        ;
    }

    public UserHelperClass(String quantity, String price, String delieveryCharges, String total) {
        Quantity = quantity;
        Price = price;
        DelieveryCharges = delieveryCharges;
        Total = total;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDelieveryCharges() {
        return DelieveryCharges;
    }

    public void setDelieveryCharges(String delieveryCharges) {
        DelieveryCharges = delieveryCharges;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
