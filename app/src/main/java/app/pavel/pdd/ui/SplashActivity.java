package app.pavel.pdd.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

        SharedPreferences preferences =
                getSharedPreferences("app.pavel.handbooklivedataroom", MODE_PRIVATE);

        if (preferences.getBoolean("first_run", true)) {

            initializeApp(preferences);
        }
        else {
            Intent intent = new Intent(SplashActivity.this, LaunchActivity.class);
            intent.putExtra("first_run", false);
            startActivity(intent);

            finish();
        }

    }

    private void initializeApp(SharedPreferences preferences) {
        MobileAds.initialize(this, getResources().getString(R.string.app_id));

        Context context = getApplicationContext();

        new PrefetchData(new PrefetchData.AsyncResponse() {
            @Override
            public void processFinished(Boolean output) {

                preferences.edit().putBoolean("first_run", false).apply();

                goToLaunchActivity();
            }
        }).execute(context);
    }

    private static class PrefetchData extends AsyncTask<Context, Void, Void> {

        interface AsyncResponse {
            void processFinished(Boolean output);
        }

        final AsyncResponse asyncResponse;

        PrefetchData(AsyncResponse asyncResponse) {
            this.asyncResponse = asyncResponse;
        }

        @Override
        protected Void doInBackground(Context... contexts) {

            try {
                DatabaseFilling.fillingAsync(Database.getInstance(contexts[0]));

                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            asyncResponse.processFinished(true);
        }
    }

    private void goToLaunchActivity() {
        Intent intent = new Intent(SplashActivity.this, LaunchActivity.class);
        intent.putExtra("first_run", true);
        startActivity(intent);

        finish();
    }

}