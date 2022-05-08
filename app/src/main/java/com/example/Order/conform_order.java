package com.example.order;


import android.content.Intent;
import android.view.View;

public class conform_order {
    //show all data

    private void showAllDate() {

        Intent intent = getIntent();
        String Or_Quantity = intent.getStringExtra("Quantity");
        String Or_Price = intent.getStringExtra("Price");
        String Or_DelieveryCharges = intent.getStringExtra("DelieveryCharges");
        String Or_Total = intent.getStringExtra("Total");

        Oquantity.getEditText().setText(Or_Quantity);
        OPrice.getEditText().setText(Or_Price);
        ODelieveryCharges.getEditText().setText(Or_DelieveryCharges);
        OTotal.getEditText().setText(Or_Total);
    }

    public void onUpdate(View view){

        if (isQuantityChanged() || isPriceChanged() || isDelieveryChargesChanged() || isTotalChanged()){
            Toast.makeText(this, "Data has Changed", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "Data same", Toast.LENGTH_LONG).show();
    }

    private boolean isAddressChanged() {
        if (!Or_Quantity.equals(Oquantity.getEditText().getText().toString())){

            reference.child(Or_Quantity).child("Quantity").setValue(OQuantity.getEditText().getText().toString());
            Or_Quantity = OQuantity.getEditText().getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isCityChanged() {
        if (!Or_Price.equals(OPrice.getEditText().getText().toString())){

            reference.child(OPrice).child("city").setValue(OPrice.getEditText().getText().toString());
            Or_Price = OPrice.getEditText().getText().toString();
            return true;
        }
        else {
            return false;
        }
    }
}
