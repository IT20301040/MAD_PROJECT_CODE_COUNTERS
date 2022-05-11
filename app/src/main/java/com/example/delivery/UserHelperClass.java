package com.example.location;

public class UserHelperClass {

    String province, district, city, address;

    public UserHelperClass() { }

    public UserHelperClass(String province, String district, String city, String address) {
        this.province = province;
        this.district = district;
        this.city = city;
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
