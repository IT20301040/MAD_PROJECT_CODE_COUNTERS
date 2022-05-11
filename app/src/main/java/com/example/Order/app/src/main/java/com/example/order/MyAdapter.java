package com.example.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter {


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        Context   context;
        ArrayList<cheeseModel> p;

        public MyAdapter(Context c, ArrayList<cheeseModel> v)
        {
            context=c;
            p=v;
        }
        @NonNull
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //  return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_raw,parent,false));
            LayoutInflater inflater=LayoutInflater.from(context);
            View view= inflater.inflate(R.layout.my_raw,parent,false);


            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

            holder.Pname.setText(p.get(position).getProduct_Name());
            holder.TxtCVV.setText(p.get(position).getPrice());
            holder.TxtCardHolderName.setText(p.get(position).getDeliverCharges());
            holder.TxtCardNumber.setText(p.get(position).getQuantity());


            holder.Remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("CardDetails");

                    reference.child(p.get(holder.getAdapterPosition()).getCardNumber()).removeValue();

                    Toast.makeText(context, "Deleted",
                            Toast.LENGTH_LONG).show();

                }
            });
}
