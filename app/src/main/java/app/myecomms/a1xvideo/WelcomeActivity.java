package app.myecomms.a1xvideo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    private Button btnGet;
    private LinearLayout linshare, linreta, linepolicy;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_FIRST_LOGIN = "firstLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if it's the first login
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isFirstLogin = prefs.getBoolean(KEY_FIRST_LOGIN, true);

        setContentView(R.layout.activity_welcome);
        linshare = findViewById(R.id.lin_share);
        linreta = findViewById(R.id.lin_Reta);
        linepolicy = findViewById(R.id.lin_privecy);
        btnGet = findViewById(R.id.btn_get);


        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirstLogin) {
                    // If it's the first login, navigate to the SignUpActivity
                    Intent intent = new Intent(WelcomeActivity.this, SignUpActivity.class);
                    startActivity(intent);

                    // Set the first login flag to false
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean(KEY_FIRST_LOGIN, false);
                    editor.apply();
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                }
//


            }
        });
        linepolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri privacyPolicyUri = Uri.parse("https://privacy.com/privacy_policy");
                Intent privacyPolicyIntent = new Intent(Intent.ACTION_VIEW, privacyPolicyUri);
                startActivity(privacyPolicyIntent);
            }
        });
        linshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareText = "Check out this awesome app!";
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
        linreta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace "com.example.yourpackage" with your app package name
                Uri uri = Uri.parse("https://play.google.com/store/games?hl=en&gl=US");
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To open only Google Play Store App
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    // If Google Play Store app is not installed, open the website
                    Uri webpage = Uri.parse("https://play.google.com/store/games?hl=en&gl=US" + getPackageName());
                    Intent goToWeb = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(goToWeb);
                }
            }
        });
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.exit_dalog, null);
        alert.setView(view);
        final AlertDialog dialog = alert.create();
        dialog.setCancelable(false);
        view.findViewById(R.id.btnyes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finishAffinity();
                // finishAndRemoveTask();
            }
        });
        view.findViewById(R.id.btnno).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


    }
}