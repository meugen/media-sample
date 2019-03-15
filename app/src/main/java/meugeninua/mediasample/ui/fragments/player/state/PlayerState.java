package meugeninua.mediasample.ui.fragments.player.state;

import meugeninua.mediasample.ui.fragments.base.state.State;

public interface PlayerState extends State {

    String ARG_CURRENT_POSITION = "current_position";
    String ARG_CURRENT_WINDOW_INDEX = "current_window_index";
    String ARG_PLAY_WHEN_READY = "play_when_ready";

    long getCurrentPosition();

    void setCurrentPosition(long position);

    int getCurrentWindowIndex();

    void setCurrentWindowIndex(int index);

    boolean getPlayWhenReady();

    void setPlayWhenReady(boolean playWhenReady);
}
