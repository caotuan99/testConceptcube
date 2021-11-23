package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageAdapter  extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{
    private List<Integer> list;

    public ImageAdapter(List<Integer> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        int i=list.get(position);
        holder.imgCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imgCard.setImageResource(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null) return list.size();
        else
        return 0;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgCard;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCard=itemView.findViewById(R.id.imgCard);
        }
    }
}
