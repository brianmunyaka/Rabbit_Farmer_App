package com.example.rabbitfarmerapp.ui.AdminTasks;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.rabbitfarmerapp.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ContentActivity extends AppCompatActivity {
    private TextView titleTextView, descriptionTextView;
    private ImageView contentImageView;
    private FirebaseFirestore db;
    private DocumentReference contentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        contentImageView = findViewById(R.id.contentImageView);

        db = FirebaseFirestore.getInstance();
        contentRef = db.collection("content").document("content_id");

        loadContent();

        descriptionTextView.setOnClickListener(v -> {
            showFullDescription();
        });
    }

    private void loadContent() {
        contentRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String title = documentSnapshot.getString("title");
                String description = documentSnapshot.getString("description");
                String imageUrl = documentSnapshot.getString("imageUrl");

                titleTextView.setText(title);
                descriptionTextView.setText(description.substring(0, 100) + "..."); // Display a preview of the description
                Glide.with(this).load(imageUrl).into(contentImageView);
            }
        }).addOnFailureListener(e -> {
            // Handle error
        });
    }

    private void showFullDescription() {
        contentRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String fullDescription = documentSnapshot.getString("description");
                // Display the full description in a new activity or dialog
            }
        }).addOnFailureListener(e -> {
            // Handle error
        });
    }
}
