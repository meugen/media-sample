package meugeninua.mediasample.model.db.dao;

import java.util.List;

import meugeninua.mediasample.model.db.entities.MediaEntity;

public interface MediaDao {

    List<MediaEntity> getAll();

    void insert(MediaEntity entity);
}
