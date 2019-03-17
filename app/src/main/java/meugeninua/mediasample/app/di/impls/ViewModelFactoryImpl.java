package meugeninua.mediasample.app.di.impls;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import meugeninua.mediasample.app.di.AppComponent;
import meugeninua.mediasample.app.di.Injectable;

class ViewModelFactoryImpl extends ViewModelProvider.NewInstanceFactory {

    private final AppComponent appComponent;

    ViewModelFactoryImpl(final AppComponent appComponent) {
        this.appComponent = appComponent;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        T viewModel = super.create(modelClass);
        if (viewModel instanceof Injectable) {
            ((Injectable) viewModel).inject(appComponent);
        }
        return viewModel;
    }
}
