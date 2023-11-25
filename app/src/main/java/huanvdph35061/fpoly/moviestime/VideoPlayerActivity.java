package huanvdph35061.fpoly.moviestime;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.request.RequestOptions;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import huanvdph35061.fpoly.moviestime.model.Movie;

public class VideoPlayerActivity extends AppCompatActivity {
    private PlayerView videoPlayer;
    private SimpleExoPlayer simpleExoPlayer;
    private static final String FILE_URL="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        videoPlayer = findViewById(R.id.movie_exo_player);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setUpExoPlayer(getIntent().getStringExtra("url"));
    }
    private void setUpExoPlayer(String url){
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this);
        videoPlayer.setPlayer(simpleExoPlayer);
        DataSource.Factory factory = new DefaultDataSourceFactory(this, Util.getUserAgent(this,"Movie Time!"));
        MediaSource mediaSource = new ExtractorMediaSource.Factory(factory).createMediaSource(Uri.parse(url));
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }
}