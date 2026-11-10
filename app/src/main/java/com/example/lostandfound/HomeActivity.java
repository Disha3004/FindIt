package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private Button btnBrowseItems;
    private Button btnReportLost;
    private Button btnReportFound;
    private Button btnMyReports;
    private Button btnProfile;
    private Button btnLogout;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        // Check if user is logged in; if not, redirect to Login
        if (mAuth.getCurrentUser() == null) {
            redirectToLogin();
            return;
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize buttons
        btnBrowseItems = findViewById(R.id.btnBrowseItems);
        btnReportLost = findViewById(R.id.btnReportLost);
        btnReportFound = findViewById(R.id.btnReportFound);
        btnMyReports = findViewById(R.id.btnMyReports);
        btnProfile = findViewById(R.id.btnProfile);
        btnLogout = findViewById(R.id.btnLogout);

        // Set listeners for temporary button functionality
        btnBrowseItems.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "Browse Items coming soon!", Toast.LENGTH_SHORT).show()
        );

        btnReportLost.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "Report Lost Item coming soon!", Toast.LENGTH_SHORT).show()
        );

        btnReportFound.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "Report Found Item coming soon!", Toast.LENGTH_SHORT).show()
        );

        btnMyReports.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "My Reports coming soon!", Toast.LENGTH_SHORT).show()
        );

        btnProfile.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "My Profile coming soon!", Toast.LENGTH_SHORT).show()
        );

        // Logout listener
        btnLogout.setOnClickListener(v -> logoutUser());
    }

    private void logoutUser() {
        mAuth.signOut();
        Toast.makeText(HomeActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        redirectToLogin();
    }

    private void redirectToLogin() {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
