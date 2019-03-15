package meugeninua.mediasample.ui.fragments.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import meugeninua.mediasample.app.MediaApp;
import meugeninua.mediasample.app.di.AppComponent;
import meugeninua.mediasample.ui.fragments.base.binding.Binding;
import meugeninua.mediasample.ui.fragments.base.state.State;

public abstract class BaseFragment<S extends State, B extends Binding<S>> extends Fragment {

    protected B binding;
    protected S state;

    @Override
    public void onAttach(Context context) {
        inject(MediaApp.provideAppComponent(context));
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state.attachBundle(savedInstanceState);
        binding.restoreState(state);
        state.detachBundle();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        state.attachBundle(outState);
        binding.saveState(state);
        state.detachBundle();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.attachView(view);
    }

    @Override
    public void onDestroyView() {
        binding.detachView();
        super.onDestroyView();
    }

    protected abstract void inject(final AppComponent component);
}
