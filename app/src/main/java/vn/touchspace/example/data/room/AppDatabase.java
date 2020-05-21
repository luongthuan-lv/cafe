package vn.touchspace.example.data.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import vn.touchspace.example.data.room.dao.UserDao;
import vn.touchspace.example.data.room.model.User;
import vn.touchspace.example.utils.sqliteopenhelper.AssetSQLiteOpenHelperFactory;

/**
 * Created by GNUD on 02/03/2018.
 */

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "dbname.db";

    private static volatile AppDatabase INSTANCE;

    public abstract UserDao mUserDao();

    public static AppDatabase getInstance(Context context, String dbName) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, dbName)
                            //.openHelperFactory(new AssetSQLiteOpenHelperFactory())
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
