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
public interface CategoryInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<CategoryInfo> categoryInfo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(CategoryInfo categoryInfo);

    @Update
    void update(CategoryInfo categoryInfo);

    @Delete
    void delete(CategoryInfo categoryInfo);

    @Query("SELECT * FROM categoryInfo " +
            "WHERE category_id LIKE :categoryTitle")
    LiveData<List<CategoryInfo>> findCategoryInfo(String categoryTitle);
}
