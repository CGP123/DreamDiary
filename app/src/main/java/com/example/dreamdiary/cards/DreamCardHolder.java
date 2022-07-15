package com.example.dreamdiary.cards;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamdiary.R;

public class DreamCardHolder extends RecyclerView.ViewHolder{
    ImageView cardImage;
    TextView cardText;

    public DreamCardHolder(@NonNull View itemView) {
        super(itemView);
        this.cardImage = itemView.findViewById(R.id.dreamCardImage);
        this.cardText = itemView.findViewById(R.id.dreamCardText);
    }
}
