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
public interface MainProvisionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<MainProvisions> mainProvisions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(MainProvisions mainProvisions);

    @Update
    void update(MainProvisions mainProvisions);

    @Delete
    void delete(MainProvisions mainProvisions);

    @Query("SELECT * FROM main_provisions")
    LiveData<List<MainProvisions>> findMainProvisions();
}
