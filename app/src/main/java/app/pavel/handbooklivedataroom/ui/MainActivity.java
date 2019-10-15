package app.pavel.handbooklivedataroom.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import app.pavel.handbooklivedataroom.R;
import app.pavel.handbooklivedataroom.data.Category;

// implements CategoriesAdapter.OnDeleteButtonClickListener {
public class MainActivity extends AppCompatActivity
    implements CategoriesAdapter.OnCategoryClickListener {

    private AppViewModel appViewModel;
    private CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        categoriesAdapter = new CategoriesAdapter( this, this);

        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllCategories()
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
            appViewModel.saveCategory(
                    new Category("This is a category title",
                            "This is a category content", "R.drawable.image_1"));
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCategoryClickListener(String categoryTitle) {
        // start CategoryActivity
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("categoryTitle", categoryTitle);
        startActivity(intent);
    }

    /**

    @Override
    public void onDeleteButtonClickListener(Category category) {
        appViewModel.deleteCategory(category);
    }

    */

    // WARNING
    // Schema export directory is not provided to the annotation processor so we cannot export
    // the schema. You can either provide `room.schemaLocation` annotation processor argument
    // OR set exportSchema to false.
}