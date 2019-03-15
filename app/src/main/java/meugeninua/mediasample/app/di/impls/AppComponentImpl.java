package meugeninua.mediasample.app.di.impls;

import android.app.Application;

import meugeninua.mediasample.app.di.AppComponent;

public class AppComponentImpl implements AppComponent {

    private final Application app;

    public AppComponentImpl(Application app) {
        this.app = app;
    }

    @Override
    public Application provideAppContext() {
        return app;
    }
}
