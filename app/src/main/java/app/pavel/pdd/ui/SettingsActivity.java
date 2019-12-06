package app.pavel.pdd.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import app.pavel.pdd.R;
import app.pavel.pdd.utils.HandbookLiveDataRoom;

public class SettingsActivity extends AppCompatActivity {

    private TextView toolbarLaunchTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        Toolbar toolbar = findViewById(R.id.toolbarSettings);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_24dp);

        toolbarLaunchTextView = findViewById(R.id.toolbarSettingsTextView);
        toolbarLaunchTextView.setText("Настройки");
        toolbarLaunchTextView.setTypeface(
                HandbookLiveDataRoom.getTypefaceByTitle(LaunchActivity.currentFont));
        toolbarLaunchTextView.setTextSize(
                HandbookLiveDataRoom.getTitleTextViewSize(LaunchActivity.currentTextSize));

        toolbar.setNavigationOnClickListener(view -> finish());
    }

}