package com.example.blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Intent intent = getIntent();
        int totalGames = intent.getIntExtra("totalGames",0);
        int totalWins = intent.getIntExtra("totalWins", 0);
        int totalLosses = intent.getIntExtra("totalLosses",0);
        Toast.makeText(this, "TOTAL GAMES " + String.valueOf(totalGames),Toast.LENGTH_LONG).show();
        ArrayList<String> statItems = new ArrayList<String>();

        statItems.add("Total Games : " + Integer.toString(totalGames));
        statItems.add("Total Wins : " + Integer.toString(totalWins));
        statItems.add("Total Losses : " + Integer.toString(totalLosses));


        ListView listView = findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, statItems);

        listView.setAdapter(arrayAdapter);
    }

}
