package app.myecomms.a1xvideo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;

import app.myecomms.a1xvideo.MainActivity;
import app.myecomms.a1xvideo.MainActivity2;
import app.myecomms.a1xvideo.R;
import app.myecomms.a1xvideo.interfase.CoinClaimListener;
import in.myinnos.androidscratchcard.ScratchCard;


public class ScratchCradFragment extends Fragment {
    private View view;
    private ImageView imgBack;
    private TextView price;

    private LottieAnimationView lottieAnimationView;
    private ScratchCard scratchCard;
    private TextView textViewprize;

    private int totalCoins;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_scratch_crad, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        price = rootView.findViewById(R.id.price);
        scratchCard = rootView.findViewById(R.id.scratchCard);
        lottieAnimationView = rootView.findViewById(R.id.anim);
        lottieAnimationView.setVisibility(View.INVISIBLE);
        scratchCard.setOnScratchListener(new ScratchCard.OnScratchListener() {
            @Override
            public void onScratch(ScratchCard scratchCard, float visiblePercent) {
                int scratchPrice = generateRandomNumber();
                String priceText = String.valueOf(scratchPrice);
                price.setText(priceText);


                if (visiblePercent > 0.2) {
                    scratchCard.setVisibility(View.GONE);
                    lottieAnimationView.setVisibility(View.VISIBLE);
                    // Subtract scratch card coins
                    if (getActivity() != null && getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).subtractCoins(scratchPrice);
                    }
                    // Subtract scratch card coins
                    if (getActivity() != null && getActivity() instanceof MainActivity2) {
                        ((MainActivity2) getActivity()).subtractCoins(scratchPrice);
                    }
                    Toast.makeText(getActivity(), "You Won " + scratchPrice, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private int generateRandomNumber() {

        return (int) (Math.random() * 50);
    }


}