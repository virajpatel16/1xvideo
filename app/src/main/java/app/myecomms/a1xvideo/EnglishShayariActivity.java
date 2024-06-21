package app.myecomms.a1xvideo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import app.myecomms.a1xvideo.adepter.ShayariAdepter;
import app.myecomms.a1xvideo.modals.Shayari;

public class EnglishShayariActivity extends AppCompatActivity {
    private RecyclerView rvEnglishsayari;
    private ShayariAdepter shayariAdepter;
    private ShayariAdepter.RecyclerViewClickListener listener;
    private final ArrayList<Shayari> shayariList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_shayari);
        initView();
    }

    private void initView() {
      rvEnglishsayari=findViewById(R.id.rv_englishsayari);

shayariList.add(new Shayari("“Don’t be too confident\n" +
        "when someone tells U\n" +
        "she likes U,The real\n" +
        "question is..\n" +
        "“UNTIL WHEN..?”\n" +
        "B’coz just like seasons..\n" +
        "SOME PEOPLE CHANGE FEELINGS”."));
shayariList.add(new Shayari("A person who always lives in dreams,won't be a dream for others\n" +
        "So come out from dreams.\n" +
        "Every mind can think in different way but most of us doesn't \n" +
        "implement it,once try to implement then u will \n" +
        "became as dream for others."));
shayariList.add(new Shayari("\"It's your outlook on life that counts. If you take yourself \n" +
        "lightly and don't take yourself too seriously, \n" +
        "pretty soon you can find the humor in our everyday lives.\n" +
        " And sometimes it can be a lifesaver.\""));
shayariList.add(new Shayari("\"Never lose sight of the fact that the most important yard \n" +
        "stick to your success is how you treat other people.\""));
shayariList.add(new Shayari("I have very strong feelings about \n" +
        "how you lead your life. You always look ahead,\n" +
        " you never look back.\""));
shayariList.add(new Shayari("\"Real courage is doing the right thing when nobody's looking. \n" +
        "Doing the unpopular thing because it's what you \n" +
        "believe, and the heck with everybody.\""));
shayariList.add(new Shayari("Every year you make a resolution to \n" +
        "change yourself. This year, make a \n" +
        "resolution to be yourself."));
shayariList.add(new Shayari("A worthy New Year's resolution, perhaps,\n" +
        " is to take no hatred into the \n" +
        "New Year without requiring it to\n" +
        " restate its purpose."));
shayariList.add(new Shayari("Approach the New Year with resolve to \n" +
        "find the opportunities hidden in \n" +
        "each new day."));
shayariList.add(new Shayari("\"What you get by achieving your goals is not\n" +
        " as important as what you become by \n" +
        "achieving your goals.\""));
shayariList.add(new Shayari("“Success is liking yourself, liking what \n" +
        "you do, and liking how you do it.”"));
shayariList.add(new Shayari("\"If you cannot do great things, do \n" +
        "small things in a great way.\""));


      setOnClickListner();
        shayariAdepter = new ShayariAdepter(EnglishShayariActivity.this, shayariList,listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(EnglishShayariActivity.this, RecyclerView.VERTICAL, false);
        rvEnglishsayari.setLayoutManager(layoutManager);
        rvEnglishsayari.setAdapter(shayariAdepter);

    }

    private void setOnClickListner() {
        listener=new ShayariAdepter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent=new Intent(EnglishShayariActivity.this,Shayri2Activity.class);
                intent.putExtra("fname",shayariList.get(position).Fname);
                startActivity(intent);
            }
        };    }
}