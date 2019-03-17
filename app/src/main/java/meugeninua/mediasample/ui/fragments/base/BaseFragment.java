package meugeninua.mediasample.ui.fragments.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import meugeninua.mediasample.app.MediaApp;
import meugeninua.mediasample.app.di.AppComponent;
import meugeninua.mediasample.app.di.Injectable;
import meugeninua.mediasample.ui.fragments.base.binding.Binding;
import meugeninua.mediasample.ui.fragments.base.state.State;

public abstract class BaseFragment<S extends State, B extends Binding<S>> extends Fragment
        implements Injectable {

    protected B binding;
    protected S state;

    private boolean injected = false;

    @Override
    public void onAttach(Context context) {
        if (!injected) {
            // If setRetainInstance(boolean) was called with true argument for
            // this fragment, than it can be already injected
            inject(MediaApp.provideAppComponent(context));
            injected = true;
        }
        super.onAttach(context);
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

        state.attachBundle(savedInstanceState);
        binding.restoreState(state);
        state.detachBundle();
    }

    @Override
    public void onDestroyView() {
        binding.detachView();
        super.onDestroyView();
    }

    protected <VM extends ViewModel> VM getViewModel(
            ViewModelProvider.Factory factory, Class<VM> clazz) {
        return ViewModelProviders.of(this, factory).get(clazz);
    }
}
