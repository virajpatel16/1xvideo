package app.myecomms.a1xvideo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import app.myecomms.a1xvideo.interfase.CoinClaimListener;
import app.myecomms.a1xvideo.modals.CoinDialog;

public class MainActivity extends AppCompatActivity implements CoinClaimListener {
    private TextView txtUser;
    private ImageView imgBenra;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private CardView Daily, spin, scratch, quiz, shayari, referralcode, Games, shotv, tictac, Withdrawal;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String DIALOG_SHOWN_KEY = "dialogShown";
    private static final String COINS_KEY = "coins";
    private int coins = 0;
    private static final int FLIP_CARD_REQUEST_CODE = 0;


    //@SuppressLint("MissingInflatedId")
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spin = findViewById(R.id.sipn);
        referralcode = findViewById(R.id.referralcode);
        Daily = findViewById(R.id.carddaily);
        scratch = findViewById(R.id.scratch);
        quiz = findViewById(R.id.quiz);
        shayari = findViewById(R.id.shayari);
        Games = findViewById(R.id.games);
        shotv = findViewById(R.id.shotv);
        tictac = findViewById(R.id.tictac);
        Withdrawal = findViewById(R.id.withdrawal);
        txtUser = findViewById(R.id.txt_uesr);
        imgBenra = findViewById(R.id.img_branr);
        // Check if dialog has been shown before
        imgBenra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RulesActivity.class);
                startActivity(intent);
            }
        });

        Withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, WithdrawalActivity.class);
                startActivity(intent);

            }
        });
        Daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("fragment", "daily");
                startActivity(intent);
            }
        });
        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("fragment", "spin");
                startActivity(intent);
            }
        });
        scratch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("fragment", "scratch");
                startActivity(intent);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("fragment", "quiz");
                startActivity(intent);
            }
        });
        referralcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("fragment", "refer");
                startActivity(intent);
            }
        });
        shotv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("fragment", "watchVideo");
                startActivity(intent);
            }
        });
        tictac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, TicTacToeActivity.class);

                startActivityForResult(intent, FLIP_CARD_REQUEST_CODE);

            }
        });
        shayari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShayariActivity.class);
                startActivity(intent);
            }
        });
        Games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, FlipCardActivity.class);
                startActivityForResult(intent, FLIP_CARD_REQUEST_CODE);
//                startActivity(intent);


            }
        });
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean dialogShown = prefs.getBoolean(DIALOG_SHOWN_KEY, false);
        if (!dialogShown) {
            showDialog();

        } else {
            // Retrieve coins count from SharedPreferences
            coins = prefs.getInt(COINS_KEY, 0);
            updateCoinsDisplay();
        }

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        if (name != null) {
            txtUser.setText("" + name);
        }

    }


    private void showDialog() {
        CoinDialog dialog = new CoinDialog(MainActivity.this, this);
        dialog.show();

        // Mark dialog as shown
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(DIALOG_SHOWN_KEY, true);
        editor.apply();
    }

    @Override
    public void onCoinsClaimed(int coinsClaimed) {
        // Update coins count in SharedPreferences and UI
        if (coins == 0) {
            coins += coinsClaimed;
            updateCoinsDisplay();

            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(COINS_KEY, coins);
            editor.apply();
            // Show a toast to indicate coins claimed
            Toast.makeText(MainActivity.this, "Coins claimed!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateCoinsDisplay();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        coins = prefs.getInt(COINS_KEY, 0);


    }


    private void saveCoinsToPrefs(int coins) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(COINS_KEY, coins);
        editor.apply();
    }

    private void updateCoinsDisplay() {
        // Retrieve coins value from SharedPreferences or set default value
        TextView coinsTextView = findViewById(R.id.showprize);
        if (coinsTextView != null) {
            coinsTextView.setText(String.valueOf(coins));
        } else {
            Log.e(TAG, "Coins TextView is null");
        }
    }


    public void subtractCoins(int scratchPrice) {
        if (coins >= scratchPrice) {
            coins += scratchPrice;
//            saveCoinsToPrefs(coins);
            updateCoinsDisplay();
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(COINS_KEY, coins);
            editor.apply();
        } else {
            Toast.makeText(MainActivity.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FLIP_CARD_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("additional_coins")) {
                int additionalCoins = data.getIntExtra("additional_coins", 0);
                // Add the additional coins to the total coins count
                coins += additionalCoins;
                updateCoinsDisplay();
                saveCoinsToPrefs(coins);


            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        saveCoinsToPrefs(coins);
    }

    public void addCoins(int savedCurrentDay) {
        if (coins >= savedCurrentDay) {
            coins += savedCurrentDay;
//            saveCoinsToPrefs(coins);
            updateCoinsDisplay();
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(COINS_KEY, coins);
            editor.apply();
        } else {
            Toast.makeText(MainActivity.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
        }
    }

    public void newCoins(int rewardedCoins) {

        if (coins >= rewardedCoins) {
            coins += rewardedCoins;
            saveCoinsToPrefs(coins);
            updateCoinsDisplay();
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(COINS_KEY, coins);
            editor.apply();
        } else {
            Toast.makeText(MainActivity.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
        }
    }
}
