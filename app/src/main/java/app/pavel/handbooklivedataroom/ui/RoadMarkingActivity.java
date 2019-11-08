package app.pavel.handbooklivedataroom.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.pavel.handbooklivedataroom.R;

public class RoadMarkingActivity extends AppCompatActivity
        implements RoadMarkingAdapter.OnRoadMarkingClickListener {

    private RoadMarkingAdapter roadMarkingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traffic_signs_activity);

        roadMarkingAdapter = new RoadMarkingAdapter(this, this);

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllRoadMarking()
                .observe(this, roadMarking -> roadMarkingAdapter.setData(roadMarking));


        RecyclerView recyclerView = findViewById(R.id.recyclerViewSigns);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(roadMarkingAdapter);
    }

    @Override
    public void onRoadMarkingClickListener(String trafficSignTitle) {
        // start SelectedSignActivity
        Intent intent = new Intent(this, SelectedSignActivity.class);
        intent.putExtra("Title", trafficSignTitle);
        startActivity(intent);
    }
}
