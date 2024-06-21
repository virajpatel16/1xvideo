package app.myecomms.a1xvideo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText etUser;
    private Button btnSub;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        // Check if the user has already agreed to the terms and conditions
        boolean agreed = sharedPreferences.getBoolean("agreed", false);
        if (agreed) {
            // If agreed, directly navigate to the next activity
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish(); // Finish this activity to prevent going back to it
            return; // Return from onCreate
        }
        setContentView(R.layout.activity_sign_up);
        initView();
    }

    private void initView() {
        etUser = findViewById(R.id.et_user);
        btnSub = findViewById(R.id.btn_submit);

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUser.getText().toString().trim();


                if (user.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, etUser.getText().toString());
                editor.putBoolean("agreed", true);
                editor.apply();

                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(SignUpActivity.this, "successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}