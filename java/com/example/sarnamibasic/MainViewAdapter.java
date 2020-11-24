package com.example.sarnamibasic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.ViewHolder> {
    private ArrayList<TableInfo> tableInfo = new ArrayList<>();
    private Context context;

    public MainViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewAdapter.ViewHolder holder, int position) {
        final String tableID = tableInfo.get(position).gettIdName();
        final String cardName = tableInfo.get(position).gettCardName();
        holder.txtCard.setText(cardName);
        holder.imgCard.setImageResource(context.getResources().getIdentifier(tableID,
                "drawable", context.getPackageName()));
        holder.mainRecViewParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(context, RecViewActivity.class);
                myIntent.putExtra("tableId", tableID);
                myIntent.putExtra("cardName", cardName);
                context.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tableInfo.size();
    }

    public void setTableInfo(ArrayList<TableInfo> tableInfo) {
        this.tableInfo = tableInfo;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCard;
        private ImageView imgCard;
        private ConstraintLayout mainRecViewParent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCard = itemView.findViewById(R.id.txtCard);
            imgCard = itemView.findViewById(R.id.imgCard);
            mainRecViewParent = itemView.findViewById(R.id.mainRecVIewParent);
        }
    }
}
