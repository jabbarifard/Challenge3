package com.example.challenge3

class Game(var secretNumber: String) {
    var result: String? = null
    var s = 0
    var p = 0
    fun hasDistinctDigits(number: Int): Boolean {
        var numMask = 0
        val numDigits = Math.ceil(Math.log10(number + 1.toDouble())).toInt()
        for (digitIdx in 0 until numDigits) {
            val curDigit = (number / Math.pow(10.0, digitIdx.toDouble())).toInt() % 10
            val digitMask = Math.pow(2.0, curDigit.toDouble()).toInt()
            if (numMask and digitMask > 0) return false
            numMask = numMask or digitMask
        }
        return true
    }

    fun spCalculation(guessStr: String): String {
        s = 0
        p = 0
        val guessArr = guessStr.toCharArray()
        for (i in 0..3) {
            val secret_ch = secretNumber[i] //could be this.
            val guess_ch = guessStr[i]
            if (secret_ch == guess_ch) {
                s++
            }
        }
        for (i in 0..3) {
            if (secretNumber.contains(guessArr[i].toString())) {
                p++
            }
        }
        result = s.toString() + "S" + (p - s) + "P"
        return result!!
    }
}
