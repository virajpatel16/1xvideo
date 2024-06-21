package app.myecomms.a1xvideo.modals;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import app.myecomms.a1xvideo.R;
import app.myecomms.a1xvideo.interfase.CoinClaimListener;

public class CoinDialog extends Dialog {
    private final CoinClaimListener mListener;
    private Dialog dialog;

    public CoinDialog(@NonNull Context context, CoinClaimListener mListener) {
        super(context);
        this.mListener = mListener;
        setContentView(R.layout.coin_dialog);
        // Initialize views from dialog layout
        ExtendedFloatingActionButton buttonClaim = findViewById(R.id.buttonClaim);
        buttonClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if mListener is not null before invoking its method
                if (mListener != null) {

                    // Notify the activity about the coins claimed
                    mListener.onCoinsClaimed(5000);
                }

                dismiss();
            }
        });

    }


}
