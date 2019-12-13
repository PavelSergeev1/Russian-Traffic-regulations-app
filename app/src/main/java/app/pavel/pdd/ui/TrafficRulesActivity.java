package app.pavel.pdd.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import app.pavel.pdd.R;
import app.pavel.pdd.ui.adapters.TrafficRulesAdapter;
import app.pavel.pdd.utils.HandbookLiveDataRoom;
import app.pavel.pdd.utils.MarginItemDecorator;
import app.pavel.pdd.view_models.AppViewModel;

import static app.pavel.pdd.ui.LaunchActivity.margin;

public class TrafficRulesActivity extends AppCompatActivity
    implements TrafficRulesAdapter.OnRuleClickListener {

    private TrafficRulesAdapter trafficRulesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traffic_rules_activity);

        Toolbar toolbar = findViewById(R.id.toolbarTrafficRules);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_24dp);
        toolbar.setNavigationOnClickListener(view -> finish());

        TextView toolbarTrafficRulesTextView = findViewById(R.id.toolbarTrafficRulesTextView);
        toolbarTrafficRulesTextView.setText("Правила дорожного движения");
        toolbarTrafficRulesTextView.setTypeface(
                HandbookLiveDataRoom.getTypefaceByTitle(LaunchActivity.currentFont));
        toolbarTrafficRulesTextView.setTextSize(
                HandbookLiveDataRoom.getTitleTextViewSize(LaunchActivity.currentTextSize));

        trafficRulesAdapter = new TrafficRulesAdapter( this, this);

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllTrafficRules()
                .observe(this, trafficRules -> trafficRulesAdapter.setData(trafficRules));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(trafficRulesAdapter);
        recyclerView.addItemDecoration(new MarginItemDecorator(margin));
    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onRuleClickListener(String trafficRuleTitle) {
        // start SelectedRuleActivity
        Intent intent = new Intent(this, SelectedPageActivity.class);
        intent.putExtra("Title", trafficRuleTitle);
        intent.putExtra("toolbarTitle", "Правила дорожного движения");
        intent.putExtra("parentActivity", "TrafficRulesActivity");
        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}