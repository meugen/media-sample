package meugeninua.mediasample.model.db.dao.impls;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import meugeninua.mediasample.model.db.dao.MediaDao;
import meugeninua.mediasample.model.db.entities.MediaEntity;
import meugeninua.mediasample.model.db.mappers.EntityMapper;

public class MediaDaoImpl implements MediaDao {

    private final SQLiteDatabase db;
    private final EntityMapper<MediaEntity> mapper;

    public MediaDaoImpl(
            SQLiteDatabase db,
            EntityMapper<MediaEntity> mapper) {
        this.db = db;
        this.mapper = mapper;
    }

    @Override
    public List<MediaEntity> getAll() {
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM medias", new String[0]);

            List<MediaEntity> result = new ArrayList<>(cursor.getCount());
            while (cursor.moveToNext()) {
                result.add(mapper.cursorToEntity(cursor));
            }
            return result;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void insert(final MediaEntity entity) {
        db.insertOrThrow("medias", null, mapper.entityToValues(entity));
    }

    @Override
    public int deleteAll() {
        return db.delete("medias", null, null);
    }
}
