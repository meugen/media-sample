package meugeninua.mediasample.ui.fragments.playlist.viewmodel;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import meugeninua.mediasample.model.db.dao.MediaDao;
import meugeninua.mediasample.model.db.entities.MediaEntity;
import meugeninua.mediasample.model.network.MediaService;
import retrofit2.Response;

class LoadPlaylistRunnable implements Runnable {

    private static final String TAG = LoadPlaylistRunnable.class.getSimpleName();

    private final WeakReference<MutableLiveData<List<MediaEntity>>> liveDataRef;

    private final SQLiteDatabase database;
    private final MediaDao mediaDao;
    private final MediaService mediaService;

    LoadPlaylistRunnable(
            final MutableLiveData<List<MediaEntity>> liveData,
            final SQLiteDatabase database,
            final MediaDao mediaDao, final MediaService mediaService) {
        this.liveDataRef = new WeakReference<>(liveData);
        this.database = database;
        this.mediaDao = mediaDao;
        this.mediaService = mediaService;
    }

    @Override
    public void run() {
        List<MediaEntity> entities = mediaDao.getAll();
        if (!postToLiveData(entities)) {
            return;
        }
        try {
            Response<List<MediaEntity>> response = mediaService.getMediaContent().execute();
            if (response.isSuccessful()) {
                entities = response.body();
                storeToDatabase(entities);
                postToLiveData(entities);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    private void storeToDatabase(final List<MediaEntity> entities) {
        try {
            database.beginTransaction();

            mediaDao.deleteAll();
            for (MediaEntity entity : entities) {
                mediaDao.insert(entity);
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    private boolean postToLiveData(final List<MediaEntity> entities) {
        MutableLiveData<List<MediaEntity>> liveData = liveDataRef.get();
        if (liveData == null) {
            return false;
        }
        liveData.postValue(entities);
        return true;
    }
}
