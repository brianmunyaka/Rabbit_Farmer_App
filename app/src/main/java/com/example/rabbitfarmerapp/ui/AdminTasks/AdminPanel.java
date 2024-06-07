package com.example.rabbitfarmerapp.ui.AdminTasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rabbitfarmerapp.R;

public class AdminPanel extends AppCompatActivity {

    LinearLayout addNewItem;
    LinearLayout addnews;
    LinearLayout addNewUSers;
    LinearLayout updateItems;
    LinearLayout viewOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        addNewItem=findViewById(R.id.LLaddNewItem);
        addnews=findViewById(R.id.LLaddnews);
        addNewUSers=findViewById(R.id.LLaddNewUsers);

        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddNewProduct.class);
                startActivity(intent);
            }
        });
        addNewUSers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),UserRegistrationActivity.class);
                startActivity(intent);
            }
        });


    }

}