package app.pavel.pdd.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.TextViewCompat;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import app.pavel.pdd.R;
import app.pavel.pdd.data.ListOfMalfunctions;
import app.pavel.pdd.data.MainProvisions;
import app.pavel.pdd.data.RoadMarkingInfo;
import app.pavel.pdd.data.TrafficRuleInfo;
import app.pavel.pdd.data.TrafficSignsInfo;
import app.pavel.pdd.utils.HandbookLiveDataRoom;
import app.pavel.pdd.view_models.AppViewModel;

public class SelectedPageActivity extends AppCompatActivity {

    private String pageTitle;
    private String toolbarTitle;
    private String parentActivityName;
    private String imageName;

    private LinearLayout linearLayout;

    private AdView mAdView;

    private static final LinearLayout.LayoutParams layoutParams =
            new LinearLayout.LayoutParams(400, 200);
    private static final LinearLayout.LayoutParams layoutParamsRM =
            new LinearLayout.LayoutParams(500, 250);
    private static final LinearLayout.LayoutParams layoutParamsMP =
            new LinearLayout.LayoutParams(200, 200);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_page_activity);

        Toolbar toolbar = findViewById(R.id.toolbarSelectedPage);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_24dp);
        toolbar.setNavigationOnClickListener(view -> finish());

        TextView toolbarSelectedPageTextView = findViewById(R.id.toolbarSelectedPageTextView);
        toolbarSelectedPageTextView.setTypeface(
                HandbookLiveDataRoom.getTypefaceByTitle(LaunchActivity.currentFont));
        toolbarSelectedPageTextView.setTextSize(HandbookLiveDataRoom
                .getTitleTextViewSize(LaunchActivity.currentTextSize));

        Intent intent = getIntent();

        if (intent.getStringExtra("Title") != null) {
            pageTitle = intent.getStringExtra("Title");
            toolbarTitle = intent.getStringExtra("toolbarTitle");
            parentActivityName = intent.getStringExtra("parentActivity");
        }

        if (savedInstanceState != null) {
            pageTitle = savedInstanceState.getString("pageTitle");
            toolbarTitle = savedInstanceState.getString("toolbarTitle");
            parentActivityName = savedInstanceState.getString("parentActivityName");
        }

        toolbarSelectedPageTextView.setText(pageTitle);

        TextView titleTextView;
        titleTextView = findViewById(R.id.textViewCategoryTitle);
        titleTextView.setText(pageTitle);
        titleTextView.setTypeface(
                HandbookLiveDataRoom.getTypefaceByTitle(LaunchActivity.currentFont));
        titleTextView.setTextSize(HandbookLiveDataRoom.getTitleTextViewSize(
                LaunchActivity.currentTextSize));

        linearLayout = findViewById(R.id.linearLayout);

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        getParagraphInformation(appViewModel, parentActivityName, pageTitle);

        layoutParams.gravity = Gravity.CENTER;
        layoutParamsRM.gravity = Gravity.CENTER;
        layoutParamsMP.gravity = Gravity.CENTER;

        if (isNetworkAvailable()) {
            mAdView = findViewById(R.id.adViewSelectedPage);
            mAdView.setVisibility(View.VISIBLE);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice("1234567")
                    .build();
            mAdView.loadAd(adRequest);
        }
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("pageTitle", pageTitle);
        outState.putString("toolbarTitle", toolbarTitle);
        outState.putString("parentActivityName", parentActivityName);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void getParagraphInformation(AppViewModel appViewModel,
                                         String parentActivityName, String pageTitle) {
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

    private ImageView setImageViewParams(ImageView imageView, String imageName, int layoutParam) {

        switch (layoutParam) {
            case 0:
                imageView.setLayoutParams(layoutParams);
                break;
            case 1:
                imageView.setLayoutParams(layoutParamsRM);
                break;
            case 2:
                imageView.setLayoutParams(layoutParamsMP);
                break;
        }

        int resID = getApplicationContext().getResources()
                .getIdentifier(imageName, "drawable",
                        HandbookLiveDataRoom.getThisPackageName());

        imageView.setImageResource(resID);
        imageView.setPadding(0,0, 0, 20);

        return imageView;
    }

    private TextView setTextViewParams(TextView textView, String paragraph) {

        textView.setTextColor(getResources().getColor(R.color.color_black));
        textView.setPadding(0,0, 0, 20);

        TextViewCompat.setAutoSizeTextTypeWithDefaults(textView,
                TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        textView.setTextSize(HandbookLiveDataRoom.getTextViewSize(LaunchActivity.currentTextSize));

        StringBuilder text = new StringBuilder();

        char[] listItem, listItemEnd;
        listItem = paragraph.substring(0, 1).toCharArray();
        listItemEnd = paragraph.substring(paragraph.length() - 1).toCharArray();

        String SPACE = " ";

        if (!Character.isDigit(listItem[0])){
            text.append(SPACE).append(SPACE);
        }
        if (Character.isLowerCase(listItem[0])) {
            String EN_DASH = "•";
            text.append(SPACE).append(SPACE).append(SPACE).append(EN_DASH).append(SPACE);
        }

        text.append(paragraph);

        if (listItemEnd[0] == '!') {
            text = new StringBuilder(text.substring(0, text.length() - 1));
            textView.setGravity(Gravity.CENTER);
        }

        textView.setText(text);
        textView.setTypeface(HandbookLiveDataRoom.getTypefaceByTitle(LaunchActivity.currentFont));

        return textView;
    }

    private void fillRuleParagraph(final TrafficRuleInfo trafficRuleInfo) {

        if (trafficRuleInfo.getImageName() != null
                && !trafficRuleInfo.getImageName().equals("no") ) {

            ImageView imageView = new ImageView(this);

            imageName = trafficRuleInfo.getImageName();

            linearLayout.addView(setImageViewParams(imageView, imageName, 0));
        }

        if (trafficRuleInfo.getParagraph() != null &&
                !trafficRuleInfo.getParagraph().contains("no")) {

            TextView textView = new TextView(this);

            String paragraph = trafficRuleInfo.getParagraph();

            linearLayout.addView(setTextViewParams(textView, paragraph));
        }
    }

    private void fillSignParagraph(final TrafficSignsInfo trafficSignsInfo) {

        if (trafficSignsInfo.getImageName() != null
                && !trafficSignsInfo.getImageName().equals("no") ) {

            ImageView imageView = new ImageView(this);

            imageName = trafficSignsInfo.getImageName();

            linearLayout.addView(setImageViewParams(imageView, imageName, 0));
        }

        if (trafficSignsInfo.getParagraph() != null &&
                !trafficSignsInfo.getParagraph().contains("no")) {

            TextView textView = new TextView(this);

            String paragraph = trafficSignsInfo.getParagraph();

            linearLayout.addView(setTextViewParams(textView, paragraph));
        }
    }

    private void fillRoadMarkingParagraph(final RoadMarkingInfo roadMarkingInfo) {

        if (roadMarkingInfo.getImageName() != null
                && !roadMarkingInfo.getImageName().equals("no") ) {

            ImageView imageView = new ImageView(this);

            imageName = roadMarkingInfo.getImageName();

            linearLayout.addView(setImageViewParams(imageView, imageName, 1));
        }

        if (roadMarkingInfo.getParagraph() != null &&
                !roadMarkingInfo.getParagraph().contains("no")) {

            TextView textView = new TextView(this);

            String paragraph = roadMarkingInfo.getParagraph();

            linearLayout.addView(setTextViewParams(textView, paragraph));
        }
    }

    private void fillMainProvisionsParagraph(final MainProvisions mainProvisions) {

        if (mainProvisions.getImageName() != null
                && !mainProvisions.getImageName().equals("no") ) {

            ImageView imageView = new ImageView(this);

            imageName = mainProvisions.getImageName();

            linearLayout.addView(setImageViewParams(imageView, imageName, 2));
        }

        if (mainProvisions.getParagraph() != null &&
                !mainProvisions.getParagraph().contains("no")) {

            TextView textView = new TextView(this);

            String paragraph = mainProvisions.getParagraph();

            linearLayout.addView(setTextViewParams(textView, paragraph));
        }
    }

    private void fillListOfMalfunctionsParagraph(final ListOfMalfunctions listOfMalfunctions) {

        if (listOfMalfunctions.getImageName() != null
                && !listOfMalfunctions.getImageName().equals("no") ) {

            ImageView imageView = new ImageView(this);

            imageName = listOfMalfunctions.getImageName();

            linearLayout.addView(setImageViewParams(imageView, imageName, 2));
        }

        if (listOfMalfunctions.getParagraph() != null &&
                !listOfMalfunctions.getParagraph().contains("no")) {

            TextView textView = new TextView(this);

            String paragraph = listOfMalfunctions.getParagraph();

            linearLayout.addView(setTextViewParams(textView, paragraph));
        }
    }

}