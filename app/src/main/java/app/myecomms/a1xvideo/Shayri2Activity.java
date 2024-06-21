package app.myecomms.a1xvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Shayri2Activity extends AppCompatActivity {
 private TextView hindi;
 private ImageView copy,share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shayri2);
       initView();
    }

    private void initView() {
        copy=findViewById(R.id.img_copy);
        share=findViewById(R.id.img_share);
        hindi=findViewById(R.id.hindi);
        String fname ="Fname not set";
        Bundle extras =getIntent().getExtras();
        if (extras!=null){
            fname=extras.getString("fname");

        }
       hindi.setText(fname);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyToClipboard(hindi.getText().toString());
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText(hindi.getText().toString());
            }
        });
    }

    private void shareText(String text) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", text);
        if (clipboard != null) {
            clipboard.setText(text);
            Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Clipboard not available", Toast.LENGTH_SHORT).show();
        }

    }
}