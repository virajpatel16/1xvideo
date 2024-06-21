package app.myecomms.a1xvideo.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.myecomms.a1xvideo.MainActivity;
import app.myecomms.a1xvideo.MainActivity2;
import app.myecomms.a1xvideo.R;

public class DailyBonusFragment extends Fragment {
    private TextView txtCaptcha;
    private Button btnClaim, btnCancel;
    private EditText etAnswer;
    private Button btnSubmit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_bonus, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        txtCaptcha = view.findViewById(R.id.txt_captcha);
        etAnswer = view.findViewById(R.id.et_answer);
        btnSubmit = view.findViewById(R.id.btn_Submit);

        generateCaptcha();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void checkAnswer() {
        String userAnswer = etAnswer.getText().toString().trim();
        if (!userAnswer.isEmpty()) {
            int answer;
            try {
                answer = Integer.parseInt(userAnswer);
            } catch (NumberFormatException e) {
                Toast.makeText(getActivity(), "Invalid input. Please enter a number.", Toast.LENGTH_SHORT).show();
                return;
            }

            int correctAnswer = getCorrectAnswer();
            if (answer == correctAnswer) {
                Toast.makeText(getActivity(), "Captcha solved!", Toast.LENGTH_SHORT).show();
                dailyCheckIn();
                generateCaptcha(); // Generate a new CAPTCHA for the next challenge
                etAnswer.getText().clear(); // Clear the answer field
            } else {
                Toast.makeText(getActivity(), "Incorrect answer. Please try again.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Please enter an answer.", Toast.LENGTH_SHORT).show();
        }
    }

    private void dailyCheckIn() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_layout2);
        dialog.setCancelable(false);

        btnClaim = dialog.findViewById(R.id.btn_claim);
        btnCancel = dialog.findViewById(R.id.btn_cancel);

        btnClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent=new Intent(getContext(), MainActivity.class);
                startActivity(intent);

                checkAnswer();
                int rewardedCoins = 10;

                // Update coins in MainActivity2
                if (getActivity() instanceof MainActivity2) {
                    ((MainActivity2) getActivity()).newCoins(rewardedCoins);
                }
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).newCoins(rewardedCoins);
                }

                Toast.makeText(getActivity(), "Claim clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private int getCorrectAnswer() {
        String captchaText = txtCaptcha.getText().toString().trim();

        // Use regular expressions to extract operands from the captcha text
        Pattern pattern = Pattern.compile("(\\d+) \\+ (\\d+) = \\?");
        Matcher matcher = pattern.matcher(captchaText);

        if (matcher.matches()) {
            try {
                int operand1 = Integer.parseInt(matcher.group(1));
                int operand2 = Integer.parseInt(matcher.group(2));
                return operand1 + operand2;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return 0; // Return a default value or handle the error accordingly
            }
        } else {
            // Handle the unexpected format of the CAPTCHA text
            return 0; // Return a default value or handle the error accordingly
        }
    }

    private void generateCaptcha() {
        Random random = new Random();
        int operand1 = random.nextInt(99);
        int operand2 = random.nextInt(99);
        String captcha = operand1 + " + " + operand2 + " = ?";
        txtCaptcha.setText(captcha);
    }
}
