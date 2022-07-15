package com.example.dreamdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.dreamdiary.cards.DreamCard;
import com.example.dreamdiary.cards.DreamCardAdapter;
import com.example.dreamdiary.cards.MainWindowCard;
import com.example.dreamdiary.cards.MainWindowCardAdapter;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Dreams extends AppCompatActivity {
    private RecyclerView cardsList;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    RecyclerView.Adapter adapter;
    List<DreamCard> list = new ArrayList<DreamCard>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dreams);
        ImageButton add_dream_btn = findViewById(R.id.add_dream_btn);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid()).child("dreams");
        getDataFromDB();
        List<DreamCard> cards = list;
        this.cardsList = this.findViewById(R.id.dreamCardsList);
        adapter = new DreamCardAdapter(Dreams.this, cards);
        cardsList.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Dreams.this, LinearLayoutManager.VERTICAL, false);
        cardsList.setLayoutManager(linearLayoutManager);
        add_dream_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Dreams.this);
                ConstraintLayout cl = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_dream_layout,null);
                builder.setView(cl);
                EditText new_dream = cl.findViewById(R.id.et_new_dream);
                Button add_new_dream_btn = cl.findViewById(R.id.add_new_dream_btn);
                Dialog dialog = builder.create();
                dialog.show();
                add_new_dream_btn.setOnClickListener(view->{

                    myRef.push().setValue(new_dream.getText().toString());
                    dialog.dismiss();
                });
            }
        });
    }
    private void getDataFromDB(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (list.size()>0)list.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    String dreamName = ds.getValue(String.class);
                    list.add(new DreamCard("Я мечтаю "+dreamName, "dream_logo"));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.addValueEventListener(vListener);
    }
}