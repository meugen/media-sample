package meugeninua.mediasample.ui.fragments.playlist.binding;

import java.util.List;

import meugeninua.mediasample.model.db.entities.MediaEntity;
import meugeninua.mediasample.ui.fragments.base.binding.Binding;
import meugeninua.mediasample.ui.fragments.base.state.State;

public interface PlaylistBinding extends Binding<State> {

    void setupRecycler();

    void setPlaylist(List<MediaEntity> entities);
}
