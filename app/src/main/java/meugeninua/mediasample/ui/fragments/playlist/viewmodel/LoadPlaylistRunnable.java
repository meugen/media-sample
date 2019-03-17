package meugeninua.mediasample.ui.fragments.playlist.viewmodel;

import android.util.Log;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import meugeninua.mediasample.model.db.dao.MediaDao;
import meugeninua.mediasample.model.db.entities.MediaEntity;
import meugeninua.mediasample.model.network.MediaService;
import retrofit2.Response;

public class LoadPlaylistRunnable implements Runnable {

    private static final String TAG = LoadPlaylistRunnable.class.getSimpleName();

    private final WeakReference<MutableLiveData<List<MediaEntity>>> liveDataRef;

    private final MediaDao mediaDao;
    private final MediaService mediaService;

    public LoadPlaylistRunnable(
            final MutableLiveData<List<MediaEntity>> liveData,
            final MediaDao mediaDao, final MediaService mediaService) {
        this.liveDataRef = new WeakReference<>(liveData);
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
                for (MediaEntity entity : entities) {
                    mediaDao.insert(entity);
                }
                postToLiveData(entities);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
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
