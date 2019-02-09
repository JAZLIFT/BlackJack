package com.example.blackjack;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView[] playerCardsImageViews = new ImageView[5];
    int[] playerScore = new int[5];
    int playerNumber = 0;

    private int[] cardImages = {
            R.drawable.two_s,   R.drawable.two_d,   R.drawable.two_c,   R.drawable.two_h,
            R.drawable.three_s, R.drawable.three_d, R.drawable.three_c, R.drawable.two_h,
            R.drawable.four_s,  R.drawable.four_d,  R.drawable.four_c,  R.drawable.four_h,
            R.drawable.five_s,  R.drawable.five_d,  R.drawable. five_c, R.drawable.five_h,
            R.drawable.six_s,   R.drawable.six_d,   R.drawable.six_c,   R.drawable.six_h,
            R.drawable.seven_s, R.drawable.seven_d, R.drawable.seven_c, R.drawable.seven_h,
            R.drawable.eight_s, R.drawable.eight_d, R.drawable.eight_c, R.drawable.eight_h,
            R.drawable.nine_s,  R.drawable.nine_d,  R.drawable.nine_c,  R.drawable.nine_h,
            R.drawable.ten_s,   R.drawable.ten_d,   R.drawable.ten_c,   R.drawable.ten_h,
            R.drawable.ace_s,   R.drawable.ace_d,   R.drawable.ace_c,   R.drawable.ace_h,
            R.drawable.jack_s,  R.drawable.jack_d,  R.drawable.jack_c,  R.drawable.jack_h,
            R.drawable.queen_s, R.drawable.queen_d, R.drawable.queen_c, R.drawable.queen_h,
            R.drawable.king_s,  R.drawable.king_d,  R.drawable.king_c,  R.drawable.king_h
    };

    public void addPlayerCard(Integer card){
        for (int i = 0; i < playerScore.length; i++){
            if (playerScore[i] < 0){
                playerScore[i] = card;
                Log.i(String.valueOf(i),card.toString());
                break;
            }
        }
    }
    public void updatePlayerScore(){
        playerNumber = 0;
        for (int i = 0; i < playerScore.length;i++){
            if (playerScore[i] >= 0 && playerScore[i] <= 3){
                playerNumber += 2;
            }
            else if (playerScore[i] >= 4 && playerScore[i] <= 7){
                playerNumber += 3;
            }
            else if (playerScore[i] >= 8 && playerScore[i] <= 11){
                playerNumber += 4;
            }
            else if (playerScore[i] >= 12 && playerScore[i] <= 15){
                playerNumber += 5;
            }
            else if (playerScore[i] >= 16 && playerScore[i] <= 19){
                playerNumber += 6;
            }
            else if (playerScore[i] >= 20 && playerScore[i] <= 23){
                playerNumber += 7;
            }
            else if (playerScore[i] >= 24 && playerScore[i] <= 27){
                playerNumber += 8;
            }
            else if (playerScore[i] >= 28 && playerScore[i] <= 33){
                playerNumber += 9;
            }
            else if (playerScore[i] >= 36 && playerScore[i] <= 39){
                playerNumber += 11;
                if (playerNumber > 21){
                    playerNumber -= 10;
                }
            } else if (playerScore[i] > -1) {
                playerNumber += 10;
            }
        }
        TextView playerScoreTextView = findViewById(R.id.playerScoreTextView);
        if (playerNumber == 21){
            playerScoreTextView.setText("BLACKJACK!!");
        } else if (playerNumber > 21) {
            playerScoreTextView.setText("BUST!");
        } else{

            playerScoreTextView.setText(String.valueOf(playerNumber));
        }
    }

    public void showPlayerCard(){

        for (int i = 0; i < playerScore.length;i++){

            if (playerScore[i] < 0){
                playerCardsImageViews[i].setVisibility(View.INVISIBLE);
            } else {
                playerCardsImageViews[i].setVisibility(View.VISIBLE);
                playerCardsImageViews[i].setImageResource(cardImages[playerScore[i]]);
            }
        }
    }

    public void hitMe(View view){

        Random random = new Random();
        int card;
        card = random.nextInt(52);
        while (card == playerScore[0] || card == playerScore[1] || card == playerScore[2] || card == playerScore[3] || card == playerScore[4] ){
            Log.i("DUPLICATE FOUND","REROLLING");
            card = random.nextInt(52);
        }
        addPlayerCard(card);
        showPlayerCard();
        updatePlayerScore();

    }

    public void newHand(View view){
        for (int i = 0; i < playerScore.length; i++){
            playerScore[i] = -1;
            playerCardsImageViews[i].setVisibility(View.INVISIBLE);
        }
        playerNumber = 0;
        TextView playerScoreTextView = findViewById(R.id.playerScoreTextView);
        playerScoreTextView.setText(String.valueOf(playerNumber));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerCardsImageViews[0]=findViewById(R.id.playerCardImageView1);
        playerCardsImageViews[1]=findViewById(R.id.playerCardImageView2);
        playerCardsImageViews[2]=findViewById(R.id.playerCardImageView3);
        playerCardsImageViews[3]=findViewById(R.id.playerCardImageView4);
        playerCardsImageViews[4]=findViewById(R.id.playerCardImageView5);

        for (int i = 0; i < playerScore.length; i++){
            playerScore[i] = -1;
            playerCardsImageViews[i].setVisibility(View.INVISIBLE);
        }


    }
}
