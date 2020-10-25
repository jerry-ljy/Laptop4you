package com.laptop4you;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.laptop4you.utils.Laptop;

import java.util.ArrayList;

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.LinearViewHolder>{

    private Context mContext;
    private ArrayList<Laptop> laptops;

    public LinearAdapter(Context context){
        this.mContext = context;
    }
    public LinearAdapter(ArrayList<Laptop> laptop){
        this.laptops = laptop;
    }
    @Override
    public LinearAdapter.LinearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        return new LinearViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_linear_item,parent,false));
    }

    @Override
    public void onBindViewHolder(LinearAdapter.LinearViewHolder holder, int position) {

        Laptop laptop = laptops.get(position);

        Glide.with(holder.itemView).load(laptop.img).into(holder.imageView);

        holder.tv_name.setText(laptop.name);
        holder.tv_price.setText(laptop.price);
        holder.tv_processor.setText(laptop.processor);
        holder.tv_graphics.setText(laptop.graphics);
        String memory = laptop.memory + "GB";
        holder.tv_memory.setText(memory);
        holder.tv_hd.setText(laptop.hard_drive);
    }

    @Override
    public int getItemCount() {
        return laptops.size();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tv_name;
        private  TextView tv_price;
        private  TextView tv_processor;
        private  TextView tv_graphics;
        private  TextView tv_memory;
        private  TextView tv_hd;

        public LinearViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_laptop);
            tv_name = itemView.findViewById(R.id.tv_model);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_processor = itemView.findViewById(R.id.tv_processor);
            tv_graphics = itemView.findViewById(R.id.tv_graphics);
            tv_memory = itemView.findViewById(R.id.tv_memory);
            tv_hd = itemView.findViewById(R.id.tv_hd);
        }
    }
}
