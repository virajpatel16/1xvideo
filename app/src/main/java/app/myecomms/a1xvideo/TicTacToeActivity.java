package app.myecomms.a1xvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class TicTacToeActivity extends AppCompatActivity {
    boolean gameActive = true;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public static int counter = 0;
    private Button buttonClaim;

    String winnerStr;
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (!gameActive) {
            gameReset(view);

            counter = 0;
        }

        // if the tapped image is empty
        if (gameState[tappedImage] == 2) {
            counter++;

            if (counter == 9) {
                // reset the game
                gameActive = false;
            }

            gameState[tappedImage] = activePlayer;

            img.setTranslationY(-1000f);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.frame);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);

                status.setText("O's Turn - Tap to play");
            } else {
                img.setImageResource(R.drawable.x1);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);

                status.setText("X'Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        int flag = 0;

        if (counter > 4) {
            for (int[] winPosition : winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 2) {
                    flag = 1;



                    gameActive = false;
                    if (gameState[winPosition[0]] == 0) {
                        winnerStr = "X has won";
                    } else {
                        winnerStr = "O has won";
                    }
                    showWinDialog();




                }
            }
            if (counter == 9 && flag == 0) {
                TextView status = findViewById(R.id.status);
                status.setText("Match Draw");
            }
        }
    }

    private void showWinDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.ticdialog);
        dialog.setCancelable(false);


        buttonClaim = dialog.findViewById(R.id.buttonClaim);

        buttonClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                String winnerStr1 = winnerStr;
// Send the additional coins count back to MainActivity
                Intent intent = new Intent();
                intent.putExtra("additional_coins", 100);
                setResult(RESULT_OK, intent);
                finish();
                Toast.makeText(TicTacToeActivity.this, "Claim clicked", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        Arrays.fill(gameState, 2);

        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
    }
}