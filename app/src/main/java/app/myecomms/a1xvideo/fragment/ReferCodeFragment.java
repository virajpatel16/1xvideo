package app.myecomms.a1xvideo.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import app.myecomms.a1xvideo.R;


public class ReferCodeFragment extends Fragment {

    private EditText referralCodeEditText;

    Button reffrelbtn;
    ScrollView scrollView;
    private Button verifyButton;
    private String[] validReferralCodes = {"CODE1", "CODE2", "CODE3"}; // Define your valid referral codes here
    private String userReferralCode = "USERCODE";
private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_refer_code, container, false);

        referralCodeEditText = view.findViewById(R.id.refreffrealcode);
        TextView textView = view.findViewById(R.id.reftextcopy);
        verifyButton = view.findViewById(R.id.refverifycode);
        scrollView = view.findViewById(R.id.refscrollView);
        reffrelbtn=view.findViewById(R.id.reffrelbtn);

        reffrelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");

// Add the referral code and app link to the message
                String referralCode = "9TEHX3TK";
                String appLink = "https://play.google.com/store/apps/details?id=com.yourpackagename";
                String shareMessage = "Referral Code: " + referralCode + "\nCheck out this awesome app:\n" + appLink;

                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

// Create a chooser to let the user choose how to share the content
                Intent chooserIntent = Intent.createChooser(shareIntent, "Share via");

// Start the chooser activity
                startActivity(chooserIntent);

            }
        });
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredCode = referralCodeEditText.getText().toString().trim();
                if (!enteredCode.isEmpty()) {
                    if (isValidReferralCode(enteredCode)) {
                        // Referral code is valid
                        Toast.makeText(requireContext(), "Referral code is valid", Toast.LENGTH_SHORT).show();
                    } else {
                        // Referral code is invalid
                        Toast.makeText(requireContext(), "Invalid referral code", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // No referral code entered
                    Toast.makeText(requireContext(), "Please enter a referral code", Toast.LENGTH_SHORT).show();

                }
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

            }
        });
        referralCodeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.smoothScrollTo(0, referralCodeEditText.getBottom());
                        }
                    });
                }
            }
        });

        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Get text from TextView
                String textToCopy = textView.getText().toString();

                // Copy text to clipboard
                ClipboardManager clipboard = (ClipboardManager) requireActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Text", textToCopy);
                clipboard.setPrimaryClip(clip);

                // Show toast indicating text copied
                Toast.makeText(requireContext(), "Text copied to clipboard", Toast.LENGTH_SHORT).show();

                return true; // Indicate that long click is consumed
            }
        });
        // Register long click listener

        return view;
    }
    private boolean isValidReferralCode(String code) {
        // Check if the entered code exists in the array of valid codes
        if (TextUtils.isEmpty(code)) {
            return false; // Empty code is not valid
        }
        if (code.equals(userReferralCode)) {
            return false; // Referring oneself is not allowed
        }
        for (String validCode : validReferralCodes) {
            if (validCode.equals(code)) {
                return true;
            }
        }
        return false;
    }
}