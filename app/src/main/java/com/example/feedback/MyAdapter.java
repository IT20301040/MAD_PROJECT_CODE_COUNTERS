package com.example.feedback;

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
    ArrayList<DBHelperClass> p;

    public MyAdapter(Context c, ArrayList<DBHelperClass> v)
    {
        context=c;
        p=v;
    }


    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //  return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_raw,parent,false));
        LayoutInflater inflater=LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.activity_my_adapter,parent,false);


        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.TxtEmail.setText(p.get(position).getEmail());
        holder.TxtFeedback.setText(p.get(position).getFeedback());




        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("feedback");

                reference.child(p.get(holder.getAdapterPosition()).getEmail()).removeValue();

                Toast.makeText(context, "Deleted",
                        Toast.LENGTH_LONG).show();

            }
        });


        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MainActivity.class);
                intent.putExtra("Email",p.get(holder.getAdapterPosition()).getEmail());
                intent.putExtra("Feedback",p.get(holder.getAdapterPosition()).getFeedback());


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
        TextView TxtEmail,TxtFeedback;
        ConstraintLayout mainLayout;
        Button btnUpdate,btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TxtEmail=(TextView) itemView.findViewById(R.id.txt_Email1);
            TxtFeedback=(TextView) itemView.findViewById(R.id.txt_Feed);


            mainLayout=(itemView.findViewById(R.id.MainLayout));

            btnUpdate = (Button) itemView.findViewById(R.id.BtnUpdate);
            btnDelete = (Button) itemView.findViewById(R.id.BtnDelete);

        }

    }

}
