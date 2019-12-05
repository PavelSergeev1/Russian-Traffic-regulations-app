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
public interface RoadMarkingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<RoadMarking> roadMarkings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(RoadMarking roadMarkings);

    @Update
    void update(RoadMarking roadMarkings);

    @Delete
    void delete(RoadMarking roadMarkings);

    @Query("SELECT * FROM road_marking")
    LiveData<List<RoadMarking>> findAll();
}
