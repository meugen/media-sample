package meugeninua.mediasample.ui.fragments.playlist.binding;

import android.content.Context;

import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import meugeninua.mediasample.R;
import meugeninua.mediasample.model.db.entities.MediaEntity;
import meugeninua.mediasample.ui.fragments.base.binding.BaseBindingImpl;
import meugeninua.mediasample.ui.fragments.base.state.State;
import meugeninua.mediasample.ui.fragments.playlist.adapters.PlaylistAdapter;

public class PlaylistBindingImpl extends BaseBindingImpl<State>
        implements PlaylistBinding {

    private final Context context;
    private PlaylistAdapter adapter;

    public PlaylistBindingImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void setupRecycler() {
        RecyclerView recyclerView = get(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL));
        adapter = new PlaylistAdapter(context);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setPlaylist(final List<MediaEntity> entities) {
        adapter.swapEntities(entities);
    }

    @Override
    public void saveState(final State state) { }

    @Override
    public void restoreState(final State state) { }
}
