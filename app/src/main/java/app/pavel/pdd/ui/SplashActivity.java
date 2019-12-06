package app.pavel.pdd.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.ads.MobileAds;

import app.pavel.pdd.R;
import app.pavel.pdd.data.Database;
import app.pavel.pdd.utils.DatabaseFilling;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences preferences =
                getSharedPreferences("app.pavel.handbooklivedataroom", MODE_PRIVATE);

        if (preferences.getBoolean("first_run", true)) {

            MobileAds.initialize(this, getResources().getString(R.string.app_id));

            new PrefetchData().execute();

            preferences.edit().putBoolean("first_run", false).apply();
        }
        else {
            Intent intent = new Intent(SplashActivity.this, LaunchActivity.class);
            intent.putExtra("first_run", false);
            startActivity(intent);

            finish();
        }

    }

    private class PrefetchData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {

            DatabaseFilling.fillingAsync(Database.getInstance(getApplicationContext()));

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Intent intent = new Intent(SplashActivity.this, LaunchActivity.class);
            intent.putExtra("first_run", true);
            startActivity(intent);

            finish();
        }

    }
}