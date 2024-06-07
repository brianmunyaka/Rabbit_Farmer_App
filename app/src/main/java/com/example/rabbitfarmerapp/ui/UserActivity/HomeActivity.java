package com.example.rabbitfarmerapp.ui.UserActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rabbitfarmerapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView productRecyclerView;
    private ProductAdapter productAdapter;
    private List<ProductInfo> productInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        productRecyclerView = findViewById(R.id.productRecyclerView);

        // Use GridLayoutManager to create a 2-column grid
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        productRecyclerView.setLayoutManager(gridLayoutManager);

        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference productReviewsRef = database.getReference("itemdata");

            productReviewsRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    productInfoList = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ProductInfo productInfo = snapshot.getValue(ProductInfo.class);
                        if (productInfo != null) {
                            productInfoList.add(productInfo);
                        }
                    }

                    productAdapter = new ProductAdapter(productInfoList);
                    productRecyclerView.setAdapter(productAdapter);

                    productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(ProductInfo productInfo) {
                            Intent intent = new Intent(HomeActivity.this, DisplayItemObjects.class);
                            intent.putExtra("productInfo", productInfo);
                            startActivity(intent);
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle database error
                    Log.e("HomeActivity", "Database error: " + error.getMessage());
                }
            });
        } catch (Exception e) {
            // Handle any exceptions
            Log.e("HomeActivity", "Exception: " + e.getMessage());
            Toast.makeText(this, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}
