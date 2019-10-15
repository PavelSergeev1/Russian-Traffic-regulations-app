package app.pavel.handbooklivedataroom.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import app.pavel.handbooklivedataroom.data.Category;
import app.pavel.handbooklivedataroom.data.CategoryDao;
import app.pavel.handbooklivedataroom.data.CategoryInfo;
import app.pavel.handbooklivedataroom.data.CategoryInfoDao;
import app.pavel.handbooklivedataroom.data.Database;
import app.pavel.handbooklivedataroom.data.Launch;
import app.pavel.handbooklivedataroom.data.LaunchDao;

public class AppViewModel extends AndroidViewModel {

    private LaunchDao launchDao;
    private CategoryDao categoryDao;
    private CategoryInfoDao categoryInfoDao;
    private ExecutorService executorService;

    public AppViewModel(@NonNull Application application) {
        super(application);

        launchDao = Database.getInstance(application).launchDao();
        categoryDao = Database.getInstance(application).categoryDao();
        categoryInfoDao = Database.getInstance(application).categoryInfoDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    LiveData<List<Launch>> getAllLaunchItems() {
        return launchDao.findAll();
    }

    LiveData<List<Category>> getAllCategories() {
        return categoryDao.findAll();
    }

    void saveCategory(Category category) {
        executorService.execute(() -> categoryDao.save(category));
    }

    /**

    void deleteCategory(Category category) {
        executorService.execute(() -> categoryDao.delete(category));
    }

     */

    LiveData<List<CategoryInfo>> getCategoryInfo(String categotyTitle) {
        return categoryInfoDao.findCategoryInfo(categotyTitle);
    }
}