package meugeninua.mediasample.ui.fragments.player.binding;

import meugeninua.mediasample.ui.fragments.base.binding.Binding;
import meugeninua.mediasample.ui.fragments.player.state.PlayerState;

public interface PlayerBinding extends Binding<PlayerState> {

    void initializePlayer();

    boolean isPlayerInitialized();

    void hideSystemUi();

    void releasePlayer();
}
