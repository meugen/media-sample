package meugeninua.mediasample.ui.fragments.player.binding;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import meugeninua.mediasample.R;
import meugeninua.mediasample.ui.fragments.base.binding.BaseBindingImpl;
import meugeninua.mediasample.ui.fragments.player.state.PlayerState;

public class PlayerBindingImpl extends BaseBindingImpl<PlayerState>
        implements PlayerBinding {

    private final Context context;
    private ExoPlayer player;

    private long currentPosition;
    private int currentWindowIndex;
    private boolean playWhenReady;

    public PlayerBindingImpl(Context context) {
        this.context = context;
    }

    @Override
    public void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                context,
                new DefaultRenderersFactory(context),
                new DefaultTrackSelector(),
                new DefaultLoadControl());

        PlayerView playerView = get(R.id.video_view);
        playerView.setPlayer(player);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindowIndex, currentPosition);

        Uri uri = Uri.parse(context.getString(R.string.media_url_mp4));
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, false, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory factory = new DefaultHttpDataSourceFactory("exoplayer");
        return new ExtractorMediaSource.Factory(factory).createMediaSource(uri);
    }

    @Override
    public boolean isPlayerInitialized() {
        return player != null;
    }

    @Override
    public void hideSystemUi() {
        PlayerView playerView = get(R.id.video_view);
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void releasePlayer() {
         if (player != null) {
             currentPosition = player.getCurrentPosition();
             currentWindowIndex = player.getCurrentWindowIndex();
             playWhenReady = player.getPlayWhenReady();
             player.release();
             player = null;
         }
    }

    @Override
    public void saveState(PlayerState state) {
        state.setCurrentPosition(currentPosition);
        state.setCurrentWindowIndex(currentWindowIndex);
        state.setPlayWhenReady(playWhenReady);
    }

    @Override
    public void restoreState(PlayerState state) {
        currentPosition = state.getCurrentPosition();
        currentWindowIndex = state.getCurrentWindowIndex();
        playWhenReady = state.getPlayWhenReady();
    }
}
