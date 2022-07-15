package com.example.dreamdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dreamdiary.cards.MainWindowCard;
import com.example.dreamdiary.cards.MainWindowCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    private RecyclerView cardsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        List<MainWindowCard> cards = getListData();
        this.cardsList = this.findViewById(R.id.profileCardsList);
        cardsList.setAdapter(new MainWindowCardAdapter(Profile.this, cards));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Profile.this, LinearLayoutManager.VERTICAL, false);
        cardsList.setLayoutManager(linearLayoutManager);
    }

    private List<MainWindowCard> getListData(){
        List<MainWindowCard> list = new ArrayList<MainWindowCard>();
        MainWindowCard personal_data = new MainWindowCard("Личные данные", "profile");
        MainWindowCard form = new MainWindowCard("Анкета", "form");
        MainWindowCard balance = new MainWindowCard("Баланс", "balance");
        MainWindowCard settings = new MainWindowCard("Настройки", "settings");
        list.add(personal_data);
        list.add(form);
        list.add(balance);
        list.add(settings);
        return list;
    }
}