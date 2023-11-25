package huanvdph35061.fpoly.moviestime.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import huanvdph35061.fpoly.moviestime.R;
import huanvdph35061.fpoly.moviestime.model.Cast;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    Context context;
    List<Cast> mData;

    public CastAdapter(Context context, List<Cast> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CastAdapter.CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cast,parent,false);
        return new CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastAdapter.CastViewHolder holder, int position) {
        Glide.with(context).load(mData.get(position).getImg_link()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_cast);
        }
    }
}
