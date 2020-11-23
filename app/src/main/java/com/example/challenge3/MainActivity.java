package com.example.challenge3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
//import com.example.challenge3.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {

    Button guessBtn;
    Button newGameBtn;
    //String secret = secretNumber;
    int backColor = Color.parseColor("#DAE8FC");
    //private ActivityMainBinding binding;
    // private lateinit var binding: ActivityMainBinding;
    TableLayout tableLayout;
    TextView guess;
    int attempt =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        binding = ActivityMainBinding.inflate(layoutInflater);
//        setContentView(binding.root);root
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        //ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater(), container, attachToContainer);
// get the root view
        //View view = binding.getRoot();
        tableLayout = (TableLayout) findViewById(R.id.guessTbl);
    }

    public void newGameOnClick(View view) {
        newGameBtn = (Button) findViewById(R.id.newGameBtn);
        Intent intent = new Intent(this, NewGame.class);
        startActivity(intent);
    }


    private static boolean hasDistinctDigits(int number) {
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

    public void guessBtnClick(View view){
        String secret = "1234";
        //String secret = secretNumber;
        int p = 0;
        int s = 0;
        guessBtn = (Button) findViewById(R.id.guessBtn);
        guess = (TextView) findViewById(R.id.guessText);
        String guessStr = guess.getText().toString();
        //char[] guessArr = new char[guessStr.toCharArray().length];
//        char[] guessArr = guessStr.toCharArray();
//        for (int i = 0; i < 4; i++) {
//            char secret_ch = secret.charAt(i);
//            char guess_ch = guessStr.charAt(i);
//            if (secret_ch == guess_ch) {
//                s++;
//            }
//        }
//
//        for(int i=0; i< 4; i++){
//            if(secret.contains(String.valueOf(guessArr[i]))){
//                p++;
//            }
//        }
//
//        String result = s+"S"+p+"P";

        hideKeyboard();

        if(guessStr.length() == 0){
            Toast.makeText(getApplicationContext(),
                    "Enter a 4 digits number!", Toast.LENGTH_LONG).show();
        }
        if(guessStr.length() != 4) {
            Toast.makeText(getApplicationContext(),
                    "Your number must be 4 digits!", Toast.LENGTH_LONG).show();
            guess.setText("");
        } else if( !hasDistinctDigits(Integer.valueOf(guessStr))){
            Toast.makeText(getApplicationContext(),
                    "Enter a 4 digits number with distinct digits!", Toast.LENGTH_LONG).show();
            guess.setText("");
        } else {
            LinearLayout.LayoutParams tableRowParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            attempt++;
            String attemptStr = String.valueOf(attempt);

            char[] guessArr = guessStr.toCharArray();
            for (int i = 0; i < 4; i++) {
                char secret_ch = secret.charAt(i);
                char guess_ch = guessStr.charAt(i);
                if (secret_ch == guess_ch) {
                    s++;
                }
            }

            for(int i=0; i< 4; i++){
                if(secret.contains(String.valueOf(guessArr[i]))){
                    p++;
                }
            }

            String result = s+"S"+p+"P";

            /* create a table row */
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(tableRowParams);

            /* create cell element - textview */
            TextView tv1 = new TextView(this);
            tv1.setBackgroundColor(backColor);
            tv1.setText(attemptStr);

            /* create cell element - button */
            TextView tv2 = new TextView(this);
            tv2.setText(guessStr);
            tv2.setBackgroundColor(backColor);

            /* create cell element - button */
            TextView tv3 = new TextView(this);
            tv3.setText(result);
            tv3.setBackgroundColor(backColor);

            /* set params for cell elements */
            TableRow.LayoutParams cellParams = new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT);
            cellParams.weight = 1;
            tv1.setLayoutParams(cellParams);
            cellParams.weight = 1;
            tv2.setLayoutParams(cellParams);
            cellParams.weight = 1;
            tv3.setLayoutParams(cellParams);

            /* add views to the row */
            tableRow.addView(tv1);
            tableRow.addView(tv2);
            tableRow.addView(tv3);

            /* add the row to the table */
            tableLayout.addView(tableRow);
        }

    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(guess.getWindowToken(), 0);
    }
}
