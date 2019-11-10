package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //  if winner is found true else false
    boolean winner = false;

    //  0 = yellow , 1 = red
    int activePlayer = 0 ;

    //  2 means unplayed
    int gameState[]= {2,2,2,2,2,2,2,2,2};

    //  Keeping all the combination of winning positions
    int[][] winningPositions = {{0,1,2} , {3,4,5} , {6,7,8} , {0,3,6} , {1,4,7} , {2,5,8} , {0,4,8} , {2,4,6}};

    public void dropIn(View view){

        //  ImageView of tapped view
        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());

        //  Find which image was tapped
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        //  Check if tapped image is already played
        if(gameState[tappedCounter] == 2 && !winner) {

            gameState[tappedCounter] = activePlayer;

            // Change Views height
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
            } else {
                counter.setImageResource(R.drawable.red);
            }

            activePlayer = (activePlayer + 1) % 2;
            //  Animating the flow of image
            counter.animate().translationYBy(1000f).setDuration(300);

            boolean noMovesLeft = true;

            //  Check if anyone have won
            for(int[] winningPosition : winningPositions)
            {
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[2]] != 2)
                {
                    System.out.println("Winner is: "+(gameState[winningPosition[0]] + 1));
                    int player = gameState[winningPosition[0]] + 1;
                    String won = "Player " + player + " Won";
                    TextView textView = (TextView) findViewById(R.id.winnerName);
                    textView.setText(won);
                    textView.setAlpha(1f);

                    Button button = (Button) findViewById(R.id.button);
                    button.setAlpha(1f);

                    winner = true;
                }

                for(int position : winningPosition){
                    if(gameState[position] == 2)
                        noMovesLeft = false;
                }
            }

            if(noMovesLeft && !winner)
            {
                String won = "It's a draw";
                TextView textView = (TextView) findViewById(R.id.winnerName);
                textView.setText(won);
                textView.setAlpha(1f);

                Button button = (Button) findViewById(R.id.button);
                button.setAlpha(1f);

                winner = true;
            }
        }

    }

    public void playAgain(View view){
        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.winnerName);
        if(winner){
            button.setAlpha(0f);
            textView.setAlpha(0f);

//            ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
            ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);

            for(int i=0;i<gameState.length;i++){
                gameState[i] = 2;
            }
            //  Giving default values to activePlayer and winner
            activePlayer = 0;
            winner = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
