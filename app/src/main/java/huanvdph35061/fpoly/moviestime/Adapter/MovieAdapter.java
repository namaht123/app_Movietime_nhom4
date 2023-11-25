package huanvdph35061.fpoly.moviestime.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import huanvdph35061.fpoly.moviestime.R;
import huanvdph35061.fpoly.moviestime.model.Movie;
import huanvdph35061.fpoly.moviestime.model.MovieItemClickListener;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> mData;

    MovieItemClickListener movieItemClickListener;

    public MovieAdapter(Context context, List<Movie> mData,MovieItemClickListener listener) {
        this.context = context;
        this.mData = mData;
        movieItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtTitle.setText(mData.get(position).getTitle());
        holder.ImgMovie.setImageResource(mData.get(position).getThumb());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle;
        private ImageView ImgMovie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.item_movie_title);
            ImgMovie = itemView.findViewById(R.id.item_movie_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()),ImgMovie);
                }
            });

        }
    }
}
