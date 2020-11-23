package com.example.challenge3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
//import com.example.challenge3.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {

    Button guessBtn;
    int backColor = Color.parseColor("#DAE8FC");
    //private ActivityMainBinding binding;
    // private lateinit var binding: ActivityMainBinding;
    TableLayout tableLayout;
    TextView guess;
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
        guess = (TextView) findViewById(R.id.guessText);
        guessBtn = (Button) findViewById(R.id.guessBtn);

//        for(ResourceBalance b : xmlDoc.balance_info)
//        {
//            // Inflate your row "template" and fill out the fields.
//            TableRow row = (TableRow) LayoutInflater.from(CheckBalanceActivity.this).inflate(R.layout.attrib_row, null);
//            ((TextView)row.findViewById(R.id.attrib_name)).setText(b.NAME);
//            ((TextView)row.findViewById(R.id.attrib_value)).setText(b.VALUE);
//            table.addView(row);
//        }
//        table.requestLayout();     // Not sure if this is needed.

        /////////////////////////////////////////////////////////////////

//        LinearLayout.LayoutParams tableRowParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//
//        /* create a table row */
//        TableRow tableRow = new TableRow(this);
//        tableRow.setLayoutParams(tableRowParams);
//
//        /* create cell element - textview */
//        TextView tv1 = new TextView(this);
//        tv1.setBackgroundColor(backColor);
//        tv1.setText("dynamic textview1");
//
//        /* create cell element - button */
//        TextView tv2 = new TextView(this);
//        tv2.setText("dynamic textview2");
//        tv2.setBackgroundColor(backColor);
//
//        /* create cell element - button */
//        TextView tv3 = new TextView(this);
//        tv3.setText("dynamic textview3");
//        tv3.setBackgroundColor(backColor);
//
//        /* set params for cell elements */
//        TableRow.LayoutParams cellParams = new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT);
//        cellParams.weight = 1;
//        tv1.setLayoutParams(cellParams);
////        cellParams.weight = 2;
////        cellParams.rightMargin = 10;
//        cellParams.weight = 1;
//        tv2.setLayoutParams(cellParams);
//        cellParams.weight = 1;
//        tv3.setLayoutParams(cellParams);
//
//        /* add views to the row */
//        tableRow.addView(tv1);
//        tableRow.addView(tv2);
//        tableRow.addView(tv3);
//
//        /* add the row to the table */
//        tableLayout.addView(tableRow);
    }

    public void createRow(int attempt, String guess, String result){
        LinearLayout.LayoutParams tableRowParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        /* create a table row */
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(tableRowParams);

        /* create cell element - textview */
        TextView tv1 = new TextView(this);
        tv1.setBackgroundColor(backColor);
        tv1.setText("attempt");

        /* create cell element - button */
        TextView tv2 = new TextView(this);
        tv2.setText("guess");
        tv2.setBackgroundColor(backColor);

        /* create cell element - button */
        TextView tv3 = new TextView(this);
        tv3.setText("result");
        tv3.setBackgroundColor(backColor);

        /* set params for cell elements */
        TableRow.LayoutParams cellParams = new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT);
        cellParams.weight = 1;
        tv1.setLayoutParams(cellParams);
//        cellParams.weight = 2;
//        cellParams.rightMargin = 10;
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
