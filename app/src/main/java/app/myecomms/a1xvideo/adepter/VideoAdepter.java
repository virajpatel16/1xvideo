package app.myecomms.a1xvideo.adepter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.myecomms.a1xvideo.R;
import app.myecomms.a1xvideo.Video2Activity;
import app.myecomms.a1xvideo.modals.Video;

public class VideoAdepter extends RecyclerView.Adapter<VideoAdepter.MyViewHolder> {
    private Context context;

    private ArrayList<Video> videoList = new ArrayList<>();

    public VideoAdepter(Context context, ArrayList<Video> videoList) {
        this.context = context;
        this.videoList = videoList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new VideoAdepter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        Video videos = videoList.get(position);

        Glide.with(context).load(videos.video).into(holder.video);
        Glide.with(context).load(videos.paly).into(holder.vplay);
        holder.video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (position) {
                    case 0:
                        playVideo("https://packaged-media.redd.it/suwzwmo8915d1/pb/m2-res_640p.mp4?m=DASHPlaylist.mpd&v=1&e=1717747200&s=492a8c1722fdca6e89785038aebb10a1f4239da4#t=0");
                        break;
                    case 1:
                        playVideo("https://packaged-media.redd.it/8k9aqozrx05d1/pb/m2-res_638p.mp4?m=DASHPlaylist.mpd&v=1&e=1717743600&s=6837521201bf63d903da64ba1d19b2dceca6d85f#t=0");
                        break;
                    case 2:
                        playVideo("https://packaged-media.redd.it/srd0y3tydz4d1/pb/m2-res_720p.mp4?m=DASHPlaylist.mpd&v=1&e=1717736400&s=5c4b31d936e7f346f74868b4505e5cb1dd96aafb#t=0");
                        break;
                    case 3:
                        playVideo("https://packaged-media.redd.it/sprl82s87czc1/pb/m2-res_720p.mp4?m=DASHPlaylist.mpd&v=1&e=1715263200&s=c61df0da1b616ad60b55f1eddac1447bce56d85c#t=0");
                        break;
                    case 4:
                        playVideo("https://media.istockphoto.com/id/1265220754/video/mock-up-fantasy-rpg-moba-video-game-gameplay-with-role-playing-personage-doing-animated-magic.mp4?s=mp4-640x640-is&k=20&c=n3PvOni4Nz7puhQt0D1gw7R4EDmd9Lkb8lEh0vQtI54=");
                        break;
                    case 5:
                        playVideo("https://packaged-media.redd.it/nwa001sjvp4d1/pb/m2-res_1920p.mp4?m=DASHPlaylist.mpd&v=1&e=1717599600&s=06a3137c419da5ffbe4d27ed416edfe45392ce65#t=0");
                        break;
                    case 6:
                        playVideo("https://packaged-media.redd.it/7867kpwusn4d1/pb/m2-res_1350p.mp4?m=DASHPlaylist.mpd&v=1&e=1717599600&s=6909b5d433602e1c9d4d890220145c28e747afe7#t=0");
                        break;
                    case 7:
                        playVideo("https://packaged-media.redd.it/jy4upsydrq4d1/pb/m2-res_576p.mp4?m=DASHPlaylist.mpd&v=1&e=1717664400&s=95e56f72530a9f8906a8492a3bd17822ed0cc926#t=0");
                        break;
                    case 8:
                        playVideo("https://packaged-media.redd.it/3nwh9b6hcq4d1/pb/m2-res_360p.mp4?m=DASHPlaylist.mpd&v=1&e=1717599600&s=ac4072b5c949c346d78d8356baf07e5a563e8ce5#t=0");
                        break;
                    case 9:
                        playVideo("https://packaged-media.redd.it/3bpc4h3rco4d1/pb/m2-res_534p.mp4?m=DASHPlaylist.mpd&v=1&e=1717675200&s=7fc7b278826a20202d7f0cb48b25eb6311ca2071#t=0");
                        break;
                    case 10:
                        playVideo("https://packaged-media.redd.it/d3r4q4gzck4d1/pb/m2-res_1080p.mp4?m=DASHPlaylist.mpd&v=1&e=1717689600&s=9a6cc0f3193458320b4f46121fa8a71868450ad7#t=0");
                        break;
                    case 11:
                        playVideo("https://packaged-media.redd.it/ekjcifyz4p4d1/pb/m2-res_720p.mp4?m=DASHPlaylist.mpd&v=1&e=1717743600&s=a5358594650959f5991c4859ce64fd786f7a913e#t=0");
                        break;
                    case 12:
                        playVideo("https://packaged-media.redd.it/vqrgtz3oea4d1/pb/m2-res_854p.mp4?m=DASHPlaylist.mpd&v=1&e=1717740000&s=3833e1f4b612623b69e20d926bd4cdee6f29e06e#t=0");
                        break;
                    case 13:
                        playVideo("https://packaged-media.redd.it/icd2ch7hyz4d1/pb/m2-res_1920p.mp4?m=DASHPlaylist.mpd&v=1&e=1717750800&s=403109ac769a3c0e76145dba510f854cf4cd32c2#t=0");
                        break;
                    case 14:
                        playVideo("https://packaged-media.redd.it/gcfchxglwi4d1/pb/m2-res_576p.mp4?m=DASHPlaylist.mpd&v=1&e=1717743600&s=4459fe10d78b80b44c86715adb99ffc525b62216#t=0");
                        break;
                    // Add more cases as needed for additional videos
                }
            }
        });


    }

    private void playVideo(String videoUrl) {
        Intent intent = new Intent(context, Video2Activity.class);
        intent.putExtra("video_url", videoUrl);
        context.startActivity(intent);

    }

    @Override
    public int getItemCount() {

        return videoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView video, vplay;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            video = itemView.findViewById(R.id.video);
            vplay = itemView.findViewById(R.id.vplay);
        }


    }
}
