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
public interface ListOfMalfunctionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<ListOfMalfunctions> listOfMalfunctions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(ListOfMalfunctions listOfMalfunctions);

    @Update
    void update(ListOfMalfunctions listOfMalfunctions);

    @Delete
    void delete(ListOfMalfunctions listOfMalfunctions);

    @Query("SELECT * FROM list_of_malfunctions")
    LiveData<List<ListOfMalfunctions>> findListOfMalfunctions();
}
