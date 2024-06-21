package app.myecomms.a1xvideo;

import static java.lang.Character.getName;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.BreakIterator;

import app.myecomms.a1xvideo.fragment.WatchVideoFragment;
import app.myecomms.a1xvideo.interfase.CoinClaimListener;
import app.myecomms.a1xvideo.modals.CoinDialog;

public class WithdrawalActivity extends AppCompatActivity {
    private CardView coin1, coin2, coin3;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String DIALOG_SHOWN_KEY = "dialogShown";
    private static final String COINS_KEY = "coins";
    private int coins;
    private ImageView imgBrck;
    private ExtendedFloatingActionButton success, failure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);

        initView();

    }

    private void initView() {
        coin1 = findViewById(R.id.coin1);
        coin2 = findViewById(R.id.coin2);
        coin3 = findViewById(R.id.coin3);
        imgBrck = findViewById(R.id.img_brck);
        coin1.setOnClickListener(v -> handleCoinClick(40999999));
        coin2.setOnClickListener(v -> handleCoinClick(199999999));
        coin3.setOnClickListener(v -> handleCoinClick(1099999999));

        imgBrck.setOnClickListener(new View.OnClickListener() {
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

    }

    private void handleCoinClick(int requiredCoins) {
        if (coins >= requiredCoins) {
            showSuccessDialog();
        } else {

            showFailureDialog();
        }
    }

    private void showFailureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_failure, null);

        final AlertDialog dialog = builder.setView(dialogView).create();

        failure = dialogView.findViewById(R.id.buttonconfalis);
        failure.setOnClickListener(v -> {
            // Handle the action for failure button click
            // For example, retry the operation or show a toast
            // Toast.makeText(this, "Retrying...", Toast.LENGTH_SHORT).show();
            dialog.dismiss(); // Dismiss the dialog
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_custom, null);

        final AlertDialog dialog = builder.setView(dialogView).create();

        Button success = dialogView.findViewById(R.id.buttonconform);
        success.setOnClickListener(v -> {
            // Handle the action for success button click
            // For example, proceed to the next activity or show a toast
            // Toast.makeText(this, "Proceeding...", Toast.LENGTH_SHORT).show();
            dialog.dismiss(); // Dismiss the dialog
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void showDialog() {
        CoinDialog dialog = new CoinDialog((Context) WithdrawalActivity.this, (CoinClaimListener) this);
        dialog.show();

        // Mark dialog as shown
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(DIALOG_SHOWN_KEY, true);
        editor.apply();
    }

    private void updateCoinsDisplay() {
        TextView coinsTextView = findViewById(R.id.showprize);
        coinsTextView.setText(String.valueOf(coins));
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
            Toast.makeText(WithdrawalActivity.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(WithdrawalActivity.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
        }
    }
}