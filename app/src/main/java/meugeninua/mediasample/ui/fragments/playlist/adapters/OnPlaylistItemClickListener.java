package meugeninua.mediasample.ui.fragments.playlist.adapters;

import meugeninua.mediasample.model.db.entities.MediaEntity;

public interface OnPlaylistItemClickListener {

    void onPlaylistItemClick(MediaEntity entity, int position);
}
