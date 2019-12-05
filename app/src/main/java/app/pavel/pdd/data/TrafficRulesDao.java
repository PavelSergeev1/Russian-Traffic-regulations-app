package app.pavel.pdd.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TrafficRulesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<TrafficRules> trafficRules);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(TrafficRules trafficRules);

    @Update
    void update(TrafficRules trafficRules);

    @Delete
    void delete(TrafficRules trafficRules);

    @Query("SELECT * FROM trafficRules")
    LiveData<List<TrafficRules>> findAll();
}
