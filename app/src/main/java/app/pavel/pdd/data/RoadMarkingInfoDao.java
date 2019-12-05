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
public interface RoadMarkingInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<RoadMarkingInfo> roadMarkingInfo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(RoadMarkingInfo roadMarkingInfo);

    @Update
    void update(RoadMarkingInfo roadMarkingInfo);

    @Delete
    void delete(RoadMarkingInfo roadMarkingInfo);

    @Query("SELECT * FROM road_marking_info " +
            "WHERE category_id LIKE :roadMarkingTitle")
    LiveData<List<RoadMarkingInfo>> findRoadMarkingInfo(String roadMarkingTitle);
}
