package com.example.challenge3;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.challenge3.databinding.ActivityMainBinding;
import com.example.challenge3.databinding.ActivityNewGameBinding;
import java.util.Random;

public class NewGame extends AppCompatActivity {

    AlertDialog.Builder builder;
    private ActivityNewGameBinding binding;
    TextView secretText;
    String secretNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        binding = ActivityNewGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void customSecretOnClick(View view) {
        secretText = binding.secretText;
        secretNumber = secretText.getText().toString();
        String regex = "[0-9]+";

        if(secretNumber.length() == 0){
            alertView("Enter a 4 digits number!");
            secretText.setText("");

        } else if (!secretNumber.matches(regex)){
            alertView("The input contains non-digit letters!");
            secretText.setText("");

        } else if(secretNumber.length() != 4) {
            alertView("Your number must be 4 digits!");
            secretText.setText("");

        } else if( !hasDistinctDigits(Integer.valueOf(secretNumber))){
            alertView("Enter a 4 digits number with distinct digits!");
            secretText.setText("");

        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("extra_secretNumber", secretNumber);
            startActivity(intent);
        }

        hideKeyboard();
    }

    public void randomSecretOnClick(View view){
        int random = 1111;
        while (!hasDistinctDigits(random)) {
            random = 1000 + new Random().nextInt(9000);//9999
            secretNumber = String.valueOf(random);
            Log.v("Random Number", secretNumber);
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("extra_secretNumber", secretNumber);
        startActivity(intent);
    }

    private void alertView(String message) {
        builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //  Action for 'Ok' Button
                dialog.cancel();
            }
        });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle(message);
        alert.show();
    }

    public boolean hasDistinctDigits(int number) {
        int numMask = 0;
        int numDigits = (int) Math.ceil(Math.log10(number+1));
        for (int digitIdx = 0; digitIdx < numDigits; digitIdx++) {
            int curDigit = (int)(number / Math.pow(10,digitIdx)) % 10;
            int digitMask = (int)Math.pow(2, curDigit);
            if ((numMask & digitMask) > 0) return false;
            numMask = numMask | digitMask;
        }
        return true;
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(secretText.getWindowToken(), 0);
    }
}
