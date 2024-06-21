package app.myecomms.a1xvideo.adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.myecomms.a1xvideo.R;
import app.myecomms.a1xvideo.modals.Shayari;

public class ShayariAdepter extends RecyclerView.Adapter<ShayariAdepter.MyViewHolder> {
    private final Context context;
    private ArrayList<Shayari> shayariList = new ArrayList<>();
    private RecyclerViewClickListener listener;

    public ShayariAdepter(Context context, ArrayList<Shayari> shayariList, RecyclerViewClickListener listener) {
        this.context = context;
        this.shayariList = shayariList;
        this.listener = listener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shayari, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String shayari = shayariList.get(position).Fname;
        holder.txtHindi.setText(shayari);

    }

    @Override
    public int getItemCount() {

        return shayariList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView txtHindi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHindi = itemView.findViewById(R.id.txt_hindi);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());

        }
    }
}
