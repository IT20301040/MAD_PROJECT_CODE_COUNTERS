package com.example.order;

public class cheeseModel {
    private String key;
    private String Product_Name;
    private String Price;
    private String DeliverCharges;
    private String Quantity;

    public cheeseModel(){

    }
    public cheeseModel(String Product_Name,String Price,String DeliverCharges,String Quantity){
        this.Product_Name =Product_Name;
        this.Price = Price;
        this.DeliverCharges =DeliverCharges;
        this.Quantity = Quantity;
    }

    public String getKey(){return key; }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String Product_Name) {
        this.Product_Name = Product_Name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = Price;
    }

    public String getDeliverCharges() {
        return DeliverCharges;
    }

    public void setDeliverCharges(String DeliverCharges) {
        this.DeliverCharges = DeliverCharges;}

    public String getQuantity() { return Quantity;}

    public void setQuantity(String Quantity) {
        this.Quantity= Quantity;
    }

   /* @Override
    public String toString() {
        return "cheeseModel{" +
                "key='" + key + '\'' +
                ", Product_Name='" + Product_Name + '\'' +
                ", Price='" + Price + '\'' +
                ",DeliverCharges='" + DeliverCharges + '\'' +
                ", Quantity='" +Quantity + '\'' +
                '}';*/
    }
//}
