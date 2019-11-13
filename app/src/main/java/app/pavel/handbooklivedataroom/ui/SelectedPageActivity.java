package app.pavel.handbooklivedataroom.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import app.pavel.handbooklivedataroom.R;
import app.pavel.handbooklivedataroom.data.RoadMarkingInfo;
import app.pavel.handbooklivedataroom.data.TrafficRuleInfo;
import app.pavel.handbooklivedataroom.data.TrafficSignsInfo;
import app.pavel.handbooklivedataroom.utils.HandbookLiveDataRoom;

public class SelectedPageActivity extends AppCompatActivity {

    private String TAB = "\t";
    private String EN_DASH = "–";

    private static String imageName;

    LinearLayout linearLayout;

    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_rule_activity);

        Intent intent = getIntent();
        String pageTitle = intent.getStringExtra("Title");
        String parentActivityName = intent.getStringExtra("parentActivity");

        TextView titleTextView;
        titleTextView = (TextView) findViewById(R.id.textViewCategoryTitle);
        titleTextView.setText(pageTitle);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        if (parentActivityName.equals("TrafficSignsActivity")) {
            appViewModel.getTrafficSignsInfo(pageTitle)
                    .observe(this, this::showSignInformation);
        } else if (parentActivityName.equals("TrafficRulesActivity")) {
            appViewModel.getTrafficRuleInfo(pageTitle)
                    .observe(this, this::showRuleInformation);
        } else if (parentActivityName.equals("RoadMarkingActivity")) {
            appViewModel.getRoadMarking(pageTitle)
                    .observe(this, this::showRoadMarkingInformation);
        } else if (parentActivityName.equals("MainProvisionsActivity")) {
            //appViewModel.getMainProvisions(pageTitle).observe(this, this::showMainProvisionsInformation);
        }

        layoutParams.gravity = Gravity.CENTER;
    }

    @Override
    public void onPause() {
        super.onPause();

        linearLayout.removeAllViews();

        finish();
    }

    private void showSignInformation(List<TrafficSignsInfo> trafficSignsInfo) {
        for (int i = 0; i < trafficSignsInfo.size(); i++) {

            fillSignParagraph(trafficSignsInfo.get(i));
        }
    }

    private void showRuleInformation(List<TrafficRuleInfo> trafficRuleInfo) {
        for (int i = 0; i < trafficRuleInfo.size(); i++) {
            fillRuleParagraph(trafficRuleInfo.get(i));
        }
    }

    private void showRoadMarkingInformation(List<RoadMarkingInfo> roadMarkingInfo) {
        for (int i = 0; i < roadMarkingInfo.size(); i++) {
            fillRoadMarkingParagraph(roadMarkingInfo.get(i));
        }
    }

    void fillSignParagraph(final TrafficSignsInfo trafficSignsInfo) {

        if (trafficSignsInfo.getImageName() != null
                && !trafficSignsInfo.getImageName().equals("no") ) {

            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);

            imageName = trafficSignsInfo.getImageName();

            int resID = HandbookLiveDataRoom.getContext().getResources()
                    .getIdentifier( imageName, "drawable",
                            HandbookLiveDataRoom.getThisPackageName());

            imageView.setImageResource(resID);
            imageView.setPadding(0,0, 0, 20);

            linearLayout.addView(imageView);

        }

        if (trafficSignsInfo.getParagraph() != null &&
                !trafficSignsInfo.getParagraph().contains("no")) {

            TextView textView = new TextView(this);
            StringBuilder text = new StringBuilder();

            char[] listItem;
            listItem = trafficSignsInfo.getParagraph().substring(0, 1).toCharArray();

            if (!Character.isDigit(listItem[0])){
                text.append(TAB);
            }

            if (Character.isLowerCase(listItem[0])) {
                text.append(TAB).append(TAB).append(EN_DASH).append(TAB);
            }

            text.append(trafficSignsInfo.getParagraph());

            textView.setText(text);
            textView.setTextColor(getResources().getColor(R.color.color_black));

            textView.setPadding(0,0, 0, 20);

            linearLayout.addView(textView);
        }

    }

    void fillRuleParagraph(final TrafficRuleInfo trafficRuleInfo) {

        if (trafficRuleInfo.getImageName() != null
                && !trafficRuleInfo.getImageName().equals("no") ) {

            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);

            imageName = trafficRuleInfo.getImageName();

            int resID = HandbookLiveDataRoom.getContext().getResources()
                    .getIdentifier( imageName, "drawable",
                            HandbookLiveDataRoom.getThisPackageName());

            imageView.setImageResource(resID);
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

            textView.setText(text);
            textView.setTextColor(getResources().getColor(R.color.color_black));

            textView.setPadding(0,0, 0, 20);

            linearLayout.addView(textView);
        }

    }

    void fillRoadMarkingParagraph(final RoadMarkingInfo roadMarkingInfo) {

        if (roadMarkingInfo.getImageName() != null
                && !roadMarkingInfo.getImageName().equals("no") ) {

            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);

            imageName = roadMarkingInfo.getImageName();

            int resID = HandbookLiveDataRoom.getContext().getResources()
                    .getIdentifier( imageName, "drawable",
                            HandbookLiveDataRoom.getThisPackageName());

            imageView.setImageResource(resID);
            imageView.setPadding(0,0, 0, 20);

            linearLayout.addView(imageView);
        }

        if (roadMarkingInfo.getParagraph() != null &&
                !roadMarkingInfo.getParagraph().contains("no")) {

            TextView textView = new TextView(this);
            StringBuilder text = new StringBuilder();

            char[] listItem;
            listItem = roadMarkingInfo.getParagraph().substring(0, 1).toCharArray();

            if (!Character.isDigit(listItem[0])){
                text.append(TAB);
            }

            if (Character.isLowerCase(listItem[0])) {
                text.append(TAB).append(TAB).append(EN_DASH).append(TAB);
            }

            text.append(roadMarkingInfo.getParagraph());

            textView.setText(text);
            textView.setTextColor(getResources().getColor(R.color.color_black));

            textView.setPadding(0,0, 0, 20);

            linearLayout.addView(textView);
        }

    }

}
