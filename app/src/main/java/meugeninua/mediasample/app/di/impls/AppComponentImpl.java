package meugeninua.mediasample.app.di.impls;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.ViewModelProvider;
import meugeninua.mediasample.BuildConfig;
import meugeninua.mediasample.app.di.AppComponent;
import meugeninua.mediasample.model.db.MediaDbHelper;
import meugeninua.mediasample.model.db.dao.MediaDao;
import meugeninua.mediasample.model.db.dao.impls.MediaDaoImpl;
import meugeninua.mediasample.model.db.mappers.impls.MediaMapperImpl;
import meugeninua.mediasample.model.network.MediaService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppComponentImpl implements AppComponent {

    private final Application app;
    private SQLiteDatabase database;
    private MediaDao mediaDao;
    private ViewModelProvider.Factory viewModelFactory;
    private OkHttpClient httpClient;
    private Gson gson;
    private Retrofit retrofit;
    private MediaService mediaService;
    private ExecutorService executorService;

    public AppComponentImpl(Application app) {
        this.app = app;
    }

    @Override
    public Application provideAppContext() {
        return app;
    }

    @Override
    public SQLiteDatabase provideDatabase() {
        if (database == null) {
            database = new MediaDbHelper(provideAppContext()).getWritableDatabase();
        }
        return database;
    }

    @Override
    public MediaDao provideMediaDao() {
        if (mediaDao == null) {
            mediaDao = new MediaDaoImpl(provideDatabase(), new MediaMapperImpl());
        }
        return mediaDao;
    }

    @Override
    public ViewModelProvider.Factory provideViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactoryImpl(this);
        }
        return viewModelFactory;
    }

    @Override
    public OkHttpClient provideHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder()
                    .build();
        }
        return httpClient;
    }

    @Override
    public Gson provideGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .create();
        }
        return gson;
    }

    @Override
    public Retrofit provideRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(provideHttpClient())
                    .baseUrl(BuildConfig.URL)
                    .addConverterFactory(GsonConverterFactory.create(provideGson()))
                    .build();
        }
        return retrofit;
    }

    @Override
    public MediaService provideMediaService() {
        if (mediaService == null) {
            mediaService = provideRetrofit()
                    .create(MediaService.class);
        }
        return mediaService;
    }

    @Override
    public ExecutorService provideExecutorService() {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(2);
        }
        return executorService;
    }
}
