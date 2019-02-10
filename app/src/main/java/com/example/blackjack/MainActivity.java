package com.example.blackjack;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button playAgainButton;
    Button hitButton;
    Button stayButton;

    int[] playerScore = new int[5];
    int playerNumber = 0;
    boolean playerTurn = true;

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

    ImageView[] playerCardsImageViews = new ImageView[5];
    private int[] playersCardsIDs = {
            R.id.playerCardImageView1,
            R.id.playerCardImageView2,
            R.id.playerCardImageView3,
            R.id.playerCardImageView4,
            R.id.playerCardImageView5
    };

    int[] dealerScore = new int[5];
    int dealerNumber = 0;

    ImageView[] dealersCardsImageViews = new ImageView[5];
    private int[] dealersCardsIDs = {
            R.id.dealerCardImageView0,
            R.id.dealerCardImageView1,
            R.id.dealerCardImageView2,
            R.id.dealerCardImageView3,
            R.id.dealerCardImageView4
    };

    public static boolean isDuplicate(int[] array, int value){
        for (int i = 0; i < array.length; i ++){
            if (array[i] == value){
                return true;
            }
        }
        return false;
    }

    public void resetDealersHand(){
        for (int i = 0; i < dealerScore.length; i++){
            dealerScore[i] = -1;
            dealersCardsImageViews[i].setVisibility(View.INVISIBLE);
        }
        dealerNumber = 0;
        for (int i = 0; i < 2; i++){
            addDealerCard();
        }
        showDealerCards();
    }
    public void showDealerCards(){
        for (int i = 0; i < dealerScore.length; i++)
        {
            if (dealerScore[i] < 0){
                dealersCardsImageViews[i].setVisibility(View.INVISIBLE);
            } else {
                dealersCardsImageViews[i].setVisibility(View.VISIBLE);
                dealersCardsImageViews[i].setImageResource(cardImages[dealerScore[i]]);
            }
        }
    }

    public void addDealerCard(){
        Random random = new Random();
        int card;
        card = random.nextInt(52);
        while (isDuplicate(playerScore,card) || isDuplicate(dealerScore,card)){
            card = random.nextInt(52);
        }
        for (int i = 0; i < dealerScore.length; i++){
            if (dealerScore[i] < 0){
                dealerScore[i] = card;
                break;
            }
        }
        showDealerCards();
        updateDealerScore();
    }

    public void updateDealerScore(){
        dealerNumber = 0;
        for (int i = 0; i < dealerScore.length;i++){
            if (dealerScore[i] >= 0 && dealerScore[i] <= 3){
                dealerNumber += 2;
            }
            else if (dealerScore[i] >= 4 && dealerScore[i] <= 7){
                dealerNumber += 3;
            }
            else if (dealerScore[i] >= 8 && dealerScore[i] <= 11){
                dealerNumber += 4;
            }
            else if (dealerScore[i] >= 12 && dealerScore[i] <= 15){
                dealerNumber += 5;
            }
            else if (dealerScore[i] >= 16 && dealerScore[i] <= 19){
                dealerNumber += 6;
            }
            else if (dealerScore[i] >= 20 && dealerScore[i] <= 23){
                dealerNumber += 7;
            }
            else if (dealerScore[i] >= 24 && dealerScore[i] <= 27){
                dealerNumber += 8;
            }
            else if (dealerScore[i] >= 28 && dealerScore[i] <= 33){
                dealerNumber += 9;
            }
            else if (dealerScore[i] >= 36 && dealerScore[i] <= 39){
                dealerNumber += 11;
            } else if (dealerScore[i] > -1) {
                dealerNumber += 10;
            }
        }
        for (int i = 0; i < dealerScore.length;i++){
            if (dealerScore[i] >= 36 && dealerScore[i] <= 39){
                if (dealerNumber > 21){
                    dealerNumber -= 10;
                }
            }
        }
    }


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
            } else if (playerScore[i] > -1) {
                playerNumber += 10;
            }
        }
        for (int i = 0; i < playerScore.length;i++){
            if (playerScore[i] >= 36 && playerScore[i] <= 39){
                if (playerNumber > 21){
                    playerNumber -= 10;
                }
            }
        }
        TextView playerScoreTextView = findViewById(R.id.playerScoreTextView);
        if (playerNumber == 21){
            playerScoreTextView.setText("BLACKJACK!!");
            roundOver(true);
        } else if (playerNumber > 21) {
            playerScoreTextView.setText("BUST!");
            roundOver(false);
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
        while (isDuplicate(playerScore,card) || isDuplicate(dealerScore,card)){
            Log.i("DUPLICATE FOUND","REROLLING");
            card = random.nextInt(52);
        }
        addPlayerCard(card);
        showPlayerCard();
        updatePlayerScore();

    }

    public  void roundOver(Boolean won){
        if (won){
            playAgainButton.setText("YOU WON! \n Play Again?");
        } else {
            playAgainButton.setText("YOU LOST! \n Play Again?");
        }
        hitButton.setEnabled(false);
        stayButton.setEnabled(false);
        playAgainButton.setVisibility(View.VISIBLE);
    }

    public void stayClicked(View view){
        while (dealerNumber < 17){
            addDealerCard();
        }
        if (dealerNumber > 21 || dealerNumber < playerNumber){
            Toast.makeText(this, "You Won!", Toast.LENGTH_SHORT).show();
            roundOver(true);
        } else
            {
                Toast.makeText(this,"You Lost!",Toast.LENGTH_SHORT).show();
                roundOver(false);
            }
    }

    public void newHand(View view){
        playAgainButton.setVisibility(View.INVISIBLE);
        hitButton.setEnabled(true);
        stayButton.setEnabled(true);

        for (int i = 0; i < playerScore.length; i++){
            playerScore[i] = -1;
            playerCardsImageViews[i].setVisibility(View.INVISIBLE);
        }
        playerNumber = 0;
        TextView playerScoreTextView = findViewById(R.id.playerScoreTextView);
        playerScoreTextView.setText(String.valueOf(playerNumber));

        for (int i = 0; i <2; i++){
            hitMe(playerScoreTextView);
        }
        resetDealersHand();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hitButton = findViewById(R.id.hitMeButton);
        stayButton = findViewById(R.id.stayButton);

        //Make play again button invisible
        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);
        //Setting up hands
        for (int i = 0; i < 5; i ++){
            playerCardsImageViews[i] = findViewById(playersCardsIDs[i]);
            dealersCardsImageViews[i] = findViewById(dealersCardsIDs[i]);
        }


        for (int i = 0; i < playerScore.length; i++){
            playerScore[i] = -1;
            playerCardsImageViews[i].setVisibility(View.INVISIBLE);
        }

        for (int i = 0; i < dealerScore.length; i++){
            dealerScore[i] = -1;
            dealersCardsImageViews[i].setVisibility(View.INVISIBLE);
        }

        resetDealersHand();
        newHand(playerCardsImageViews[1]);
    }
}
