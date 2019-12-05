package app.pavel.pdd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.pavel.pdd.R;
import app.pavel.pdd.utils.HandbookLiveDataRoom;

import static app.pavel.pdd.ui.LaunchActivity.margin;

public class RoadMarkingActivity extends AppCompatActivity
        implements RoadMarkingAdapter.OnRoadMarkingClickListener {

    private RoadMarkingAdapter roadMarkingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.road_marking_activity);

        Toolbar toolbar = findViewById(R.id.toolbarRoadMarking);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_24dp);

        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });

        TextView toolbarRoadMarkingTextView = findViewById(R.id.toolbarRoadMarkingTextView);
        toolbarRoadMarkingTextView.setText("Дорожная разметка");
        toolbarRoadMarkingTextView.setTypeface(
                HandbookLiveDataRoom.getTypefaceByTitle(LaunchActivity.currentFont));
        toolbarRoadMarkingTextView.setTextSize(
                HandbookLiveDataRoom.getTitleTextViewSize(LaunchActivity.currentTextSize));

        roadMarkingAdapter = new RoadMarkingAdapter(this, this);

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllRoadMarking()
                .observe(this, roadMarking -> roadMarkingAdapter.setData(roadMarking));

        RecyclerView recyclerView = findViewById(R.id.recyclerViewRoadMarking);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(roadMarkingAdapter);
        recyclerView.addItemDecoration(new MarginItemDecorator(margin));
    }

    @Override
    public void onRoadMarkingClickListener(String trafficSignTitle) {
        Intent intent = new Intent(this, SelectedPageActivity.class);
        intent.putExtra("Title", trafficSignTitle);
        intent.putExtra("toolbarTitle", "Дорожая разметка");
        intent.putExtra("parentActivity", "RoadMarkingActivity");
        startActivity(intent);
    }
}