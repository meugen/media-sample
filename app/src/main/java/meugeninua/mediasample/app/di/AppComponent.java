package meugeninua.mediasample.app.di;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.util.concurrent.ExecutorService;

import androidx.lifecycle.ViewModelProvider;
import meugeninua.mediasample.model.db.dao.MediaDao;
import meugeninua.mediasample.model.network.MediaService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public interface AppComponent {

    Context provideAppContext();

    SQLiteDatabase provideDatabase();

    MediaDao provideMediaDao();

    ViewModelProvider.Factory provideViewModelFactory();

    OkHttpClient provideHttpClient();

    Gson provideGson();

    Retrofit provideRetrofit();

    MediaService provideMediaService();

    ExecutorService provideExecutorService();
}
