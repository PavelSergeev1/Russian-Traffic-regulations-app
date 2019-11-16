package app.pavel.handbooklivedataroom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;
import java.util.Objects;

import app.pavel.handbooklivedataroom.R;
import app.pavel.handbooklivedataroom.data.ListOfMalfunctions;
import app.pavel.handbooklivedataroom.data.MainProvisions;
import app.pavel.handbooklivedataroom.data.RoadMarkingInfo;
import app.pavel.handbooklivedataroom.data.TrafficRuleInfo;
import app.pavel.handbooklivedataroom.data.TrafficSignsInfo;
import app.pavel.handbooklivedataroom.utils.HandbookLiveDataRoom;

public class SelectedPageActivity extends AppCompatActivity {

    private String TAB = "\t";
    private String SPACE = " ";
    private String EN_DASH = "•";

    private static String imageName;

    LinearLayout linearLayout;

    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 200);
    LinearLayout.LayoutParams layoutParamsRM = new LinearLayout.LayoutParams(600, 200);
    LinearLayout.LayoutParams layoutParamsMP = new LinearLayout.LayoutParams(300, 125);
    //LinearLayout.LayoutParams layoutParamsMP = new LinearLayout.LayoutParams(150, 150);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_page_activity);

        Toolbar toolbar = findViewById(R.id.toolbarSelectedPage);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String pageTitle = intent.getStringExtra("Title");
        String toolbarTitle = intent.getStringExtra("toolbarTitle");
        String parentActivityName = intent.getStringExtra("parentActivity");

        Objects.requireNonNull(getSupportActionBar()).setTitle(pageTitle);

        TextView titleTextView;
        titleTextView = (TextView) findViewById(R.id.textViewCategoryTitle);
        titleTextView.setText(pageTitle);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        switch (parentActivityName) {
            case "TrafficSignsActivity":
                appViewModel.getTrafficSignsInfo(pageTitle)
                        .observe(this, this::showSignInformation);
                break;
            case "TrafficRulesActivity":
                appViewModel.getTrafficRuleInfo(pageTitle)
                        .observe(this, this::showRuleInformation);
                break;
            case "RoadMarkingActivity":
                appViewModel.getRoadMarking(pageTitle)
                        .observe(this, this::showRoadMarkingInformation);
                break;
            case "MainProvisions":
                appViewModel.getMainProvisions()
                        .observe(this, this::showMainProvisionsInformation);
                break;
            case "ListOfMalfunctions":
                appViewModel.getListOfMalfunctions()
                        .observe(this, this::showListOfMalfunctionsInformation);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + parentActivityName);
        }

        layoutParams.gravity = Gravity.CENTER;
        layoutParamsRM.gravity = Gravity.CENTER;
        layoutParamsMP.gravity = Gravity.CENTER;
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

    private void showMainProvisionsInformation(List<MainProvisions> mainProvisions) {
        for (int i = 0; i < mainProvisions.size(); i++) {
            fillMainProvisionsParagraph(mainProvisions.get(i));
        }
    }

    private void showListOfMalfunctionsInformation(List<ListOfMalfunctions> listOfMalfunctions) {
        for (int i = 0; i < listOfMalfunctions.size(); i++) {
            fillListOfMalfunctionsParagraph(listOfMalfunctions.get(i));
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
                text.append(TAB).append(TAB).append(EN_DASH).append(SPACE);
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
                text.append(TAB).append(TAB).append(EN_DASH).append(SPACE);
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
            imageView.setLayoutParams(layoutParamsRM);

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
                text.append(TAB).append(TAB).append(EN_DASH).append(SPACE);
            }

            text.append(roadMarkingInfo.getParagraph());

            textView.setText(text);
            textView.setTextColor(getResources().getColor(R.color.color_black));

            textView.setPadding(0,0, 0, 20);

            linearLayout.addView(textView);
        }

    }

    void fillMainProvisionsParagraph(final MainProvisions mainProvisions) {

        if (mainProvisions.getImageName() != null
                && !mainProvisions.getImageName().equals("no") ) {

            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParamsMP);

            imageName = mainProvisions.getImageName();

            int resID = HandbookLiveDataRoom.getContext().getResources()
                    .getIdentifier( imageName, "drawable",
                            HandbookLiveDataRoom.getThisPackageName());

            imageView.setImageResource(resID);
            imageView.setPadding(0,0, 0, 20);

            linearLayout.addView(imageView);
        }

        if (mainProvisions.getParagraph() != null &&
                !mainProvisions.getParagraph().contains("no")) {

            TextView textView = new TextView(this);
            StringBuilder text = new StringBuilder();

            char[] listItem;
            listItem = mainProvisions.getParagraph().substring(0, 1).toCharArray();

            if (!Character.isDigit(listItem[0])){
                text.append(TAB);
            }

            if (Character.isLowerCase(listItem[0])) {
                text.append(TAB).append(TAB).append(EN_DASH).append(SPACE);
            }

            text.append(mainProvisions.getParagraph());

            textView.setText(text);
            textView.setTextColor(getResources().getColor(R.color.color_black));

            textView.setPadding(0,0, 0, 20);

            linearLayout.addView(textView);
        }

    }

    void fillListOfMalfunctionsParagraph(final ListOfMalfunctions listOfMalfunctions) {

        if (listOfMalfunctions.getImageName() != null
                && !listOfMalfunctions.getImageName().equals("no") ) {

            ImageView imageView = new ImageView(this);
            //imageView.setLayoutParams(layoutParams);

            imageName = listOfMalfunctions.getImageName();

            int resID = HandbookLiveDataRoom.getContext().getResources()
                    .getIdentifier( imageName, "drawable",
                            HandbookLiveDataRoom.getThisPackageName());

            imageView.setImageResource(resID);
            imageView.setPadding(0,0, 0, 20);

            linearLayout.addView(imageView);
        }

        if (listOfMalfunctions.getParagraph() != null &&
                !listOfMalfunctions.getParagraph().contains("no")) {

            TextView textView = new TextView(this);
            StringBuilder text = new StringBuilder();

            char[] listItem, listItemEnd;
            listItem = listOfMalfunctions.getParagraph().substring(0, 1).toCharArray();
            listItemEnd = listOfMalfunctions.getParagraph()
                    .substring(listOfMalfunctions.getParagraph().length() - 1).toCharArray();

            if (!Character.isDigit(listItem[0])){
                text.append(TAB);
            }

            if (Character.isLowerCase(listItem[0])) {
                text.append(TAB).append(TAB).append(EN_DASH).append(SPACE);
            }

            text.append(listOfMalfunctions.getParagraph());

            if (listItemEnd[0] == '!') {
                text = new StringBuilder(text.substring(0, text.length() - 1));
                Log.i("TEXT CENTER", text.toString());
                textView.setGravity(Gravity.CENTER);
            }

            textView.setText(text);
            textView.setTextColor(getResources().getColor(R.color.color_black));

            textView.setPadding(0,0, 0, 20);

            linearLayout.addView(textView);
        }

    }

}