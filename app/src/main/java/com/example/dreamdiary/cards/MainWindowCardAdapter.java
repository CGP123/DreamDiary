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

public class MainWindowCardAdapter extends RecyclerView.Adapter<MainWindowCardHolder> {
    private List<MainWindowCard> cards;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public MainWindowCardAdapter(Context context, List<MainWindowCard> cards){
        this.context = context;
        this.cards = cards;
        this.mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public MainWindowCardHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View recycleViewItem = mLayoutInflater.inflate(R.layout.recyclerview_main_card_layout, parent, false);

        recycleViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRecycleItemClick((RecyclerView) parent, v);
            }
        });
        return new MainWindowCardHolder(recycleViewItem);
    }
    @Override
    public void onBindViewHolder(MainWindowCardHolder holder, int position){
        MainWindowCard card = this.cards.get(position);

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
        MainWindowCard card = this.cards.get(itemPosition);
        if (card.getCardText().equals("Мой профиль")){
            Intent intent = new Intent(context, Profile.class);
            context.startActivity(intent);
        }
        else if (card.getCardText().equals("Мои мечты")){
            Intent intent = new Intent(context, Dreams.class);
            context.startActivity(intent);
        }
    }
}
