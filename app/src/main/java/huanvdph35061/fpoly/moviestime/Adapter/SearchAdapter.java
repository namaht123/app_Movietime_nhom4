package huanvdph35061.fpoly.moviestime.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import huanvdph35061.fpoly.moviestime.R;
import huanvdph35061.fpoly.moviestime.model.Movie;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable {

    List<Movie> movieList;
    List<Movie> movieListOld;


    public SearchAdapter(List<Movie> movieList) {
        this.movieList = movieList;
        this.movieListOld = movieListOld;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        if (movie == null){
            return;
        }
        holder.imageSearch.setImageResource(movie.getThumb());
        holder.txt_Title.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        if (movieList != null){
            return movieList.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()){
                    movieList = movieListOld;
                }else {
                    List<Movie> list = new ArrayList<>();
                    for (Movie movie: movieListOld){
                        if (movie.getTitle().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(movie);
                        }
                    }
                    movieList = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = movieList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                movieList = (List<Movie>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageSearch;
        private TextView txt_Title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSearch = itemView.findViewById(R.id.img_poster);
            txt_Title = itemView.findViewById(R.id.txt_Title);
        }
    }
}
