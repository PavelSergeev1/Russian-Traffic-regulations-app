package app.pavel.handbooklivedataroom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import app.pavel.handbooklivedataroom.data.Category;
import app.pavel.handbooklivedataroom.data.CategoryDao;
import app.pavel.handbooklivedataroom.data.Database;

public class CategoryViewModel extends AndroidViewModel {

    private CategoryDao categoryDao;
    private ExecutorService executorService;

    public CategoryViewModel(@NonNull Application application) {
        super(application);

        categoryDao = Database.getInstance(application).categoryDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    LiveData<List<Category>> getAllCategories() {
        return categoryDao.findAll();
    }

    void saveCategory(Category category) {
        executorService.execute(() -> categoryDao.save(category));
    }

    void deleteCategory(Category category) {
        executorService.execute(() -> categoryDao.delete(category));
    }
}
