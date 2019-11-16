package app.pavel.handbooklivedataroom.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import app.pavel.handbooklivedataroom.R;
import app.pavel.handbooklivedataroom.data.Database;
import app.pavel.handbooklivedataroom.utils.DatabaseFilling;

public class LaunchActivity extends AppCompatActivity
    implements LaunchAdapter.OnLaunchItemClickListener {

    SharedPreferences preferences = null;

    private LaunchAdapter launchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        preferences = getSharedPreferences("app.pavel.handbooklivedataroom", MODE_PRIVATE);

        Toolbar toolbar = findViewById(R.id.toolbarLaunch);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("ПДД РФ");

        launchAdapter = new LaunchAdapter(this, this);

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllLaunchItems()
                .observe(this, launches -> launchAdapter.setLaunchData(launches));

        RecyclerView recyclerView = findViewById(R.id.recyclerViewLaunch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(launchAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (preferences.getBoolean("first_run", true)) {

            DatabaseFilling.fillingAsync(Database.getInstance(this));

            preferences.edit().putBoolean("first_run", false).apply();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onItemClickListener(String itemTitle) {
        if (itemTitle.equals("Правила дорожного движения РФ")) {
            Intent intent = new Intent(this, TrafficRulesActivity.class);
            startActivity(intent);
        } else if (itemTitle.equals("Дорожные знаки")) {
            Intent intent = new Intent(this, TrafficSignsActivity.class);
            startActivity(intent);
        } else if (itemTitle.equals("Дорожная разметка и ее характеристики")) {
            Intent intent = new Intent(this, RoadMarkingActivity.class);
            startActivity(intent);
        } else if (itemTitle.equals("Основные положения по допуску транспортных средств к " +
                "эксплуатации и обязанности должностных лиц по обеспечению безопасности " +
                "дорожного движения")) {
            // start SelectedPageActivity
            Intent intent = new Intent(this, SelectedPageActivity.class);
            intent.putExtra("Title", "Основные положения по допуску транспортных " +
                    "средств к эксплуатации и обязанности должностных лиц по обеспечению " +
                    "безопасности дорожного движения");
            intent.putExtra("toolbarTitle", "Положения по допуску");
            intent.putExtra("parentActivity", "MainProvisions");
            startActivity(intent);
        } else if (itemTitle.equals("Перечень неисправностей и условий, при которых запрещается " +
                "эксплуатация транспортных средств")) {
            Intent intent = new Intent(this, SelectedPageActivity.class);
            intent.putExtra("Title", "Перечень неисправностей и условий, при которых " +
                    "запрещается эксплуатация транспортных средств");
            intent.putExtra("toolbarTitle", "Перечень неисправностей");
            intent.putExtra("parentActivity", "ListOfMalfunctions");
            startActivity(intent);
        }

    }

}