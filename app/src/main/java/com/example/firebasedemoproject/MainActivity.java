package com.example.firebasedemoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = database.getReference("Message");

        databaseReference.setValue("Happy Diwali to you");

        DatabaseReference databaseReference1 = database.getReference("User");

        User user = new User("Srinu","srinu@gmail.com");

        databaseReference1.setValue(user);

        TextView textView = findViewById(R.id.textView);

         databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    if (snapshot.hasChild("name") && snapshot.hasChild("email")){
                        User user1 = snapshot.getValue(User.class);
                        textView.setText("Email : " + user1.getEmail());
                    }else {
                        String message = snapshot.getValue(String.class);
                        textView.setText(message);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, ""+ error, Toast.LENGTH_SHORT).show();

            }
        });
    }
}