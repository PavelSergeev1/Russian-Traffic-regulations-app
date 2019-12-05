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
public interface LaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<Launch> launch);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Launch launch);

    @Update
    void update(Launch launch);

    @Delete
    void delete(Launch launch);

    @Query("SELECT * FROM launch")
    LiveData<List<Launch>> findAll();
}