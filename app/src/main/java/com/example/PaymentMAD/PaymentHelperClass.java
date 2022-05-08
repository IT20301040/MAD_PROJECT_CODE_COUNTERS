package com.example.kottupaymentmethod;

public class PaymentHelperClass {
    String PhoneNumber,CardNumber,CardHolderName,ExpireDate,CW;

    public PaymentHelperClass() {

    }

    public PaymentHelperClass(String phoneNumber,String cardNumber, String cardHolderName, String expireDate, String CW) {
        this.PhoneNumber = phoneNumber;
        this.CardNumber = cardNumber;
        this.CardHolderName = cardHolderName;
        this.ExpireDate = expireDate;
        this.CW = CW;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {

        CardNumber = cardNumber;
    }

    public String getCardHolderName() {

        return CardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {

        CardHolderName = cardHolderName;
    }

    public String getExpireDate() {

        return ExpireDate;
    }

    public void setExpireDate(String expireDate) {

        ExpireDate = expireDate;
    }

    public String getCW() {

        return CW;
    }

    public void setCW(String CW) {

        this.CW = CW;
    }
}
