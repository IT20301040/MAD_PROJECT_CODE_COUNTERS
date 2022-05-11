package com.example.kottupaymentmethod;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context   context;
    ArrayList<PaymentHelperClass> p;

    public MyAdapter(Context c, ArrayList<PaymentHelperClass> v)
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

        holder.TxtPhoneNumber.setText(p.get(position).getPhoneNumber());
        holder.TxtCVV.setText(p.get(position).getCW());
        holder.TxtCardHolderName.setText(p.get(position).getCardHolderName());
        holder.TxtCardNumber.setText(p.get(position).getCardNumber());
        holder.TxtEXPDate.setText(p.get(position).getExpireDate());



        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("CardDetails");

                reference.child(p.get(holder.getAdapterPosition()).getCardNumber()).removeValue();

                Toast.makeText(context, "Deleted",
                        Toast.LENGTH_LONG).show();

            }
        });


       holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UpdateDetails.class);
                intent.putExtra("CardNumber",p.get(holder.getAdapterPosition()).getCardNumber());
                intent.putExtra("CardHolderName",p.get(holder.getAdapterPosition()).getCardHolderName());
                intent.putExtra("ExpireDate",p.get(holder.getAdapterPosition()).getExpireDate());
                intent.putExtra("CW",p.get(holder.getAdapterPosition()).getCW());
                intent.putExtra("PhoneNumber",p.get(holder.getAdapterPosition()).getPhoneNumber());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);

            }
        });



    }


    public int getItemCount()
    {
        return p.size();
        // return Images.length;

    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView TxtPhoneNumber,TxtCardNumber,TxtCardHolderName,TxtEXPDate,TxtCVV;
        ConstraintLayout mainLayout;
        Button btnUpdate,btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TxtPhoneNumber=(TextView) itemView.findViewById(R.id.txt_PhoneNumber);
            TxtCardNumber=(TextView) itemView.findViewById(R.id.txt_CardNumber);
            TxtCardHolderName=(TextView) itemView.findViewById(R.id.txt_CardHolderName);
            TxtEXPDate=(TextView) itemView.findViewById(R.id.txt_ExpDate);
            TxtCVV=(TextView) itemView.findViewById(R.id.txt_CVV);

            mainLayout=(itemView.findViewById(R.id.MainLayout));

            btnUpdate = (Button) itemView.findViewById(R.id.BtnUpdate);
            btnDelete = (Button) itemView.findViewById(R.id.BtnDelete);

        }

    }

}
