package app.pavel.pdd.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import app.pavel.pdd.data.ListOfMalfunctions;
import app.pavel.pdd.data.ListOfMalfunctionsDao;
import app.pavel.pdd.data.MainProvisions;
import app.pavel.pdd.data.MainProvisionsDao;
import app.pavel.pdd.data.RoadMarking;
import app.pavel.pdd.data.RoadMarkingDao;
import app.pavel.pdd.data.RoadMarkingInfo;
import app.pavel.pdd.data.RoadMarkingInfoDao;
import app.pavel.pdd.data.TrafficRuleInfo;
import app.pavel.pdd.data.TrafficRules;
import app.pavel.pdd.data.TrafficRulesDao;
import app.pavel.pdd.data.TrafficRuleInfoDao;
import app.pavel.pdd.data.Database;
import app.pavel.pdd.data.Launch;
import app.pavel.pdd.data.LaunchDao;
import app.pavel.pdd.data.TrafficSigns;
import app.pavel.pdd.data.TrafficSignsDao;
import app.pavel.pdd.data.TrafficSignsInfo;
import app.pavel.pdd.data.TrafficSignsInfoDao;

public class AppViewModel extends AndroidViewModel {

    private final LaunchDao launchDao;
    private final TrafficRulesDao trafficRulesDao;
    private final TrafficRuleInfoDao trafficRuleInfoDao;
    private final TrafficSignsDao trafficSignsDao;
    private final TrafficSignsInfoDao trafficSignsInfoDao;
    private final RoadMarkingDao roadMarkingDao;
    private final RoadMarkingInfoDao roadMarkingInfoDao;
    private final MainProvisionsDao mainProvisionsDao;
    private final ListOfMalfunctionsDao listOfMalfunctionsDao;

    public AppViewModel(Application application) {
        super(application);

        launchDao = Database.getInstance(application).launchDao();
        trafficRulesDao = Database.getInstance(application).trafficRulesDao();
        trafficRuleInfoDao = Database.getInstance(application).trafficRuleInfoDao();
        trafficSignsDao = Database.getInstance(application).trafficSignsDao();
        trafficSignsInfoDao = Database.getInstance(application).trafficSignsInfoDao();
        roadMarkingDao = Database.getInstance(application).roadMarkingDao();
        roadMarkingInfoDao = Database.getInstance(application).roadMarkingInfoDao();
        mainProvisionsDao = Database.getInstance(application).mainProvisionsDao();
        listOfMalfunctionsDao = Database.getInstance(application).listOfMalfunctionsDao();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
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

    LiveData<List<MainProvisions>> getMainProvisions() {
        return mainProvisionsDao.findMainProvisions();
    }

    LiveData<List<ListOfMalfunctions>> getListOfMalfunctions() {
        return listOfMalfunctionsDao.findListOfMalfunctions();
    }

}