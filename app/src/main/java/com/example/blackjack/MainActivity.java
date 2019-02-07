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
            }
        }
    }

    public void hitMe(View view){

        Random random = new Random();
        int card;
        card = random.nextInt(8);
        while (card == playerScore[0] || card == playerScore[1] || card == playerScore[2] || card == playerScore[3] || card == playerScore[4] ){
            Log.i("DUPLICATE FOUND","REROLLING");
            card = random.nextInt(8);
        }
        addPlayerCard(card);
        showPlayerCard();

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
