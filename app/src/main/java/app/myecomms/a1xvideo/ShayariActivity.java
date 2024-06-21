package app.myecomms.a1xvideo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ShayariActivity extends AppCompatActivity {
    private CardView hindi, English;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shayari);
        initView();
    }

    private void initView() {
        hindi = findViewById(R.id.hindi);
        English = findViewById(R.id.english);
        hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShayariActivity.this, HindiShayariActivity.class);
                startActivity(intent);
            }
        });
        English.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShayariActivity.this, EnglishShayariActivity.class);
                startActivity(intent);
            }
        });

    }
}