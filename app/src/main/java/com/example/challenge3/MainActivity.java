package com.example.challenge3;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.example.challenge3.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    Game game;
    AlertDialog.Builder builder;
    private ActivityMainBinding binding;
    TextView guess;

    int attempt = 0;
    String attemptStr;
    String secretStr;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        secretStr = getIntent().getStringExtra("extra_secretNumber");
        game = new Game(secretStr);
    }

    public void newGameOnClick(View view) {
        Intent intent = new Intent(this, NewGame.class);
        startActivity(intent);
    }

    public void guessBtnClick(View view){
        //String secret = "1234";
        guess = binding.guessText;
        String guessStr = guess.getText().toString();
        String regex = "[0-9]+";

        if(guessStr.length() == 0){
            alertView("Enter a 4 digits number!");

        } else if (!guessStr.matches(regex)){
            alertView("The input contains non-digit letters!");

            } else if(guessStr.length() != 4) {
            alertView("Your number must be 4 digits!");

            } else if( !game.hasDistinctDigits(Integer.valueOf(guessStr))){
            alertView("Enter a 4 digits number with distinct digits!");

            } else {
                if (secretStr == null){
                    alertView("You should define a secret number by clicking on New Game!");
                } else {
                    addRow(guessStr);
                }
            }

            guess.setText("");
            hideKeyboard();
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

    public void addRow(String guessStr){
        int backColor = Color.parseColor("#DAE8FC");

        LinearLayout.LayoutParams tableRowParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        attempt++;
        attemptStr = String.valueOf(attempt);
        result = game.spCalculation(guessStr);
        if (result.compareTo("4S0P") == 0) {
            alertView("Congratulation!");
            result = "You Win!";
        }

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
        binding.guessTbl.addView(tableRow);



    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(guess.getWindowToken(), 0);
    }
}
