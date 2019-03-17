package meugeninua.mediasample.model.network;

import java.util.List;

import meugeninua.mediasample.model.db.entities.MediaEntity;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MediaService {

    @GET("/player/content.json")
    Call<List<MediaEntity>> getMediaContent();
}
