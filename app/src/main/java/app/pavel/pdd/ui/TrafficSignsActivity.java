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

public class TrafficSignsActivity extends AppCompatActivity
    implements TrafficSignsAdapter.OnSignClickListener {

    private TrafficSignsAdapter trafficSignsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traffic_signs_activity);

        Toolbar toolbar = findViewById(R.id.toolbarSigns);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_24dp);

        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });

        TextView toolbarSignsTextView = findViewById(R.id.toolbarSignsTextView);
        toolbarSignsTextView.setText("Дорожные знаки");
        toolbarSignsTextView.setTypeface(
                HandbookLiveDataRoom.getTypefaceByTitle(LaunchActivity.currentFont));
        toolbarSignsTextView.setTextSize(
                HandbookLiveDataRoom.getTitleTextViewSize(LaunchActivity.currentTextSize));

        trafficSignsAdapter = new TrafficSignsAdapter(this, this);

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllTrafficSigns()
                .observe(this, trafficSigns -> trafficSignsAdapter.setData(trafficSigns));

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSigns);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(trafficSignsAdapter);
        recyclerView.addItemDecoration(new MarginItemDecorator(margin));
    }

    @Override
    public void onSignClickListener(String trafficSignTitle) {
        Intent intent = new Intent(this, SelectedPageActivity.class);
        intent.putExtra("Title", trafficSignTitle);
        intent.putExtra("toolbarTitle", "Дорожные знаки");
        intent.putExtra("parentActivity", "TrafficSignsActivity");
        startActivity(intent);
    }
}