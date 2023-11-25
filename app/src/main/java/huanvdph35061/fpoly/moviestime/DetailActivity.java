package huanvdph35061.fpoly.moviestime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import huanvdph35061.fpoly.moviestime.Adapter.CastAdapter;
import huanvdph35061.fpoly.moviestime.model.Cast;

public class DetailActivity extends AppCompatActivity {

    private ImageView MovieThumbImg,MovieCoverImg;
    private String url;

    private TextView txtTitle, txtDes;

    private FloatingActionButton play_fab;
    CastAdapter castAdapter;
    private RecyclerView recyclerCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        recyclerCast = findViewById(R.id.RcvCast);

        iniViews();
        setupRvCast();
    }
    private void iniViews(){
        play_fab = findViewById(R.id.play_fab);
        String movieTitle = getIntent().getExtras().getString("title");
        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        int imageCover = getIntent().getExtras().getInt("imgCover");
        String movieDes = getIntent().getExtras().getString("desc");
        MovieThumbImg = findViewById(R.id.detail_movie_img);
        MovieThumbImg.setImageResource(imageResourceId);
        MovieCoverImg = findViewById(R.id.detail_movie_cover);
        Glide.with(this).load(imageCover).into(MovieCoverImg);
        txtTitle = findViewById(R.id.detail_movie_title);
        txtTitle.setText(movieTitle);
        txtDes = findViewById(R.id.detail_movie_desc);
        txtDes.setText(movieDes);
        url = getIntent().getStringExtra("url");

        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, VideoPlayerActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });

    }
    private void setupRvCast(){
        List<Cast> mData = new ArrayList<>();
        mData.add(new Cast("name",R.drawable.cast1));
        mData.add(new Cast("name",R.drawable.cast2));
        mData.add(new Cast("name",R.drawable.cast3));
        mData.add(new Cast("name",R.drawable.cast4));
        mData.add(new Cast("name",R.drawable.cast5));
        castAdapter = new CastAdapter(this,mData);
        recyclerCast .setAdapter(castAdapter);
        recyclerCast.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }
}