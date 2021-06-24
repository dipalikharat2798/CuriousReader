package com.example.curiousreader.Adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.curiousreader.Model.FileModel;
import com.example.curiousreader.R;
import com.example.curiousreader.ViewPdfActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyAdapter extends FirebaseRecyclerAdapter<FileModel,MyAdapter.MyviewHolder> {
FirebaseDatabase database;
DatabaseReference databaseReference;
    public MyAdapter(@NonNull FirebaseRecyclerOptions<FileModel> options) {
        super(options);
        database=FirebaseDatabase.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference("mydocuments");
    }

    @Override
    protected void onBindViewHolder(@NonNull MyviewHolder holder, int position, @NonNull FileModel model) {
        holder.header.setText(model.getFilename());
        int nov,nol,nod;
        String id;
        nov=model.getNov();
        nol=model.getNol();
        nod=model.getNod();
        id=model.getId();
        holder.textviewbook.setText(String.valueOf(model.getNov()));
        holder.textlike.setText(String.valueOf(model.getNol()));

        holder.readbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nov1 =novAdd(nov);
                holder.textviewbook.setText(String.valueOf(nov1));
               // databaseReference.child(id).child("nov").setValue(nov1);
            }
        });
        holder.likebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nov1 =novAdd(nol);
                holder.textlike.setText(String.valueOf(nov1));
            }
        });


        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.img1.getContext(), ViewPdfActivity.class);
                intent.putExtra("filename",model.getFilename());
                intent.putExtra("fileurl",model.getFileurl());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.img1.getContext().startActivity(intent);
            }
        });
    }

    private int novAdd(int nov) {
        nov=nov+1;
        return nov;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return  new MyviewHolder(view);
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{

        ImageView img1;
        TextView header;
        ImageView readbook,likebook,dislikebook;
        TextView textviewbook,textlike,textdislike;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.img1);
            header=itemView.findViewById(R.id.header);

            readbook=itemView.findViewById(R.id.readbook);
            likebook=itemView.findViewById(R.id.likebook);

            textviewbook=itemView.findViewById(R.id.textviewbook);
            textlike=itemView.findViewById(R.id.textlike);

        }
    }
}
