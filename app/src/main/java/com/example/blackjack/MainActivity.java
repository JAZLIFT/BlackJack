package com.example.blackjack;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView[] playerCardsImageViews = new ImageView[5];
    int[] playerScore = new int[5];

    public void addPlayerCard(Integer card){
        for (int i = 0; i < playerScore.length; i++){
            if (playerScore[i] < 0){
                playerScore[i] = card;
                Log.i(String.valueOf(i),card.toString());
                break;
            }
        }
    }

    public void showPlayerCard(){

        for (int i = 0; i < playerScore.length;i++){

            if (playerScore[i] < 0){
                playerCardsImageViews[i].setVisibility(View.INVISIBLE);
            } else {
                playerCardsImageViews[i].setVisibility(View.VISIBLE);
            }
            switch (playerScore[i]){
                case 0:
                    playerCardsImageViews[i].setImageResource(R.drawable.two_s);
                    break;
                case 1:
                    playerCardsImageViews[i].setImageResource(R.drawable.two_d);
                    break;
                case 2:
                    playerCardsImageViews[i].setImageResource(R.drawable.two_c);
                    break;
                case 3:
                    playerCardsImageViews[i].setImageResource(R.drawable.two_h);
                    break;
                case 4:
                    playerCardsImageViews[i].setImageResource(R.drawable.three_s);
                    break;
                case 5:
                    playerCardsImageViews[i].setImageResource(R.drawable.three_d);
                    break;
                case 6:
                    playerCardsImageViews[i].setImageResource(R.drawable.three_c);
                    break;
                case 7:
                    playerCardsImageViews[i].setImageResource(R.drawable.three_h);
                    break;
                case 8:
                    playerCardsImageViews[i].setImageResource(R.drawable.four_s);
                    break;
                case 9:
                    playerCardsImageViews[i].setImageResource(R.drawable.four_d);
                    break;
                case 10:
                    playerCardsImageViews[i].setImageResource(R.drawable.four_c);
                    break;
                case 11:
                    playerCardsImageViews[i].setImageResource(R.drawable.four_h);
                    break;
                case 12:
                    playerCardsImageViews[i].setImageResource(R.drawable.five_s);
                    break;
                case 13:
                    playerCardsImageViews[i].setImageResource(R.drawable.five_d);
                    break;
                case 14:
                    playerCardsImageViews[i].setImageResource(R.drawable.five_c);
                    break;
                case 15:
                    playerCardsImageViews[i].setImageResource(R.drawable.five_h);
                    break;
                case 16:
                    playerCardsImageViews[i].setImageResource(R.drawable.six_s);
                    break;
                case 17:
                    playerCardsImageViews[i].setImageResource(R.drawable.six_d);
                    break;
                case 18:
                    playerCardsImageViews[i].setImageResource(R.drawable.six_c);
                    break;
                case 19:
                    playerCardsImageViews[i].setImageResource(R.drawable.six_h);
                    break;
                case 20:
                    playerCardsImageViews[i].setImageResource(R.drawable.seven_s);
                    break;
                case 21:
                    playerCardsImageViews[i].setImageResource(R.drawable.seven_d);
                    break;
                case 22:
                    playerCardsImageViews[i].setImageResource(R.drawable.seven_c);
                    break;
                case 23:
                    playerCardsImageViews[i].setImageResource(R.drawable.seven_h);
                    break;
                case 24:
                    playerCardsImageViews[i].setImageResource(R.drawable.eight_s);
                    break;
                case 25:
                    playerCardsImageViews[i].setImageResource(R.drawable.eight_d);
                    break;
                case 26:
                    playerCardsImageViews[i].setImageResource(R.drawable.eight_c);
                    break;
                case 27:
                    playerCardsImageViews[i].setImageResource(R.drawable.eight_h);
                    break;
                case 28:
                    playerCardsImageViews[i].setImageResource(R.drawable.nine_s);
                    break;
                case 29:
                    playerCardsImageViews[i].setImageResource(R.drawable.nine_d);
                    break;
                case 30:
                    playerCardsImageViews[i].setImageResource(R.drawable.nine_c);
                    break;
                case 31:
                    playerCardsImageViews[i].setImageResource(R.drawable.nine_h);
                    break;
                case 32:
                    playerCardsImageViews[i].setImageResource(R.drawable.ten_s);
                    break;
                case 33:
                    playerCardsImageViews[i].setImageResource(R.drawable.ten_d);
                    break;
                case 34:
                    playerCardsImageViews[i].setImageResource(R.drawable.ten_c);
                    break;
                case 35:
                    playerCardsImageViews[i].setImageResource(R.drawable.ten_h);
                    break;
                case 36:
                    playerCardsImageViews[i].setImageResource(R.drawable.ace_s);
                    break;
                case 37:
                    playerCardsImageViews[i].setImageResource(R.drawable.ace_d);
                    break;
                case 38:
                    playerCardsImageViews[i].setImageResource(R.drawable.ace_c);
                    break;
                case 39:
                    playerCardsImageViews[i].setImageResource(R.drawable.ace_h);
                    break;
                case 40:
                    playerCardsImageViews[i].setImageResource(R.drawable.jack_s);
                    break;
                case 41:
                    playerCardsImageViews[i].setImageResource(R.drawable.jack_d);
                    break;
                case 42:
                    playerCardsImageViews[i].setImageResource(R.drawable.jack_c);
                    break;
                case 43:
                    playerCardsImageViews[i].setImageResource(R.drawable.jack_h);
                    break;
                case 44:
                    playerCardsImageViews[i].setImageResource(R.drawable.queen_s);
                    break;
                case 45:
                    playerCardsImageViews[i].setImageResource(R.drawable.queen_d);
                    break;
                case 46:
                    playerCardsImageViews[i].setImageResource(R.drawable.queen_c);
                    break;
                case 47:
                    playerCardsImageViews[i].setImageResource(R.drawable.queen_h);
                    break;
                case 48:
                    playerCardsImageViews[i].setImageResource(R.drawable.king_s);
                    break;
                case 49:
                    playerCardsImageViews[i].setImageResource(R.drawable.king_d);
                    break;
                case 50:
                    playerCardsImageViews[i].setImageResource(R.drawable.king_c);
                    break;
                case 51:
                    playerCardsImageViews[i].setImageResource(R.drawable.king_h);
                    break;
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

    }

    public void newHand(View view){
        for (int i = 0; i < playerScore.length; i++){
            playerScore[i] = -1;
            playerCardsImageViews[i].setVisibility(View.INVISIBLE);
        }
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
