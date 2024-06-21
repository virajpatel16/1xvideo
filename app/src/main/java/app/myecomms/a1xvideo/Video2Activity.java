package app.myecomms.a1xvideo;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import app.myecomms.a1xvideo.fragment.WatchVideoFragment;

public class Video2Activity extends AppCompatActivity {
    private VideoView videoView;
    private String videourl;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String COINS_KEY = "coins";
    private boolean hasWatchedVideo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video2);

        // Initialize the VideoView object
        videoView = findViewById(R.id.video_view);
        if (getIntent().getExtras() != null) {
            videourl = getIntent().getStringExtra("video_url");
            playVideo(videourl);
        }
    }

    private void playVideo(String videourl) {
        videoView.setVideoURI(Uri.parse(videourl));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // Start playing the video
                videoView.start();
            }
        });

        // Set an OnCompletionListener to the VideoView to show dialog when video ends
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // Set hasWatchedVideo to true
                hasWatchedVideo = true;
                // Show dialog to add coins
                showAddCoinsDialog();
            }
        });
    }

    private void showAddCoinsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.videodialog, null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);

        Button addButton = dialogView.findViewById(R.id.add_coins_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add coins
                addCoins(100);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                WatchVideoFragment fragment = new WatchVideoFragment();

                fragmentTransaction.commit();
                // Prepare result intent
                Intent resultIntent = new Intent(Video2Activity.this, WatchVideoFragment.class);
                resultIntent.putExtra("coinsAdded", 100);
                startActivity(resultIntent);

                // Close dialog and activity
                dialog.dismiss();
                finish();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void addCoins(int coins) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int oldCoins = preferences.getInt(COINS_KEY, 0);
        int newCoins = oldCoins + coins;

        // Update coins count in SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(COINS_KEY, newCoins);
        editor.apply();

        // Show a toast to indicate coins added
        Toast.makeText(this, "It will take some moments!  " + coins , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        // If the user has watched the video, add coins and show the dialog
        if (hasWatchedVideo) {
            showAddCoinsDialog();
        } else {
            super.onBackPressed();
        }
    }
}