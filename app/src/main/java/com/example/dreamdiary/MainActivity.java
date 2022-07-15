package com.example.dreamdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.dreamdiary.cards.MainWindowCard;
import com.example.dreamdiary.cards.MainWindowCardAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private RecyclerView cardsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.d("MyLog" , ""+currentUser);
        if (currentUser == null){
            Intent intent = new Intent(this, Authorization.class);
            startActivity(intent);
        }
        List<MainWindowCard> cards = getListData();
        this.cardsList = this.findViewById(R.id.cardsList);
        cardsList.setAdapter(new MainWindowCardAdapter(MainActivity.this, cards));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        cardsList.setLayoutManager(linearLayoutManager);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            Intent intent = new Intent(this, Authorization.class);
            startActivity(intent);
        }
    }
    private List<MainWindowCard> getListData(){
        List<MainWindowCard> list = new ArrayList<MainWindowCard>();
        MainWindowCard profile = new MainWindowCard("Мой профиль", "profile");
        MainWindowCard dreams = new MainWindowCard("Мои мечты", "dreams");
        MainWindowCard tasks = new MainWindowCard("Цели", "tasks");
        MainWindowCard blog = new MainWindowCard("Живая лента", "blog");
        MainWindowCard advices = new MainWindowCard("Советы и мотвации", "advices");
        MainWindowCard diary = new MainWindowCard("Дневник чемпиона", "diary");
        list.add(profile);
        list.add(dreams);
        list.add(tasks);
        list.add(blog);
        list.add(advices);
        list.add(diary);
        return list;
    }
}