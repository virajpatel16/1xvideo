package app.myecomms.a1xvideo.fragment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.myecomms.a1xvideo.MainActivity2;
import app.myecomms.a1xvideo.R;


public class SpinWheelFragment extends Fragment {
    LuckyWheel luckyWheel;
    List<WheelItem> wheelItemList = new ArrayList<>();
    private View view;
    String points;
    Button btnClick;

    SharedPreferences sharedPreferences;
    int dailySpins = 3;
    // MediaPlayer to play sound
    private MediaPlayer FXPlayer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_spin_wheel, container, false);
        luckyWheel = view.findViewById(R.id.luckwheel);
        btnClick = view.findViewById(R.id.btn_click);
        sharedPreferences = requireActivity().getSharedPreferences("SPIN_WHEEL_PREFS", 0);


        WheelItem wheelItem1 = new WheelItem(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null), BitmapFactory.decodeResource(getResources(), R.drawable.coin), "100");
        wheelItemList.add(wheelItem1);

        WheelItem wheelItem2 = new WheelItem(ResourcesCompat.getColor(getResources(), R.color.purple_500, null), BitmapFactory.decodeResource(getResources(), R.drawable.coin), "350");
        wheelItemList.add(wheelItem2);

        WheelItem wheelItem3 = new WheelItem(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null), BitmapFactory.decodeResource(getResources(), R.drawable.coin), "400");
        wheelItemList.add(wheelItem3);

        WheelItem wheelItem4 = new WheelItem(ResourcesCompat.getColor(getResources(), R.color.purple_500, null), BitmapFactory.decodeResource(getResources(), R.drawable.coin), "500");
        wheelItemList.add(wheelItem4);

        WheelItem wheelItem5 = new WheelItem(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null), BitmapFactory.decodeResource(getResources(), R.drawable.coin), "600");
        wheelItemList.add(wheelItem5);

        WheelItem wheelItem6 = new WheelItem(ResourcesCompat.getColor(getResources(), R.color.purple_500, null), BitmapFactory.decodeResource(getResources(), R.drawable.coin), "650");
        wheelItemList.add(wheelItem6);

        WheelItem wheelItem7 = new WheelItem(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null), BitmapFactory.decodeResource(getResources(), R.drawable.coin), "700");
        wheelItemList.add(wheelItem7);

        WheelItem wheelItem8 = new WheelItem(ResourcesCompat.getColor(getResources(), R.color.purple_500, null), BitmapFactory.decodeResource(getResources(), R.drawable.coin), "940");
        wheelItemList.add(wheelItem8);

        WheelItem wheelItem9 = new WheelItem(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null), BitmapFactory.decodeResource(getResources(), R.drawable.coin), "850");
        wheelItemList.add(wheelItem9);

        WheelItem wheelItem10 = new WheelItem(ResourcesCompat.getColor(getResources(), R.color.purple_500, null), BitmapFactory.decodeResource(getResources(), R.drawable.coin), "1,050");
        wheelItemList.add(wheelItem10);

        WheelItem wheelItem11 = new WheelItem(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null), BitmapFactory.decodeResource(getResources(), R.drawable.coin), "2,500");
        wheelItemList.add(wheelItem11);

        WheelItem wheelItem12 = new WheelItem(ResourcesCompat.getColor(getResources(), R.color.purple_500, null), BitmapFactory.decodeResource(getResources(), R.drawable.coin), "5,000");
        wheelItemList.add(wheelItem12);

        luckyWheel.addWheelItems(wheelItemList);

        // Initialize MediaPlayer with the audio file
//        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.spin_sound);

        luckyWheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                // Stop playing audio when the spinning stops

//                WheelItem itemSelected = wheelItemList.get(Integer.parseInt(points) - 1);
                int index = Integer.parseInt(points) - 1;
                if (index >= 0 && index < wheelItemList.size()) {
                    // Access the wheelItemList at the valid index
                    WheelItem itemSelected = wheelItemList.get(index);
                    String points_amount = itemSelected.text;
                    FXPlayer.stop();
                    ((MainActivity2) requireActivity()).addCoins(Integer.parseInt(points_amount));

                    LottieAnimationView animationView = view.findViewById(R.id.spinCompleteAnimationView);
                    animationView.setAnimation(R.raw.win); // replace with the path to your animation file
                    animationView.playAnimation();

                    Toast.makeText(getActivity(), points_amount, Toast.LENGTH_SHORT).show();
                    // Proceed with further operations using itemSelected
                } else {
                    // Handle the case where points is not within a valid range
                    Log.e(TAG, "Invalid index: " + index);
                }
//                String points_amount = itemSelected.text;
                // Cast to MainActivity2 instead of MainActivit

            }
        });

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Play audio when spinning
                // Initialize media player if it's null

                int spinsDone = sharedPreferences.getInt("SPINS_DONE", 0);

                if (spinsDone < dailySpins) {

                    spinsDone++;
                    sharedPreferences.edit().putInt("SPINS_DONE", spinsDone).apply();


                    // Play audio

                    Random random = new Random();
                    points = String.valueOf(random.nextInt(10));
                    if (points.equals("0")) {
                        points = String.valueOf(1);
                    }
                    luckyWheel.rotateWheelTo(Integer.parseInt(points));
                    FXPlayer = MediaPlayer.create(requireContext(), R.raw.spin_sound);
                    FXPlayer.start();
                } else {
                    Toast.makeText(requireContext(), "You have already used all spins for today.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }



}