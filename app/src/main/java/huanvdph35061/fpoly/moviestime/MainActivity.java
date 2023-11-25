package huanvdph35061.fpoly.moviestime;

import static huanvdph35061.fpoly.moviestime.utils.DataSource.getPhimMoi;

import android.app.ActivityOptions;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import huanvdph35061.fpoly.moviestime.Adapter.MovieAdapter;
import huanvdph35061.fpoly.moviestime.Adapter.SearchAdapter;
import huanvdph35061.fpoly.moviestime.Adapter.SliderAdapter;
import huanvdph35061.fpoly.moviestime.model.Movie;
import huanvdph35061.fpoly.moviestime.model.MovieItemClickListener;
import huanvdph35061.fpoly.moviestime.model.Slide;
import huanvdph35061.fpoly.moviestime.utils.DataSource;


public class MainActivity extends AppCompatActivity implements MovieItemClickListener {

    private ViewPager slidePager;
    private TabLayout indicator;

    private RecyclerView RvPhimMoi,RvPhimKinhDi,RvPhimAnime;

    private TabLayout categoryTab;

    List<Slide> homeBannerList;

    private Toolbar toolbar;

    private SearchAdapter searchAdapter;

    private SearchView searchView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slidePager = findViewById(R.id.slide_pager);
        indicator = findViewById(R.id.indicator);
        RvPhimMoi = findViewById(R.id.RcPhimMoi);
        RvPhimKinhDi = findViewById(R.id.RcPhimKinhDi);
        RvPhimAnime = findViewById(R.id.RcPhimAnime);
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);




        iniSlider();

        iniPhimMoi();
        iniPhimKinhDi();
        iniPhimAnime();

    }

    private void iniPhimMoi() {

        MovieAdapter movieAdapter = new MovieAdapter(this, getPhimMoi(),this);
        RvPhimMoi.setAdapter(movieAdapter);
        RvPhimMoi.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void iniPhimKinhDi(){
        MovieAdapter movieAdapter = new MovieAdapter(this, DataSource.getPhimKinhDi(),this);
        RvPhimKinhDi.setAdapter(movieAdapter);
        RvPhimKinhDi.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }
    private void iniPhimAnime(){
        MovieAdapter movieAdapter = new MovieAdapter(this, DataSource.getPhimAnime(),this);
        RvPhimAnime.setAdapter(movieAdapter);
        RvPhimAnime.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void iniSearch(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RvPhimMoi.setLayoutManager(linearLayoutManager);

        searchAdapter = new SearchAdapter(getPhimMoi());
        RvPhimMoi.setAdapter(searchAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        RvPhimMoi.addItemDecoration(itemDecoration);
    }


    private void iniSlider() {
        homeBannerList = new ArrayList<>();
        homeBannerList.add(new Slide(R.drawable.slide1,"Slide Title / more text here"));
        homeBannerList.add(new Slide(R.drawable.slide2,"Slide Title / more text here"));
        homeBannerList.add(new Slide(R.drawable.slide1,"Slide Title / more text here"));
        homeBannerList.add(new Slide(R.drawable.slide2,"Slide Title / more text here"));

        SliderAdapter adapter = new SliderAdapter(this,homeBannerList);
        slidePager.setAdapter(adapter);


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTime(),4000,6000);
        indicator.setupWithViewPager(slidePager,true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL",movie.getThumb());
        intent.putExtra("imgCover",movie.getCoverPhoto());
        intent.putExtra("desc",movie.getDes());
        intent.putExtra("url",movie.getStreamLink());


        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,movieImageView,"sharedName");
        startActivity(intent,options.toBundle());

    }

    class SliderTime extends TimerTask{

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (slidePager.getCurrentItem()<homeBannerList.size()-1){
                        slidePager.setCurrentItem(slidePager.getCurrentItem()+1);
                    }else {
                        slidePager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}