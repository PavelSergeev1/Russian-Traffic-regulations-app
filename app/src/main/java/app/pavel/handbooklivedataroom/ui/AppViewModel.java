package app.pavel.handbooklivedataroom.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import app.pavel.handbooklivedataroom.data.MainProvisions;
import app.pavel.handbooklivedataroom.data.MainProvisionsDao;
import app.pavel.handbooklivedataroom.data.RoadMarking;
import app.pavel.handbooklivedataroom.data.RoadMarkingDao;
import app.pavel.handbooklivedataroom.data.RoadMarkingInfo;
import app.pavel.handbooklivedataroom.data.RoadMarkingInfoDao;
import app.pavel.handbooklivedataroom.data.TrafficRuleInfo;
import app.pavel.handbooklivedataroom.data.TrafficRules;
import app.pavel.handbooklivedataroom.data.TrafficRulesDao;
import app.pavel.handbooklivedataroom.data.TrafficRuleInfoDao;
import app.pavel.handbooklivedataroom.data.Database;
import app.pavel.handbooklivedataroom.data.Launch;
import app.pavel.handbooklivedataroom.data.LaunchDao;
import app.pavel.handbooklivedataroom.data.TrafficSigns;
import app.pavel.handbooklivedataroom.data.TrafficSignsDao;
import app.pavel.handbooklivedataroom.data.TrafficSignsInfo;
import app.pavel.handbooklivedataroom.data.TrafficSignsInfoDao;

public class AppViewModel extends AndroidViewModel {

    private LaunchDao launchDao;
    private TrafficRulesDao trafficRulesDao;
    private TrafficRuleInfoDao trafficRuleInfoDao;
    private TrafficSignsDao trafficSignsDao;
    private TrafficSignsInfoDao trafficSignsInfoDao;
    private RoadMarkingDao roadMarkingDao;
    private RoadMarkingInfoDao roadMarkingInfoDao;
    private MainProvisionsDao mainProvisionsDao;

    private ExecutorService executorService;

    public AppViewModel(@NonNull Application application) {
        super(application);

        launchDao = Database.getInstance(application).launchDao();
        trafficRulesDao = Database.getInstance(application).trafficRulesDao();
        trafficRuleInfoDao = Database.getInstance(application).trafficRuleInfoDao();
        trafficSignsDao = Database.getInstance(application).trafficSignsDao();
        trafficSignsInfoDao = Database.getInstance(application).trafficSignsInfoDao();
        roadMarkingDao = Database.getInstance(application).roadMarkingDao();
        roadMarkingInfoDao = Database.getInstance(application).roadMarkingInfoDao();
        mainProvisionsDao = Database.getInstance(application).mainProvisionsDao();

        executorService = Executors.newSingleThreadExecutor();
    }

    LiveData<List<Launch>> getAllLaunchItems() {
        return launchDao.findAll();
    }

    LiveData<List<TrafficRules>> getAllTrafficRules() {
        return trafficRulesDao.findAll();
    }

    LiveData<List<TrafficRuleInfo>> getTrafficRuleInfo(String ruleTitle) {
        return trafficRuleInfoDao.findTrafficRuleInfo(ruleTitle);
    }

    LiveData<List<TrafficSigns>> getAllTrafficSigns() {
        return trafficSignsDao.findAll();
    }

    LiveData<List<TrafficSignsInfo>> getTrafficSignsInfo(String signTitle) {
        return trafficSignsInfoDao.findTrafficSignsInfo(signTitle);
    }

    LiveData<List<RoadMarking>> getAllRoadMarking() {
        return roadMarkingDao.findAll();
    }

    LiveData<List<RoadMarkingInfo>> getRoadMarking(String roadMarking) {
        return roadMarkingInfoDao.findRoadMarkingInfo(roadMarking);
    }

    LiveData<List<MainProvisions>> getMainProvisions(String mainProvisions) {
        return mainProvisionsDao.findMainProvisions(mainProvisions);
    }

}