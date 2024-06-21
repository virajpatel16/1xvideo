package app.myecomms.a1xvideo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.myecomms.a1xvideo.adepter.ShayariAdepter;
import app.myecomms.a1xvideo.modals.Shayari;

public class HindiShayariActivity extends AppCompatActivity {
    private RecyclerView rvShayari;
    private ShayariAdepter shayariAdepter;
    private ShayariAdepter.RecyclerViewClickListener listener;
    private final ArrayList<Shayari> shayariList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hindi_shayari);
        initView();
    }

    private void initView() {
        rvShayari = findViewById(R.id.rv_shayari);

        shayariList.add(new Shayari("राज तो हमारा हर जगह पे है,\n" +
                "पसंद करने वालों के \"दिल\" में और,\n" +
                "नापसंद करने वालों के \"दिमाग\" में"));
        shayariList.add(new Shayari("सर झुकाने की आदत नहीं है,\n" +
                "आँसू बहाने की आदत नहीं है,\n" +
                "हम खो गए तो पछताओगे बहुत,\n" +
                "क्युकी हमारी लौट के आने की आदत नहीं है."));
        shayariList.add(new Shayari("दिल की किताब में गुलाब उनका था,\n" +
                "रात की नींद में ख्वाब उनका था,\n" +
                "कितना प्यार करते हो जब हमने पूछा,\n" +
                "मर जायंगे तुम्हारे बिना ये जबाब उनका था."));
        shayariList.add(new Shayari("तेरी आवाज़ से प्यार है हमें,\n" +
                "इतना इज़हार हम कर नहीं सकते,\n" +
                "हमारे लिए तू उस खुदा की तरह है,\n" +
                "जिसका दीदार हम कर नहीं सकते."));
        shayariList.add(new Shayari("कभी तुम्हे पूरा लिखूँ कभी अधूरा लिखूँ\n" +
                "मै रातो मे बैठकर तुम्हे सवेरा लिखूँ...!\n" +
                "\n" +
                "मै जब भी लिखू बस इतना लिखूँ\n" +
                "मुझे तेरा,,और तुझे मेरा लिखूँ."));
        shayariList.add(new Shayari("कभी तुम्हे पूरा लिखूँ कभी अधूरा लिखूँ\n" +
                "मै रातो मे बैठकर तुम्हे सवेरा लिखूँ...!\n" +
                "\n" +
                "मै जब भी लिखू बस इतना लिखूँ\n" +
                "मुझे तेरा,,और तुझे मेरा लिखूँ."));
        shayariList.add(new Shayari("“लोग पूछते है मुझसे हाल मेरा\n" +
                "मैं क्या बताऊं मैं उदास रहता हूं,\n" +
                "और ज्यादा कुछ नहीं बस उसकी आस में रहता हूं ।।”"));
        shayariList.add(new Shayari("“मेरे अल्फ़ाज़ बंधे हुए है कुछ एहसानो से,\n" +
                "जब मिट जाऊंगा तभी चूका पाउँगा |\n" +
                "कोई उम्मीद नहीं मुझे इस ज़माने से,\n" +
                "जो मेरा फ़र्ज़ है वो निभाके ही लौट जाऊंगा”"));
        shayariList.add(new Shayari("चाहने वाले तो लाखो है जनाब\n" +
                "एक समझने वाला मिल जाए\n" +
                "तो ही लाइफ खुशनुमा है..!!"));
        shayariList.add(new Shayari("अपनी सोच को हमेशा सकारात्मक रखिए\n" +
                "यही हमारे जीवन को आकार देती हैं\n" +
                "और जीवन को आनंदमय\n" +
                "बनाती है..!!"));
        shayariList.add(new Shayari("मेरी अपने किस्मत से बनती\n" +
                "नहीं है इसलिए\n" +
                "खामोश रहकर बातो को\n" +
                "टालने का हुनर\n" +
                "सिख गया ..!!"));
        shayariList.add(new Shayari("जमाना तब तक पूछता है यारो\n" +
                "जब तक तू आबाद है\n" +
                "बर्बाद होने पर सबसे पहले\n" +
                "करीबी ही पराये बनते हैं..!!"));
        setOnClickListner();
        shayariAdepter = new ShayariAdepter(HindiShayariActivity.this, shayariList,listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HindiShayariActivity.this, RecyclerView.VERTICAL, false);
        rvShayari.setLayoutManager(layoutManager);
        rvShayari.setAdapter(shayariAdepter);

    }

    private void setOnClickListner() {
      listener=new ShayariAdepter.RecyclerViewClickListener() {
          @Override
          public void onClick(View v, int position) {
              Intent intent=new Intent(HindiShayariActivity.this,Shayri2Activity.class);
              intent.putExtra("fname",shayariList.get(position).Fname);
              startActivity(intent);
          }
      };

    }
}