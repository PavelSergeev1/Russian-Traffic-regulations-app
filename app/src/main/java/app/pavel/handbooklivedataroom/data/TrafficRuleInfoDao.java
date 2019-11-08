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
public interface TrafficRuleInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<TrafficRuleInfo> trafficRuleInfo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(TrafficRuleInfo trafficRuleInfo);

    @Update
    void update(TrafficRuleInfo trafficRuleInfo);

    @Delete
    void delete(TrafficRuleInfo trafficRuleInfo);

    @Query("SELECT * FROM trafficRuleInfo " +
            "WHERE category_id LIKE :ruleTitle")
    LiveData<List<TrafficRuleInfo>> findTrafficRuleInfo(String ruleTitle);
}
