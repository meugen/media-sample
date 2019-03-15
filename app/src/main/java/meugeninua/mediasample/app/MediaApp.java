package meugeninua.mediasample.app;

import android.app.Application;
import android.content.Context;

import meugeninua.mediasample.app.di.AppComponent;
import meugeninua.mediasample.app.di.impls.AppComponentImpl;

public class MediaApp extends Application {

    public static AppComponent provideAppComponent(final Context context) {
        MediaApp app = (MediaApp) context.getApplicationContext();
        if (app.appComponent == null) {
            app.appComponent = new AppComponentImpl(app);
        }
        return app.appComponent;
    }

    private AppComponent appComponent;
}
