package meugeninua.mediasample.ui.fragments.playlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import meugeninua.mediasample.R;
import meugeninua.mediasample.app.di.AppComponent;
import meugeninua.mediasample.model.db.entities.MediaEntity;
import meugeninua.mediasample.ui.fragments.base.BaseFragment;
import meugeninua.mediasample.ui.fragments.base.state.State;
import meugeninua.mediasample.ui.fragments.playlist.binding.PlaylistBinding;
import meugeninua.mediasample.ui.fragments.playlist.binding.PlaylistBindingImpl;
import meugeninua.mediasample.ui.fragments.playlist.viewmodel.PlaylistViewModel;

public class PlaylistFragment extends BaseFragment<State, PlaylistBinding> {

    private PlaylistViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playlist,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setupRecycler();
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getMediaEntities().observe(this, binding::setPlaylist);
    }

    @Override
    public void inject(final AppComponent component) {
        state = State.EMPTY;
        binding = new PlaylistBindingImpl(getContext());
        viewModel = getViewModel(component.provideViewModelFactory(),
                PlaylistViewModel.class);
    }
}
