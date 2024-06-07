package com.example.rabbitfarmerapp.ui.UserActivity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rabbitfarmerapp.R;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rabbitfarmerapp.ui.UserActivity.CartAdapter;
import com.example.rabbitfarmerapp.R;
import com.example.rabbitfarmerapp.ui.UserActivity.ProductInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private CartAdapter cartAdapter;
    private List<ProductInfo> productInfoList;
    private RecyclerView cartRecyclerView;

    private TextView TVtotalcost, TVcostDesc, TvcartDesc;
    private Button btnMpesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.RVcartRecyclerView);
        TVtotalcost = findViewById(R.id.TVtotalcost);
        TVcostDesc = findViewById(R.id.TVcostDesc);
        TvcartDesc = findViewById(R.id.TVcartDesc);
        btnMpesa = findViewById(R.id.btnMpesa);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        cartRecyclerView.setLayoutManager(gridLayoutManager);

        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference cartRef = database.getReference("cartdata");

            cartRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    productInfoList = new ArrayList<>();

                    double totalCost = 0.0;

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ProductInfo productInfo = dataSnapshot.getValue(ProductInfo.class);
                        if (productInfo != null) {
                            productInfoList.add(productInfo);

                            totalCost += Double.parseDouble(productInfo.getProductPrice());
                        }
                    }

                    cartAdapter = new CartAdapter(productInfoList);
                    cartRecyclerView.setAdapter(cartAdapter);

                    //Update total cost

                    TVtotalcost.setText(String.format("%.2f", totalCost));
                    TVtotalcost.setVisibility(View.VISIBLE);
                    TVcostDesc.setVisibility(View.VISIBLE);
                    TvcartDesc.setVisibility(View.VISIBLE);
                    btnMpesa.setVisibility(View.VISIBLE);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("error", String.valueOf(error));
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show();
        }
    }
}