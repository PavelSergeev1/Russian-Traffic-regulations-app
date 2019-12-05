package app.pavel.pdd.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Launch.class, TrafficRules.class, TrafficRuleInfo.class,
    TrafficSigns.class, TrafficSignsInfo.class, RoadMarking.class, RoadMarkingInfo.class,
    MainProvisions.class, ListOfMalfunctions.class},
        version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database INSTANCE;

    public abstract LaunchDao launchDao();
    public abstract TrafficRulesDao trafficRulesDao();
    public abstract TrafficRuleInfoDao trafficRuleInfoDao();
    public abstract TrafficSignsDao trafficSignsDao();
    public abstract TrafficSignsInfoDao trafficSignsInfoDao();
    public abstract RoadMarkingDao roadMarkingDao();
    public abstract RoadMarkingInfoDao roadMarkingInfoDao();
    public abstract MainProvisionsDao mainProvisionsDao();
    public abstract ListOfMalfunctionsDao listOfMalfunctionsDao();

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