package app.pavel.handbooklivedataroom.utils;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import app.pavel.handbooklivedataroom.R;
import app.pavel.handbooklivedataroom.data.RoadMarking;
import app.pavel.handbooklivedataroom.data.RoadMarkingInfo;
import app.pavel.handbooklivedataroom.data.TrafficRuleInfo;
import app.pavel.handbooklivedataroom.data.TrafficRules;
import app.pavel.handbooklivedataroom.data.Database;
import app.pavel.handbooklivedataroom.data.Launch;
import app.pavel.handbooklivedataroom.data.TrafficSigns;
import app.pavel.handbooklivedataroom.data.TrafficSignsInfo;

public class DatabaseFilling {

    public static void fillingAsync(@NonNull final Database database) {
        FillingDatabaseAsync task = new FillingDatabaseAsync(database);
        task.execute();
    }

    public static void fillingSync(@NonNull final Database database) {
        fillingWithData(database);
    }

    private static void addLaunchItem(final Database database, Launch launch) {
        database.launchDao().save(launch);
    }

    private static void addTrafficRules(final Database database, TrafficRules trafficRules) {
        database.trafficRulesDao().save(trafficRules);
    }

    private static void addTrafficRuleInfo(final Database database, TrafficRuleInfo trafficRuleInfo) {
        database.trafficRuleInfoDao().save(trafficRuleInfo);
    }

    private static void addTrafficSigns(final Database database, TrafficSigns trafficSigns) {
        database.trafficSignsDao().save(trafficSigns);
    }

    private static void addTrafficSignsInfo(final Database database,
                                            TrafficSignsInfo trafficSignsInfo) {
        database.trafficSignsInfoDao().save(trafficSignsInfo);
    }

    private static void addRoadMarkingItem(final Database database, RoadMarking roadMarking) {
        database.roadMarkingDao().save(roadMarking);
    }

    private static void addRoadMarkingInfo(final Database database, RoadMarkingInfo roadMarkingInfo) {
        database.roadMarkingInfoDao().save(roadMarkingInfo);
    }

    // filling database when app setting up in the first time
    private static void fillingWithData(Database database) {

        String[] LAUNCH_TITLE = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.launch_title);
        String[] LAUNCH_SHORT_DESC = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.launch_short_desc);
        String[] LAUNCH_IMAGE_NAME = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.launch_imageName);

        for (int i = 0; i < LAUNCH_TITLE.length; i++) {
            Launch launch = new Launch();
            launch.setTitle(LAUNCH_TITLE[i]);
            launch.setDescription(LAUNCH_SHORT_DESC[i]);
            launch.setImageName(LAUNCH_IMAGE_NAME[i]);
            addLaunchItem(database, launch);
        }

        String[] TRAFFIC_RULES = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.traffic_rules);
        String[] TRAFFIC_RULES_IMAGE_NAMES = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.traffic_rules_image_names);

        for (int i = 0; i < TRAFFIC_RULES.length; i++) {
            TrafficRules trafficRules = new TrafficRules();
            trafficRules.setTitle(TRAFFIC_RULES[i]);
            trafficRules.setImageName(TRAFFIC_RULES_IMAGE_NAMES[i]);
            addTrafficRules(database, trafficRules);
        }

        String[] TRAFFIC_RULE_ID = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.traffic_rule_id);
        String[] TRAFFIC_RULE_ID_IMAGE_NAME = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.traffic_rule_image_name);
        String[] TRAFFIC_RULE_PARAGRAPH = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.traffic_rule_paragraph);

        for (int i = 0; i < TRAFFIC_RULE_ID.length - 1 ; i++) {
            TrafficRuleInfo trafficRuleInfo = new TrafficRuleInfo();
            trafficRuleInfo.setCategoryId(TRAFFIC_RULE_ID[i]);
            trafficRuleInfo.setImageName(TRAFFIC_RULE_ID_IMAGE_NAME[i]);
            trafficRuleInfo.setParagraph(TRAFFIC_RULE_PARAGRAPH[i]);
            addTrafficRuleInfo(database, trafficRuleInfo);
        }

        String[] TRAFFIC_SIGNS = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.traffic_signs);
        String[] TRAFFIC_SIGNS_IMAGE_NAMES = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.traffic_signs_image_names);

        for (int i = 0; i < TRAFFIC_SIGNS.length; i++) {
            TrafficSigns trafficSigns = new TrafficSigns();
            trafficSigns.setTitle(TRAFFIC_SIGNS[i]);
            trafficSigns.setImageName(TRAFFIC_SIGNS_IMAGE_NAMES[i]);
            addTrafficSigns(database, trafficSigns);
        }

        String[] TRAFFIC_SIGNS_ID = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.traffic_signs_id);
        String[] TRAFFIC_SIGN_IMAGE_NAME = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.traffic_sign_image_name);
        String[] TRAFFIC_SIGN_PARAGRAPH = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.traffic_sign_paragraph);

        for (int i = 0; i < TRAFFIC_SIGNS_ID.length - 1; i++) {
            TrafficSignsInfo trafficSignsInfo = new TrafficSignsInfo();
            trafficSignsInfo.setSignTitle(TRAFFIC_SIGNS_ID[i]);
            trafficSignsInfo.setImageName(TRAFFIC_SIGN_IMAGE_NAME[i]);
            trafficSignsInfo.setParagraph(TRAFFIC_SIGN_PARAGRAPH[i]);
            addTrafficSignsInfo(database, trafficSignsInfo);
        }

        String[] ROAD_MARKING_TITLE = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.road_marking_title);
        String[] ROAD_MARKING_IMAGE_NAME = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.road_marking_image_name);

        for (int i = 0; i < ROAD_MARKING_TITLE.length; i++) {
            RoadMarking roadMarking = new RoadMarking();
            roadMarking.setTitle(ROAD_MARKING_TITLE[i]);
            roadMarking.setImageName(ROAD_MARKING_IMAGE_NAME[i]);
            addRoadMarkingItem(database, roadMarking);
        }

        String[] RM_ID = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.rm_id);
        String[] RM_IMAGE_NAME = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.rm_image_name);
        String[] RM_PARAGRAPH = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.rm_paragraph);

        for (int i = 0; i < RM_ID.length; i++) {
            RoadMarkingInfo roadMarkingInfo = new RoadMarkingInfo();
            roadMarkingInfo.setCategoryId(RM_ID[i]);
            roadMarkingInfo.setImageName(RM_IMAGE_NAME[i]);
            roadMarkingInfo.setParagraph(RM_PARAGRAPH[i]);
            addRoadMarkingInfo(database, roadMarkingInfo);
        }
    }

    private static class FillingDatabaseAsync extends AsyncTask<Void, Void, Void> {

        private final Database database;

        FillingDatabaseAsync(Database database) {
            this.database = database;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            fillingWithData(database);
            return null;
        }
    }

}