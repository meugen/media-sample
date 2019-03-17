package meugeninua.mediasample.ui.fragments.playlist.viewmodel;

import java.util.List;
import java.util.concurrent.ExecutorService;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import meugeninua.mediasample.app.di.AppComponent;
import meugeninua.mediasample.app.di.Injectable;
import meugeninua.mediasample.model.db.dao.MediaDao;
import meugeninua.mediasample.model.db.entities.MediaEntity;
import meugeninua.mediasample.model.network.MediaService;

public class PlaylistViewModel extends ViewModel implements Injectable {

    private MutableLiveData<List<MediaEntity>> liveData;

    private ExecutorService executorService;
    private MediaDao mediaDao;
    private MediaService mediaService;

    public LiveData<List<MediaEntity>> getMediaEntities() {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
            loadMediaEntities();
        }
        return liveData;
    }

    private void loadMediaEntities() {
        executorService.execute(new LoadPlaylistRunnable(liveData, mediaDao, mediaService));
    }

    @Override
    public void inject(final AppComponent appComponent) {
        this.executorService = appComponent.provideExecutorService();
        this.mediaDao = appComponent.provideMediaDao();
        this.mediaService = appComponent.provideMediaService();
    }
}
