package app.myecomms.a1xvideo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import app.myecomms.a1xvideo.fragment.DailyBonusFragment;
import app.myecomms.a1xvideo.fragment.QuizFragment;
import app.myecomms.a1xvideo.fragment.ReferCodeFragment;
import app.myecomms.a1xvideo.fragment.ScratchCradFragment;
import app.myecomms.a1xvideo.fragment.SpinWheelFragment;
import app.myecomms.a1xvideo.fragment.WatchVideoFragment;
import app.myecomms.a1xvideo.interfase.CoinClaimListener;
import app.myecomms.a1xvideo.modals.CoinDialog;

public class MainActivity2 extends AppCompatActivity implements CoinClaimListener {
    private TextView txtUser;
    private ImageView imgBenra, imgBrack;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private CardView scratch, Daily;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String DIALOG_SHOWN_KEY = "dialogShown";
    private static final String COINS_KEY = "coins";
    private int coins;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtUser = findViewById(R.id.txt_uesr);
        imgBrack = findViewById(R.id.img_brck);
        imgBrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
//            subtractCoins(10);
        }
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        if (name != null) {
            txtUser.setText("" + name);
        }


        if (getIntent().hasExtra("fragment")) {
            // Get the fragment identifier from the intent
            String fragmentId = getIntent().getStringExtra("fragment");
            // Load the corresponding fragment based on the identifier
            switch (fragmentId) {
                case "daily":
                    loadFragment(new DailyBonusFragment());
                    break;
                case "spin":
                    loadFragment(new SpinWheelFragment());
                    break;
                case "scratch":
                    loadFragment(new ScratchCradFragment());

                    break;
                case "quiz":
                    loadFragment(new QuizFragment());
                    break;
                case "refer":
                    loadFragment(new ReferCodeFragment());
                    break;
                case "watchVideo":
                    loadFragment(new WatchVideoFragment());
                    break;

            }
        } else {

        }
    }


    private void updateCoinsDisplay() {
        TextView coinsTextView = findViewById(R.id.showprize);
        coinsTextView.setText(String.valueOf(coins));


    }

    private void showDialog() {
        CoinDialog dialog = new CoinDialog(MainActivity2.this, this);
        dialog.show();

        // Mark dialog as shown
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(DIALOG_SHOWN_KEY, true);
        editor.apply();
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();

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
            Toast.makeText(MainActivity2.this, "Coins claimed!", Toast.LENGTH_SHORT).show();
        }

    }

    public void subtractCoins(int scratchPrice) {
        if (coins >= scratchPrice) {
            coins += scratchPrice;
            updateCoinsDisplay();

            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(COINS_KEY, coins);
            editor.apply();
        } else {
            Toast.makeText(MainActivity2.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
        }
    }


    public void addCoins(int parseInt) {
        if (coins >= parseInt) {
            coins += parseInt;
            updateCoinsDisplay();
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(COINS_KEY, coins);
            editor.apply();

        } else {
            Toast.makeText(MainActivity2.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
        }
    }


    public void newCoins(int rewardedCoins) {
        if (coins >= rewardedCoins) {
            coins += rewardedCoins;
            updateCoinsDisplay();
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(COINS_KEY, coins);
            editor.apply();

        } else {
            Toast.makeText(MainActivity2.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
        }
    }
}
