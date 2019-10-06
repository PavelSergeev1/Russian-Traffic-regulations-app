package app.pavel.handbooklivedataroom.utils;

import android.content.res.Resources;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import app.pavel.handbooklivedataroom.R;
import app.pavel.handbooklivedataroom.data.Category;
import app.pavel.handbooklivedataroom.data.Database;

public class DatabaseFilling {

    private static final String TAG = DatabaseFilling.class.getName();

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

    // filling database when app setting up in the first time
    private static void fillingWithData(Database database) {

        Resources resources = Resources.getSystem();
        String[] CATEGORIES = resources.getStringArray(R.array.categories);
        String[] CATEGORIES_SHORT_DESC = resources.getStringArray(R.array.categories_short_desc);
        String[] CATEGORIES_IMAGE_NAMES = resources.getStringArray(R.array.categories_image_names);

        for (int i = 0; i < CATEGORIES.length; i++) {
            Category category = new Category();
            category.setTitle(CATEGORIES[i]);
            category.setContent(CATEGORIES_SHORT_DESC[0]);
            category.setImageName(CATEGORIES_IMAGE_NAMES[i]);
            addCategory(database, category);
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
