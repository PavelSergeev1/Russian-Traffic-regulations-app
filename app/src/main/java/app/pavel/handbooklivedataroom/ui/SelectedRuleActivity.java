package app.pavel.handbooklivedataroom.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import app.pavel.handbooklivedataroom.R;
import app.pavel.handbooklivedataroom.data.TrafficRuleInfo;
import app.pavel.handbooklivedataroom.utils.HandbookLiveDataRoom;

public class SelectedRuleActivity extends AppCompatActivity {

    static String imageName;

    LinearLayout linearLayout;

    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_rule_activity);

        Intent intent = getIntent();
        String trafficRuleTitle = intent.getStringExtra("Title");

        TextView titleTextView;
        titleTextView = (TextView) findViewById(R.id.textViewCategoryTitle);
        titleTextView.setText(trafficRuleTitle);
        linearLayout = findViewById(R.id.linearLayout);

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getTrafficRuleInfo(trafficRuleTitle)
                .observe(this, this::showRuleInformation);

        layoutParams.gravity = Gravity.CENTER;
    }

    @Override
    public void onPause() {
        super.onPause();

        linearLayout.removeAllViews();
        SelectedRuleActivity.this.finish();
    }

    private void showRuleInformation(List<TrafficRuleInfo> trafficRuleInfo) {
        for (int i = 0; i < trafficRuleInfo.size(); i++) {
            fillParagraph(trafficRuleInfo.get(i));
        }
    }

    void fillParagraph(final TrafficRuleInfo trafficRuleInfo) {

        String TAB = "\t";
        String EN_DASH = "–";

        if (trafficRuleInfo.getImageName() != null
                && !trafficRuleInfo.getImageName().equals("no") ) {

            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            //imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageName = trafficRuleInfo.getImageName();

            int resID = HandbookLiveDataRoom.getContext().getResources()
                    .getIdentifier( imageName, "drawable",
                            HandbookLiveDataRoom.getThisPackageName());

            imageView.setImageResource(resID);

            //new SelectedRuleActivity.SetImageInImageView().execute(imageView);

            imageView.setPadding(0,0, 0, 20);

            linearLayout.addView(imageView);
        }

        if (trafficRuleInfo.getParagraph() != null &&
                !trafficRuleInfo.getParagraph().contains("no")) {

            TextView textView = new TextView(this);
            StringBuilder text = new StringBuilder();

            char[] listItem;
            listItem = trafficRuleInfo.getParagraph().substring(0, 1).toCharArray();

            if (!Character.isDigit(listItem[0])){
                text.append(TAB);
            }

            if (Character.isLowerCase(listItem[0])) {
                text.append(TAB).append(TAB).append(EN_DASH).append(TAB);
            }

            text.append(trafficRuleInfo.getParagraph());
            //text.append("\n");

            textView.setText(text);
            textView.setTextColor(getResources().getColor(R.color.color_black));

            textView.setPadding(0,0, 0, 20);

            linearLayout.addView(textView);
        }

    }

    public static class SetImageInImageView extends AsyncTask<ImageView, Void, Integer> {
        ImageView imageView = null;
        @Override
        protected Integer doInBackground(ImageView... imageViews) {
            this.imageView = imageViews[0];
            return getImageFromResource(imageName);
        }

        @Override
        protected void onPostExecute(Integer resID) {

            imageView.setImageResource(resID);
        }

        private int getImageFromResource(String imageName) {
            return HandbookLiveDataRoom.getResourceId(imageName);
        }
    }

}