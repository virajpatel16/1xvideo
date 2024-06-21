package app.myecomms.a1xvideo.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.myecomms.a1xvideo.R;
import app.myecomms.a1xvideo.adepter.VideoAdepter;
import app.myecomms.a1xvideo.modals.Video;


public class WatchVideoFragment extends Fragment  {

    private View view;
    private RecyclerView rvVideo;
    private VideoAdepter videoAdepter;

    private ArrayList<Video>videoList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container ,Bundle savedInstanceState) {
              view= inflater.inflate(R.layout.fragment_watch_video, container, false);
              initView();
        return view;
    }

    private void initView() {
        rvVideo=view.findViewById(R.id.rv_video);
        videoList.add(new Video(R.drawable.video0,R.drawable.vpaly));
        videoList.add(new Video(R.drawable.video1,R.drawable.vpaly));
        videoList.add(new Video(R.drawable.video2,R.drawable.vpaly));
        videoList.add(new Video(R.drawable.video3,R.drawable.vpaly));
        videoList.add(new Video(R.drawable.video4,R.drawable.vpaly));
        videoList.add(new Video(R.drawable.video5,R.drawable.vpaly));
        videoList.add(new Video(R.drawable.video6,R.drawable.vpaly));
        videoList.add(new Video(R.drawable.video7,R.drawable.vpaly));
        videoList.add(new Video(R.drawable.video8,R.drawable.vpaly));
        videoList.add(new Video(R.drawable.video9,R.drawable.vpaly));
        videoList.add(new Video(R.drawable.video10,R.drawable.vpaly));
        videoList.add(new Video(R.drawable.video11,R.drawable.vpaly));
        videoList.add(new Video(R.drawable.video12,R.drawable.vpaly));
        videoList.add(new Video(R.drawable.video13,R.drawable.vpaly));
        videoList.add(new Video(R.drawable.video14,R.drawable.vpaly));


        videoAdepter = new VideoAdepter(getContext(),videoList);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),3,RecyclerView.VERTICAL,false);
        rvVideo.setLayoutManager(layoutManager);
        rvVideo.setAdapter(videoAdepter);
    }


}