package app.pavel.handbooklivedataroom.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import app.pavel.handbooklivedataroom.R;

public class RoadMarkingActivity extends AppCompatActivity
        implements RoadMarkingAdapter.OnRoadMarkingClickListener {

    private RoadMarkingAdapter roadMarkingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.road_marking_activity);

        Toolbar toolbar = findViewById(R.id.toolbarRoadMarking);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Дорожая разметка");


        roadMarkingAdapter = new RoadMarkingAdapter(this, this);

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllRoadMarking()
                .observe(this, roadMarking -> roadMarkingAdapter.setData(roadMarking));


        RecyclerView recyclerView = findViewById(R.id.recyclerViewRoadMarking);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(roadMarkingAdapter);
    }

    @Override
    public void onRoadMarkingClickListener(String trafficSignTitle) {
        // start SelectedPageActivity
        Intent intent = new Intent(this, SelectedPageActivity.class);
        intent.putExtra("Title", trafficSignTitle);
        intent.putExtra("toolbarTitle", "Дорожая разметка");
        intent.putExtra("parentActivity", "RoadMarkingActivity");
        startActivity(intent);
    }
}