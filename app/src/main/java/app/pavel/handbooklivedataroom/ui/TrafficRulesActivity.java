package app.pavel.handbooklivedataroom.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import app.pavel.handbooklivedataroom.R;

public class TrafficRulesActivity extends AppCompatActivity
    implements TrafficRulesAdapter.OnRuleClickListener {

    private TrafficRulesAdapter trafficRulesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traffic_rules_activity);

        /*

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         */

        trafficRulesAdapter = new TrafficRulesAdapter( this, this);

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllTrafficRules()
                .observe(this, trafficRules -> trafficRulesAdapter.setData(trafficRules));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(trafficRulesAdapter);
    }

    /*

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
                    new TrafficRules("This is a category title",
                            "This is a category content", "R.drawable.image_1"));
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

     */

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onRuleClickListener(String trafficRuleTitle) {
        // start SelectedRuleActivity
        Intent intent = new Intent(this, SelectedPageActivity.class);
        intent.putExtra("Title", trafficRuleTitle);
        intent.putExtra("parentActivity", "TrafficRulesActivity");
        startActivity(intent);

    }

}