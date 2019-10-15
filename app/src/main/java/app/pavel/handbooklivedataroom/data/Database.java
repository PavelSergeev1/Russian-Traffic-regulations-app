package app.pavel.handbooklivedataroom.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

//@Database(entities = {Category.class}, version = 1)
@androidx.room.Database(entities = {Launch.class, Category.class, CategoryInfo.class},
        version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database INSTANCE;
    public abstract LaunchDao launchDao();
    public abstract CategoryDao categoryDao();
    public abstract CategoryInfoDao categoryInfoDao();
    private static final Object sLock = new Object();

    public static Database getInstance(final Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        Database.class, "Database.db")
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }

}