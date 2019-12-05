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
public interface TrafficSignsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<TrafficSigns> trafficSigns);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(TrafficSigns trafficSigns);

    @Update
    void update(TrafficSigns trafficSigns);

    @Delete
    void delete(TrafficSigns trafficSigns);

    @Query("SELECT * FROM trafficSigns")
    LiveData<List<TrafficSigns>> findAll();
}
