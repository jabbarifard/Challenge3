package com.example.challenge3;


public class Game {
    String secretNumber;
    String result;
    int s;
    int p;

    public Game(String secretNumber){
        this.secretNumber = secretNumber;
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

    public String spCalculation(String guessStr){
        s = 0;
        p = 0;
        char[] guessArr = guessStr.toCharArray();

        for (int i = 0; i < 4; i++) {
            char secret_ch = secretNumber.charAt(i);//could be this.
            char guess_ch = guessStr.charAt(i);
            if (secret_ch == guess_ch) {
                s++;
            }
        }

        for(int i=0; i< 4; i++){
            if(secretNumber.contains(String.valueOf(guessArr[i]))){
                p++;
            }
        }

        result = s+"S"+(p-s)+"P";
        return result;
    }



}

