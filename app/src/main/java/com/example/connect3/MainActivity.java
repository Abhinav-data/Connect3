package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    boolean f=true;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int [][] winningpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;

    public void dropIn(View view){
        ImageView counter=(ImageView)view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive && f) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().rotation(300).translationYBy(1000).setDuration(500);

            for (int[] winningPos : winningpos) {
                if (gameState[winningPos[0]] == gameState[winningPos[1]] && gameState[winningPos[1]] == gameState[winningPos[2]] && gameState[winningPos[0]] != 2) {
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    gameActive=false;
                    Toast.makeText(this, winner + " has won", Toast.LENGTH_LONG).show();
                    Button playAgain=(Button)findViewById(R.id.playAgain);
                    playAgain.setVisibility(View.VISIBLE);
                }

            }
            for(int i=0;i<gameState.length;i++){
                f=false;
                if(gameState[i]==2){
                    f=true;
                    break;
                }
            }
            if(f==false){
                Toast.makeText(this, "Nobody Won!", Toast.LENGTH_LONG).show();
                Button playAgain=(Button)findViewById(R.id.playAgain);
                playAgain.setVisibility(View.VISIBLE);

            }
        }
    }

    public void playAgain(View view){
        Button playAgain=(Button)findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter=(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activePlayer=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        gameActive=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
