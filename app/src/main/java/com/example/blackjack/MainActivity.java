package com.example.blackjack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Declare Stats
    int totalGames;
    int totalWins;
    int totalLosses;
    int totalDraws;

    //Shared Preferences
    SharedPreferences sharedPreferences;

    Button playAgainButton;
    Button hitButton;
    Button stayButton;

    int[] playerScore = new int[6];
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

    ImageView[] playerCardsImageViews = new ImageView[6];
    private int[] playersCardsIDs = {
            R.id.playerCardImageView1,
            R.id.playerCardImageView2,
            R.id.playerCardImageView3,
            R.id.playerCardImageView4,
            R.id.playerCardImageView5,
            R.id.playerCardImageView6
    };

    int[] dealerScore = new int[6];
    int dealerNumber = 0;

    ImageView[] dealersCardsImageViews = new ImageView[6];
    private int[] dealersCardsIDs = {
            R.id.dealerCardImageView0,
            R.id.dealerCardImageView1,
            R.id.dealerCardImageView2,
            R.id.dealerCardImageView3,
            R.id.dealerCardImageView4,
            R.id.dealerCardImageView5
    };

    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // Menu Select


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.stats:
                Intent intent = new Intent(getApplicationContext(), StatsActivity.class);
                intent.putExtra("totalGames", totalGames);
                intent.putExtra("totalWins", totalWins);
                intent.putExtra("totalLosses", totalLosses);
                startActivity(intent);
                break;

            case R.id.resetStats:
                resetStats();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    // Check to make sure cards have not been drawn twice
    public static boolean isDuplicate(int[] array, int value){
        for (int i = 0; i < array.length; i ++){
            if (array[i] == value){
                return true;
            }
        }
        return false;
    }

    //
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
        dealersCardsImageViews[1].setImageResource(R.drawable.yellow_back);
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
            else if (playerScore[i] >= 28 && playerScore[i] <= 31){
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
    public void stayClicked(View view){
        showDealerCards();
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

    public  void roundOver(Boolean won){
        if (won)
        {
            playAgainButton.setText("YOU WON! \n Play Again?");
            updateStats("WIN");
        }
        else
        {
            playAgainButton.setText("YOU LOST! \n Play Again?");
            updateStats("LOSS");
        }
        hitButton.setEnabled(false);
        stayButton.setEnabled(false);
        playAgainButton.setVisibility(View.VISIBLE);
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

    public void updateStats(String result){
        totalGames ++;
        Log.i("TOTAL GAMES", String.valueOf(totalGames));
        sharedPreferences.edit().putInt("totalGames",totalGames).apply();

        if (result.equals("WIN"))
        {
            totalWins++;
            sharedPreferences.edit().putInt("totalWins",totalWins).apply();

        }
        else if (result.equals("LOSS"))
        {
            totalLosses++;
            sharedPreferences.edit().putInt("totalLosses",totalLosses).apply();

        }
        else if (result.equals("DRAW"))
        {

        }
        else
        {
            Toast.makeText(this,"Problem Saving Stats!",Toast.LENGTH_SHORT).show();
        }
    }

    public void resetStats(){
        totalDraws = 0;
        totalWins = 0;
        totalLosses = 0;
        totalGames = 0;
        sharedPreferences.edit().putInt("totalGames",totalGames).apply();
        sharedPreferences.edit().putInt("totalWins",totalWins).apply();
        sharedPreferences.edit().putInt("totalLosses",totalLosses).apply();
        sharedPreferences.edit().putInt("totalDraws",totalDraws).apply();
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
        for (int i = 0; i < playerCardsImageViews.length; i ++){
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

        // SharedPreferences Saving score
        sharedPreferences = this.getSharedPreferences("com.example.blackjack", Context.MODE_PRIVATE);

        totalGames =sharedPreferences.getInt("totalGames",0);
        totalWins =sharedPreferences.getInt("totalWins", 0);
        totalLosses = sharedPreferences.getInt("totalLosses", 0);
        Toast.makeText(this,"SAVE LOADED",Toast.LENGTH_SHORT).show();
        Log.i("TOTAL GAMES", Integer.toString(totalGames));

    }
}
