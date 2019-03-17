package meugeninua.mediasample.model.db.mappers.impls;

import android.content.ContentValues;
import android.database.Cursor;

import meugeninua.mediasample.model.db.entities.MediaEntity;
import meugeninua.mediasample.model.db.mappers.EntityMapper;

public class MediaMapperImpl implements EntityMapper<MediaEntity> {

    private static final String FLD_ID = "id";
    private static final String FLD_NAME = "name";
    private static final String FLD_DESCRIPTION = "description";
    private static final String FLD_URL = "url";

    @Override
    public MediaEntity cursorToEntity(final Cursor cursor) {
        MediaEntity entity = new MediaEntity();
        entity.id = cursor.getInt(cursor.getColumnIndexOrThrow(FLD_ID));
        entity.name = cursor.getString(cursor.getColumnIndexOrThrow(FLD_NAME));
        entity.description = cursor.getString(cursor.getColumnIndexOrThrow(FLD_DESCRIPTION));
        entity.url = cursor.getString(cursor.getColumnIndexOrThrow(FLD_URL));
        return entity;
    }

    @Override
    public ContentValues entityToValues(final MediaEntity entity) {
        ContentValues values = new ContentValues();
        values.put(FLD_ID, entity.id);
        values.put(FLD_NAME, entity.name);
        values.put(FLD_DESCRIPTION, entity.description);
        values.put(FLD_URL, entity.url);
        return values;
    }
}
