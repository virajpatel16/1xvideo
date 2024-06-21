package app.myecomms.a1xvideo.adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.myecomms.a1xvideo.R;
import app.myecomms.a1xvideo.modals.Rules;

public class RulesAdepetr extends PagerAdapter {
    private final Context context;
    private ArrayList<Rules> rulesList = new ArrayList<>();

    public RulesAdepetr(Context context, ArrayList<Rules> rulesList) {
        this.context = context;
        this.rulesList = rulesList;
    }

    @Override
    public int getCount() {
        return rulesList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Rules rules = rulesList.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.item_rules, container, false);

        TextView txtDuration = view.findViewById(R.id.txt_duration);
        TextView txtGetcoin = view.findViewById(R.id.txt_getcoin);
        ImageView imgTime = view.findViewById(R.id.img_time);


        txtDuration.setText(rules.Fname);
        txtGetcoin.setText(rules.Lname);

        Glide.with(context).load(rules.Image).into(imgTime);


        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
