package com.example.infosphere;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Model> modelArrayList;

    public Adapter(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.layout_item,null,false);
       return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,WebViewActivity.class);
                intent.putExtra("url",modelArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });


        holder.mTime.setText("Published At:-"+modelArrayList.get(position).getPublishedAt());
        holder.mAuthor.setText(modelArrayList.get(position).getAuthor());
        holder.mHeading.setText(modelArrayList.get(position).getTitle());
        holder.mContent.setText(modelArrayList.get(position).getDescription());
        Glide.with(context).load(modelArrayList.get(position).getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

            TextView mHeading,mTime,mAuthor,mContent,mCategory;
            CardView cardView;
            ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAuthor = itemView.findViewById(R.id.author);
            mHeading = itemView.findViewById(R.id.mainHeading);
            mTime = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.imageView);
            mContent = itemView.findViewById(R.id.content);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
