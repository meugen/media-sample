package meugeninua.mediasample.model.db;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MediaDbHelper extends SQLiteOpenHelper {

    private static final String NAME = "media";
    private static final int VERSION = 1;

    private final AssetManager assets;
    private Pattern pattern;

    public MediaDbHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.assets = context.getAssets();
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        onUpgrade(db, 0, VERSION);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        pattern = Pattern.compile("([^;]+);");
        try {
            for (int version = oldVersion + 1; version <= newVersion; version++) {
                upgrade(db, version);
            }
        } finally {
            pattern = null;
        }
    }

    private void upgrade(SQLiteDatabase db, int version) {
        Matcher matcher = pattern.matcher(fetchSqlByVersion(version));
        while (matcher.find()) {
            db.execSQL(matcher.group(1));
        }
    }

    private CharSequence fetchSqlByVersion(int version) {
        try {
            Reader reader = null;
            try {
                String fileName = "db/" + NAME + "/" + version + ".sql";
                reader = new BufferedReader(new InputStreamReader(assets.open(fileName)));

                StringBuilder builder = new StringBuilder();
                char[] buf = new char[256];
                while (true) {
                    int count = reader.read(buf);
                    if (count < 0) {
                        break;
                    }
                    builder.append(buf, 0, count);
                }
                return builder;
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
