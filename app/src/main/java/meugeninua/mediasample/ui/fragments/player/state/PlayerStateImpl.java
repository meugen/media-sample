package meugeninua.mediasample.ui.fragments.player.state;

import meugeninua.mediasample.ui.fragments.base.state.BaseStateImpl;

public class PlayerStateImpl extends BaseStateImpl
        implements PlayerState {

    @Override
    public long getCurrentPosition() {
        return bundle.getLong(ARG_CURRENT_POSITION);
    }

    @Override
    public void setCurrentPosition(long position) {
        bundle.putLong(ARG_CURRENT_POSITION, position);
    }

    @Override
    public int getCurrentWindowIndex() {
        return bundle.getInt(ARG_CURRENT_WINDOW_INDEX);
    }

    @Override
    public void setCurrentWindowIndex(int index) {
        bundle.putInt(ARG_CURRENT_WINDOW_INDEX, index);
    }

    @Override
    public boolean getPlayWhenReady() {
        return bundle.getBoolean(ARG_PLAY_WHEN_READY, true);
    }

    @Override
    public void setPlayWhenReady(boolean playWhenReady) {
        bundle.putBoolean(ARG_PLAY_WHEN_READY, playWhenReady);
    }
}
