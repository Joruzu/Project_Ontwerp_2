package com.example.sarnamibasic;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.ViewHolder> {
    private ArrayList<Translation> trans = new ArrayList<>();
    private Context context;
    private int recViewIndex = -1;
    private String audioTableId;
    private int textSizeValue;

    public RecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recview_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final String songNameId = String.valueOf(trans.get(position).getId());
        holder.txtSar.setTextSize(textSizeValue);
        holder.txtNed.setTextSize(textSizeValue-3);
        holder.txtSar.setText(trans.get(position).getSar());
        holder.txtNed.setText(trans.get(position).getNed());
        holder.recViewParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recViewIndex = position;
                notifyDataSetChanged();
                MediaPlayer mp = MediaPlayer.create(context,
                        context.getResources().getIdentifier(getAudioTableId() + songNameId, "raw",
                                context.getPackageName()));
                mp.start();
            }
        });
        if(recViewIndex == position) {
            holder.recViewParent.setBackgroundColor(context.getResources().getColor(R.color.highlightColor));
        }
        else {
            holder.recViewParent.setBackground(context.getDrawable(R.drawable.borderbottom));
        }
    }

    @Override
    public int getItemCount() {
        return trans.size();
    }

    public void setTranslation(ArrayList<Translation> trans) {
        this.trans = trans;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSar;
        private TextView txtNed;
        private ConstraintLayout recViewParent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSar = itemView.findViewById(R.id.txtSarnami);
            txtNed = itemView.findViewById(R.id.txtNederlands);
            recViewParent = itemView.findViewById(R.id.recViewParent);
        }
    }

    public void setAudioTableId(String tableId) {
        this.audioTableId = tableId;
    }

    public String getAudioTableId() {
        return audioTableId;
    }

    public void setTextSizeValue(int value) {
        this.textSizeValue = value;
    }
}
