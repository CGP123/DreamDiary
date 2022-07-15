package com.example.dreamdiary.cards;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamdiary.Dreams;
import com.example.dreamdiary.Profile;
import com.example.dreamdiary.R;

import java.util.List;

public class DreamCardAdapter extends RecyclerView.Adapter<DreamCardHolder>{
    private List<DreamCard> cards;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public DreamCardAdapter(Context context, List<DreamCard> cards){
        this.context = context;
        this.cards = cards;
        this.mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public DreamCardHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View recycleViewItem = mLayoutInflater.inflate(R.layout.recyclerview_dream_card_layout, parent, false);

        recycleViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRecycleItemClick((RecyclerView) parent, v);
            }
        });
        return new DreamCardHolder(recycleViewItem);
    }
    @Override
    public void onBindViewHolder(DreamCardHolder holder, int position){
        DreamCard card = this.cards.get(position);
        int imageResId = this.getDrawableResIdByName(card.getCardImageName());
        holder.cardImage.setImageResource(imageResId);
        holder.cardText.setText(card.getCardText());
    }
    @Override
    public int getItemCount(){
        return this.cards.size();
    }

    public int getDrawableResIdByName(String resName){
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName, "drawable", pkgName);
        return resID;
    }
    private void handleRecycleItemClick(RecyclerView recyclerView, View itemView){
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        DreamCard card = this.cards.get(itemPosition);
    }
}
