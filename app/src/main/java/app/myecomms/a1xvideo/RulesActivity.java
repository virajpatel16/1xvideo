package app.myecomms.a1xvideo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import app.myecomms.a1xvideo.adepter.RulesAdepetr;
import app.myecomms.a1xvideo.modals.Rules;

public class RulesActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private RulesAdepetr rulesAdepetr;
    private final ArrayList<Rules> rulesList = new ArrayList<>();

    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        intiView();
    }

    private void intiView() {
        btnNext = findViewById(R.id.btn_next);
        viewPager = findViewById(R.id.viewpager);


        // Set the adapter to the ViewPager

        // Inside your onCreate method or where you initialize your ViewPager and button
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = viewPager.getCurrentItem();
                int pageCount = rulesAdepetr.getCount();

                if (currentItem + 1 < pageCount) {
                    viewPager.setCurrentItem(currentItem + 1);
                } else {
                    // If the current item is the last page
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });

// Set the text of the button to "Next" by default
        btnNext.setText("Next");
        viewPager.setAdapter(rulesAdepetr);

        // Now set up the DotsIndicator with the ViewPager

// Add an onPageChangeListener to your ViewPager
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Not needed for this implementation
            }

            @Override
            public void onPageSelected(int position) {
                int pageCount = rulesAdepetr.getCount();

                // Check if the new position is the last but one page
                if (position == pageCount - 1) {
                    btnNext.setText("Finish");
                } else {
                    btnNext.setText("Next");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Not needed for this implementation
            }
        });


        rulesList.add(new Rules(R.drawable.qureka_rules_1, "Time Duration", "Time duration not finish to not get coin.."));
        rulesList.add(new Rules(R.drawable.qureka_rules_2, "Automatic add coin", "Time duration finish after you get \nautomatically coin your wallet.."));
        rulesList.add(new Rules(R.drawable.qureka_rules_3, "Open Link Time", "Link open time if you close your\napplicaton to not get coins.."));

        rulesAdepetr = new RulesAdepetr(RulesActivity.this, rulesList);
        viewPager.setAdapter(rulesAdepetr);
    }
}