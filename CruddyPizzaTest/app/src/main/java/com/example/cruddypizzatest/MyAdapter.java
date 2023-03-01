package com.example.cruddypizzatest;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public List<Pizza> orderList;

    public MyAdapter(List<Pizza> pizzaOrdersList)
    {
        orderList = pizzaOrdersList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView pk, custInfo, size, cheese, pepperoni, olives, sausage, date;
//        Button delete, update;

        public ViewHolder(View itemView) {
            super(itemView);
            pk = (TextView) itemView.findViewById(R.id.tvPK);
            custInfo = (TextView) itemView.findViewById(R.id.tvCustInfo);
            size = (TextView) itemView.findViewById(R.id.tvSize);
            cheese = (TextView) itemView.findViewById(R.id.tvCheese);
            pepperoni = (TextView) itemView.findViewById(R.id.tvPepp);
            olives = (TextView) itemView.findViewById(R.id.tvOlive);
            sausage = (TextView) itemView.findViewById(R.id.tvSausa);
            date = (TextView) itemView.findViewById(R.id.textViewDate);
//            delete = (Button) itemView.findViewById(R.id.buttonRVDelete);
//            update = (Button) itemView.findViewById(R.id.buttonRVUpdate);


        }

    }

    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.pk.setText(String.valueOf(orderList.get(position).getRowID()));
        holder.custInfo.setText(orderList.get(position).getCustInfo());
        holder.size.setText(String.valueOf(orderList.get(position).getSize()));
        holder.cheese.setText(String.valueOf(orderList.get(position).getCheese()));
        holder.pepperoni.setText(String.valueOf(orderList.get(position).getPepperoni()));
        holder.olives.setText(String.valueOf(orderList.get(position).getOlives()));
        holder.sausage.setText(String.valueOf(orderList.get(position).getSausage()));
        holder.date.setText(orderList.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    // functions here


}
