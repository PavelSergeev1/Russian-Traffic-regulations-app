package app.pavel.handbooklivedataroom.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
        Intent intent = new Intent(this, SelectedRuleActivity.class);
        intent.putExtra("Title", trafficRuleTitle);
        startActivity(intent);

    }

    /*

    @Override
    public void onDeleteButtonClickListener(TrafficRules category) {
        appViewModel.deleteCategory(category);
    }

    */

    // WARNING
    // Schema export directory is not provided to the annotation processor so we cannot export
    // the schema. You can either provide `room.schemaLocation` annotation processor argument
    // OR set exportSchema to false.
}