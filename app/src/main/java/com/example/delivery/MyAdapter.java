package com.example.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<UserHelperClass> Lo;

    public MyAdapter(Context c, ArrayList<UserHelperClass> v)
    {
        context=c;
        Lo=v;
    }


    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.activity_my_adapter,parent,false);


        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.TxtProvince.setText(Lo.get(position).getProvince());
        holder.TxtDistrict.setText(Lo.get(position).getDistrict());
        holder.TxtCity.setText(Lo.get(position).getCity());
        holder.TxtAddress.setText(Lo.get(position).getAddress());


//----------------------delete------------------------

        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("location");

                reference.child(Lo.get(holder.getAdapterPosition()).getAddress()).removeValue();

                Toast.makeText(context, "Deleted",
                        Toast.LENGTH_LONG).show();

            }
        });

//-----------------update----------------------------------

        holder.Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, UpdateLocation.class);

                intent.putExtra("Province",Lo.get(holder.getAdapterPosition()).getProvince());
                intent.putExtra("District",Lo.get(holder.getAdapterPosition()).getDistrict());
                intent.putExtra("City",Lo.get(holder.getAdapterPosition()).getCity());
                intent.putExtra("Address",Lo.get(holder.getAdapterPosition()).getAddress());


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);

            }
        });



    }


    public int getItemCount()
    {
        return Lo.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView TxtProvince,TxtDistrict,TxtCity,TxtAddress;
        ConstraintLayout mainLayout;
        Button Update,Delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TxtProvince=(TextView) itemView.findViewById(R.id.txt_Province);
            TxtDistrict=(TextView) itemView.findViewById(R.id.txt_District);
            TxtCity=(TextView) itemView.findViewById(R.id.txt_City);
            TxtAddress=(TextView) itemView.findViewById(R.id.txt_Address);


            mainLayout=(itemView.findViewById(R.id.MainLayout));

            Update = (Button) itemView.findViewById(R.id.BtnUpdate);
            Delete = (Button) itemView.findViewById(R.id.BtnDelete);

        }

    }

}
