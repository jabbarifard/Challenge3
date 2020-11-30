package com.example.challenge3

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var game: Game? = null
    var builder: AlertDialog.Builder? = null
    private var binding: ActivityMainBinding? = null
    var guess: TextView? = null
    var attempt = 0
    var attemptStr: String? = null
    var secretStr: String? = null
    var result: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)
        secretStr = intent.getStringExtra("extra_secretNumber")
        game = secretStr?.let { Game(it) }
    }

    fun newGameOnClick(view: View?) {
        val intent = Intent(this, NewGame::class.java)
        startActivity(intent)
    }

    fun guessBtnClick(view: View?) {
        //String secret = "1234";
        guess = binding!!.guessText
        val guessStr = (guess as EditText).text.toString()
        if (guessStr.isEmpty()) {
            alertView("Enter a 4 digits number!")
        } else if (!isInteger(guessStr)) {
            alertView("The input contains non-digit letters!")
        } else if (guessStr.length != 4) {
            alertView("Your number must be 4 digits!")
        } else if (!game!!.hasDistinctDigits(Integer.valueOf(guessStr))) {
            alertView("Enter a 4 digits number with distinct digits!")
        } else {
            if (secretStr == null) {
                alertView("You should define a secret number by clicking on New Game!")
            } else {
                addRow(guessStr)
            }
        }
        (guess as EditText).setText("")
        hideKeyboard()
    }

    private fun alertView(message: String) {
        builder = AlertDialog.Builder(this)
        builder!!.setNegativeButton("Ok") { dialog, id -> //  Action for 'Ok' Button
            dialog.cancel()
        }
        //Creating dialog box
        val alert = builder!!.create()
        //Setting the title manually
        alert.setTitle(message)
        alert.show()
    }

    private fun addRow(guessStr: String?) {
        val backColor = Color.parseColor("#DAE8FC")
        val tableRowParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        attempt++
        attemptStr = attempt.toString()
        result = guessStr?.let { game!!.spCalculation(it) }
        if (result!!.compareTo("4S0P") == 0) {
            alertView("Congratulation!")
            result = "You Win!"
        }

        /* create a table row */
        val tableRow = TableRow(this)
        tableRow.layoutParams = tableRowParams

        /* create cell element - textview */
        val tv1 = TextView(this)
        tv1.setBackgroundColor(backColor)
        tv1.text = attemptStr

        /* create cell element - button */
        val tv2 = TextView(this)
        tv2.text = guessStr
        tv2.setBackgroundColor(backColor)

        /* create cell element - button */
        val tv3 = TextView(this)
        tv3.text = result
        tv3.setBackgroundColor(backColor)

        /* set params for cell elements */
        val cellParams = TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT)
        cellParams.weight = 1f
        tv1.layoutParams = cellParams
        cellParams.weight = 1f
        tv2.layoutParams = cellParams
        cellParams.weight = 1f
        tv3.layoutParams = cellParams

        /* add views to the row */tableRow.addView(tv1)
        tableRow.addView(tv2)
        tableRow.addView(tv3)

        /* add the row to the table */binding!!.guessTbl.addView(tableRow)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(
                INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(guess!!.windowToken, 0)
    }

    private fun isInteger(str: String?) = str?.toIntOrNull()?.let { true } ?: false
}
