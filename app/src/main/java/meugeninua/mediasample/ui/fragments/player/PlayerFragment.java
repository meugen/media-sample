package meugeninua.mediasample.ui.fragments.player;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import meugeninua.mediasample.R;
import meugeninua.mediasample.app.di.AppComponent;
import meugeninua.mediasample.ui.fragments.base.BaseFragment;
import meugeninua.mediasample.ui.fragments.player.binding.PlayerBinding;
import meugeninua.mediasample.ui.fragments.player.binding.PlayerBindingImpl;
import meugeninua.mediasample.ui.fragments.player.state.PlayerState;
import meugeninua.mediasample.ui.fragments.player.state.PlayerStateImpl;

public class PlayerFragment extends BaseFragment<PlayerState, PlayerBinding>
        implements LifecycleObserver {

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @Override
    public void onDestroy() {
        ProcessLifecycleOwner.get().getLifecycle().removeObserver(this);
        binding.releasePlayer();
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player,
                container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            binding.initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.hideSystemUi();
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M || !binding.isPlayerInitialized()) {
            binding.initializePlayer();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onProcessStop() {
        binding.releasePlayer();
    }

    @Override
    protected void inject(AppComponent component) {
        binding = new PlayerBindingImpl(getContext());
        state = new PlayerStateImpl();
    }
}
