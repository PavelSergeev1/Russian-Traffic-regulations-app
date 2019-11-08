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
import app.pavel.handbooklivedataroom.data.TrafficSignsInfo;
import app.pavel.handbooklivedataroom.utils.HandbookLiveDataRoom;

public class SelectedSignActivity extends AppCompatActivity {

    //TextView textView;

    //ImageView imageView;

    public static String imageName;

    LinearLayout linearLayout;

    //LayoutInflater inflater;

    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_rule_activity);

        Intent intent = getIntent();
        String trafficSignsTitle = intent.getStringExtra("Title");

        TextView titleTextView;
        titleTextView = (TextView) findViewById(R.id.textViewCategoryTitle);
        titleTextView.setText(trafficSignsTitle);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getTrafficSignsInfo(trafficSignsTitle)
                .observe(this, this::showSignInformation);

        //inflater = this.getLayoutInflater();

        layoutParams.gravity = Gravity.CENTER;
    }

    @Override
    public void onPause() {
        super.onPause();

        linearLayout.removeAllViews();
        SelectedSignActivity.this.finish();
    }

    private void showSignInformation(List<TrafficSignsInfo> trafficSignsInfo) {
        View[] itemViews = new View[trafficSignsInfo.size()];
        for (int i = 0; i < trafficSignsInfo.size(); i++) {

            //itemViews[i] = getLayoutInflater().inflate(R.layout.paragraph_item, null);

            //fillParagraph(trafficSignsInfo.get(i), itemViews[i]);

            //linearLayout.addView(itemViews[i]);

            fillParagraph(trafficSignsInfo.get(i));
        }
    }

    // void fillParagraph(final TrafficSignsInfo trafficSignsInfo, View itemView) {
    void fillParagraph(final TrafficSignsInfo trafficSignsInfo) {

        //imageView = itemView.findViewById(R.id.imageViewParagraphItem);
        //TextView textView = itemView.findViewById(R.id.textViewParagraphItem);

        String TAB = "\t";
        String EN_DASH = "–";

        //LayoutInflater inflater = ; //= (LayoutInflater) Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if (trafficSignsInfo.getImageName() != null
                && !trafficSignsInfo.getImageName().equals("no") ) {

            //imageView = itemView.findViewById(R.id.imageViewParagraphItem);
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            //imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageName = trafficSignsInfo.getImageName();

            int resID = HandbookLiveDataRoom.getContext().getResources()
                    .getIdentifier( imageName, "drawable",
                            HandbookLiveDataRoom.getThisPackageName());

            imageView.setImageResource(resID);

            //new SetImageInImageView().execute();
            //new SelectedSignActivity.SetImageInImageView().execute(imageView);



            imageView.setPadding(0,0, 0, 20);

            //imageView.setMaxWidth(200);
            //imageView.setMaxHeight(200);

            linearLayout.addView(imageView);

            //imageView = null;
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
            //text.append("\n");

            textView.setText(text);
            textView.setTextColor(getResources().getColor(R.color.color_black));

            textView.setPadding(0,0, 0, 20);

            linearLayout.addView(textView);
        }

    }

    /*

    public void setImageInImageView(Drawable drawable) {
        try {
            imageView.setImageDrawable(drawable);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

     */

    /*

    private class SetImageInImageView extends AsyncTask<Integer, Void, Drawable> {
        //ImageView imageView = null;
        @Override
        protected Drawable doInBackground(Integer... resourceId) {
            //imageView = imageViews[0];

            int resId = HandbookLiveDataRoom.getResourceId(imageName);

            return SelectedSignActivity.this.getResources().getDrawable(resId);
        }

        @Override
        protected void onPostExecute(Drawable drawable) {

            SelectedSignActivity.this.setImageInImageView(drawable);

            //imageView.setImageResource(resID);
            //Glide.with(imageView.getContext()).load(resID).into(imageView);

            //((ViewGroup)imageView.getParent()).removeView(imageView);

        }

        /*

        private int getImageFromResource(String imageName) {
            return HandbookLiveDataRoom.getResourceId(imageName);
        }

         */

    public class SetImageInImageView extends AsyncTask<ImageView, Void, Integer> {
        ImageView imageView = null;
        @Override
        protected Integer doInBackground(ImageView... imageViews) {
            this.imageView = imageViews[0];
            return getImageFromResource(imageName);
        }

        @Override
        protected void onPostExecute(Integer resID) {

            //imageView.setImageResource(resID);
        }

        private int getImageFromResource(String imageName) {
            return HandbookLiveDataRoom.getResourceId(imageName);
        }

    }

}
