package app.pavel.handbooklivedataroom.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TrafficSignsInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<TrafficSignsInfo> trafficSignsInfo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(TrafficSignsInfo trafficSignsInfo);

    @Update
    void update(TrafficSignsInfo trafficSignsInfo);

    @Delete
    void delete(TrafficSignsInfo trafficSignsInfo);

    @Query("SELECT * FROM trafficSignsInfo " +
            "WHERE signTitle LIKE :signsTitle")
    LiveData<List<TrafficSignsInfo>> findTrafficSignsInfo(String signsTitle);
}
