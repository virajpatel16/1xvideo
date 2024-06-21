package app.myecomms.a1xvideo;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

import app.myecomms.a1xvideo.adepter.ImageAdapter;

public class FlipCardActivity extends AppCompatActivity {
    ImageView curView = null;
    private int countPair = 0;
    private Button buttonClaim;



    final int[] drawable = new int[]{R.drawable.image0, R.drawable.image1, R.drawable.image2, R.drawable.image3,
            R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7};

    ArrayList<Integer> pos1 = new ArrayList<>();

    int additionalCoins;
    int currentPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_card);
        additionalCoins = getIntent().getIntExtra("additional_coins", 0);
        initView();
    }

    private void initView() {


        pos1.add(0);
        pos1.add(1);
        pos1.add(2);
        pos1.add(3);
        pos1.add(4);
        pos1.add(5);
        pos1.add(6);
        pos1.add(7);
        pos1.add(0);
        pos1.add(1);
        pos1.add(2);
        pos1.add(3);
        pos1.add(4);
        pos1.add(5);
        pos1.add(6);
        pos1.add(7);
        Collections.shuffle(pos1);

        GridView gridView = findViewById(R.id.gridView);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentPos < 0) {
                    currentPos = position;
                    curView = (ImageView) view;
                    ((ImageView) view).setImageResource(drawable[pos1.get(position)]);
                } else {
                    if (currentPos == position) {
                        ((ImageView) view).setImageResource(R.drawable.faq);
                    } else if (!pos1.get(currentPos).equals(pos1.get(position))) {
                        curView.setImageResource(R.drawable.faq);
                    } else {
                        ((ImageView) view).setImageResource(drawable[pos1.get(position)]);
                        countPair++;
                        if (countPair == 8) { // Check if all pairs have been found

                            showWinDialog();
                            Toast.makeText(FlipCardActivity.this, "You Win!", Toast.LENGTH_SHORT).show();


                        }
                    }
                    currentPos = -1;
                }
            }
        });
    }

    private void showWinDialog() {
        Dialog dialog = new Dialog(FlipCardActivity.this);
        dialog.setContentView(R.layout.ticdialog);
        dialog.setCancelable(false);

        // Set up button click listener
        buttonClaim = dialog.findViewById(R.id.buttonClaim);
        buttonClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog
                dialog.dismiss();

              // Send the additional coins count back to MainActivity
                Intent intent = new Intent();
                intent.putExtra("additional_coins", 100);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


}