package com.example.challenge3

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge3.databinding.ActivityNewGameBinding
import java.util.*
import kotlin.math.ceil
import kotlin.math.log10
import kotlin.math.pow

class NewGame : AppCompatActivity() {
    var builder: AlertDialog.Builder? = null
    private var binding: ActivityNewGameBinding? = null
    private var secretText: TextView? = null
    private var secretNumber: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)
        binding = ActivityNewGameBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)
    }

    fun customSecretOnClick(view: View?) {
        secretText = binding!!.secretText
        secretNumber = (secretText as EditText).text.toString()
        if (secretNumber!!.isEmpty()) {
            alertView("Enter a 4 digits number!")
        } else if (!isInteger(secretNumber)) {
            alertView("The input contains non-digit letters!")
        } else if (secretNumber!!.length != 4) {
            alertView("Your number must be 4 digits!")
        } else if (!hasDistinctDigits(Integer.valueOf(secretNumber))) {
            alertView("Enter a 4 digits number with distinct digits!")
        } else {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("extra_secretNumber", secretNumber)
            startActivity(intent)
        }
        hideKeyboard()
        (secretText as EditText).setText("")
    }

    fun randomSecretOnClick(view: View?) {
        var random = 1111
        while (!hasDistinctDigits(random)) {
            random = 1000 + Random().nextInt(9000) //9999
            secretNumber = random.toString()
            Log.v("Random Number", secretNumber!!)
        }
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("extra_secretNumber", secretNumber)
        startActivity(intent)
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

    private fun hasDistinctDigits(number: Int): Boolean {
        var numMask = 0
        val numDigits = ceil(log10(number + 1.toDouble())).toInt()
        for (digitIdx in 0 until numDigits) {
            val curDigit = (number / 10.0.pow(digitIdx.toDouble())).toInt() % 10
            val digitMask = 2.0.pow(curDigit.toDouble()).toInt()
            if (numMask and digitMask > 0) return false
            numMask = numMask or digitMask
        }
        return true
    }

    private fun hideKeyboard() {
        val imm = getSystemService(
                INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(secretText!!.windowToken, 0)
    }
}

fun isInteger(str: String?) = str?.toIntOrNull()?.let { true } ?: false
