package com.example.challenge3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewGame extends AppCompatActivity {

    Button saveSecretBtn;
    Button backBtn;
    TextView secretText;
    String secretNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
    }

    public void saveSecretOnClick(View view) {
        saveSecretBtn = (Button) findViewById(R.id.secretBtn);
        secretText = (TextView) findViewById(R.id.secretText);
        secretNumber = secretText.getText().toString();
    }

    public void backOnClick(View view) {
        backBtn = (Button) findViewById(R.id.backBtn);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}