package app.pavel.handbooklivedataroom.utils;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import app.pavel.handbooklivedataroom.R;
import app.pavel.handbooklivedataroom.data.Category;
import app.pavel.handbooklivedataroom.data.CategoryInfo;
import app.pavel.handbooklivedataroom.data.Database;
import app.pavel.handbooklivedataroom.data.Launch;

public class DatabaseFilling {

    // private static final String TAG = DatabaseFilling.class.getName();

    public static void fillingAsync(@NonNull final Database database) {
        FillingDatabaseAsync task = new FillingDatabaseAsync(database);
        task.execute();
    }

    public static void fillingSync(@NonNull final Database database) {
        fillingWithData(database);
    }

    private static Category addCategory(final Database database, Category category) {
        database.categoryDao().save(category);
        return category;
    }

    private static CategoryInfo addCategoryInfo(final Database database, CategoryInfo categoryInfo) {
        database.categoryInfoDao().save(categoryInfo);
        return categoryInfo;
    }

    private static Launch addLaunchItem(final Database database, Launch launch) {
        database.launchDao().save(launch);
        return launch;
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

        String[] CATEGORIES = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.categories);
        String[] CATEGORIES_SHORT_DESC = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.categories_short_desc);
        String[] CATEGORIES_IMAGE_NAMES = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.categories_image_names);

        for (int i = 0; i < CATEGORIES.length; i++) {
            Category category = new Category();
            category.setTitle(CATEGORIES[i]);
            category.setContent(CATEGORIES_SHORT_DESC[0]);
            category.setImageName(CATEGORIES_IMAGE_NAMES[i]);
            addCategory(database, category);
        }

        String[] CATEGORY_ID = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.category_id);
        String[] CATEGORY_IMAGE_NAME = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.imageName);
        String[] CATEGORY_PARAGRAPH = HandbookLiveDataRoom.getHandbookLiveDataRoomResources()
                .getStringArray(R.array.paragraph);

        for (int i = 0; i < CATEGORY_ID.length - 1 ; i++) {
            CategoryInfo categoryInfo = new CategoryInfo();
            categoryInfo.setCategoryId(CATEGORY_ID[i]);
            categoryInfo.setImageName(CATEGORY_IMAGE_NAME[i]);
            categoryInfo.setParagraph(CATEGORY_PARAGRAPH[i]);
            addCategoryInfo(database, categoryInfo);
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