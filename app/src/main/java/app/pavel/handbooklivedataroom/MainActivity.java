package app.pavel.handbooklivedataroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import app.pavel.handbooklivedataroom.data.Category;
import app.pavel.handbooklivedataroom.data.Database;
import app.pavel.handbooklivedataroom.utils.DatabaseFilling;

public class MainActivity extends AppCompatActivity
    implements CategoriesAdapter.OnDeleteButtonClickListener {

    private CategoriesAdapter categoriesAdapter;
    private CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        DatabaseFilling.fillingAsync(Database.getInstance(this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        categoriesAdapter = new CategoriesAdapter(this, this);

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        categoryViewModel.getAllCategories()
                .observe(this, categories -> categoriesAdapter.setData(categories));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(categoriesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addCategory) {
            categoryViewModel.saveCategory(
                    new Category("This is a category title",
                            "This is a category content", "R.drawable.image_1"));
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDeleteButtonClickListener(Category category) {
        categoryViewModel.deleteCategory(category);
    }
}