package app.pavel.pdd.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import app.pavel.pdd.R;
import app.pavel.pdd.ui.adapters.LaunchAdapter;
import app.pavel.pdd.utils.HandbookLiveDataRoom;
import app.pavel.pdd.utils.MarginItemDecorator;
import app.pavel.pdd.view_models.AppViewModel;

public class LaunchActivity extends AppCompatActivity
    implements LaunchAdapter.OnLaunchItemClickListener {

    public static final int margin = 20;

    private static final String FONTS = "pref_text_font";
    private static final String SIZES = "pref_text_text_size";

    public static String currentFont;
    public static String currentTextSize;

    private static LaunchAdapter launchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        Intent intent = getIntent();
        boolean isFirstRun = intent.getBooleanExtra("first_run", false);

        if (isFirstRun) {
            currentFont = "open_sans";
            currentTextSize = "text_medium";
        }
        else {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            currentFont = prefs.getString(FONTS, null);
            currentTextSize = prefs.getString(SIZES, null);
        }

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        PreferenceManager.getDefaultSharedPreferences(this).
                registerOnSharedPreferenceChangeListener(preferencesChangeListener);

        Toolbar toolbar = findViewById(R.id.toolbarLaunch);
        setSupportActionBar(toolbar);

        TextView toolbarLaunchTextView = findViewById(R.id.toolbarLaunchTextView);
        toolbarLaunchTextView.setText("ПДД РФ");
        toolbarLaunchTextView.setTypeface(HandbookLiveDataRoom.getTypefaceByTitle(currentFont));
        toolbarLaunchTextView.setTextSize(HandbookLiveDataRoom.getTitleTextViewSize(currentTextSize));

        launchAdapter = new LaunchAdapter(this, this);

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllLaunchItems()
                .observe(this, launches -> launchAdapter.setLaunchData(launches));

        RecyclerView recyclerView = findViewById(R.id.recyclerViewLaunch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(launchAdapter);
        recyclerView.addItemDecoration(new MarginItemDecorator(margin));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intent preferenceIntent = new Intent(this, SettingsActivity.class);
        preferenceIntent.putExtra("textFont", currentFont);
        startActivity(preferenceIntent);
        return super.onOptionsItemSelected(item);
    }

    private final SharedPreferences.OnSharedPreferenceChangeListener preferencesChangeListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {

                @Override
                public void onSharedPreferenceChanged(
                        SharedPreferences sharedPreferences, String key) {

                    if (key.equals(FONTS)) {
                        currentFont = sharedPreferences.getString(FONTS, null);
                        //Log.e("FONT FONT FONT FONT", Objects.requireNonNull(currentFont));
                    }
                    if (key.equals(SIZES)) {
                        currentTextSize = sharedPreferences.getString(SIZES, null);
                    }
                }
    };

    @Override
    public void onItemClickListener(String itemTitle) {
        switch (itemTitle) {
            case "Правила дорожного движения РФ": {
                Intent intent = new Intent(this, TrafficRulesActivity.class);
                startActivity(intent);
                break;
            }
            case "Дорожные знаки": {
                Intent intent = new Intent(this, TrafficSignsActivity.class);
                startActivity(intent);
                break;
            }
            case "Дорожная разметка и ее характеристики": {
                Intent intent = new Intent(this, RoadMarkingActivity.class);
                startActivity(intent);
                break;
            }
            case "Основные положения по допуску транспортных средств к " +
                    "эксплуатации и обязанности должностных лиц по обеспечению безопасности " +
                    "дорожного движения": {
                // start SelectedPageActivity
                Intent intent = new Intent(this, SelectedPageActivity.class);
                intent.putExtra("Title", "Основные положения по допуску транспортных " +
                        "средств к эксплуатации и обязанности должностных лиц по обеспечению " +
                        "безопасности дорожного движения");
                intent.putExtra("toolbarTitle", "Положения по допуску");
                intent.putExtra("parentActivity", "MainProvisions");
                startActivity(intent);
                break;
            }
            case "Перечень неисправностей и условий, при которых запрещается " +
                    "эксплуатация транспортных средств": {
                Intent intent = new Intent(this, SelectedPageActivity.class);
                intent.putExtra("Title", "Перечень неисправностей и условий, при которых " +
                        "запрещается эксплуатация транспортных средств");
                intent.putExtra("toolbarTitle", "Перечень неисправностей");
                intent.putExtra("parentActivity", "ListOfMalfunctions");
                startActivity(intent);
                break;
            }
        }

    }

    public static class UpdateRecyclerView {
        public void onUpdateRecyclerView() {

            launchAdapter.notifyDataSetChanged();
        }
    }

}