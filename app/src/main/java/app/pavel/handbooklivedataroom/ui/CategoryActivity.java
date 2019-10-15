package app.pavel.handbooklivedataroom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import app.pavel.handbooklivedataroom.R;
import app.pavel.handbooklivedataroom.data.CategoryInfo;
import app.pavel.handbooklivedataroom.utils.HandbookLiveDataRoom;

public class CategoryActivity extends AppCompatActivity {

    // String textJustification = "<html><body><p align=\"justify\">";

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);

        Intent intent = getIntent();
        String categoryTitle = intent.getStringExtra("categoryTitle");

        TextView titleTextView;
        titleTextView = (TextView) findViewById(R.id.textViewCategoryTitle);
        titleTextView.setText(categoryTitle);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getCategoryInfo(categoryTitle)
                .observe(this, this::showCategoryInformation);
    }

    private void showCategoryInformation(List<CategoryInfo> categoryInfo) {
        for (int i = 0; i < categoryInfo.size(); i++) {
            fillParagraph(categoryInfo.get(i));
        }
    }

    void fillParagraph(final CategoryInfo categoryInfo) {

        String TAB = "\t";

        if (categoryInfo.getImageName() != null && !categoryInfo.getImageName().equals("no") ) {

            ImageView imageView = new ImageView(this);
            String imageName = categoryInfo.getImageName();
            int resID = HandbookLiveDataRoom.setImageInImageView(imageView, imageName);
            imageView.setImageResource(resID);

            if (imageView != null)
                linearLayout.addView(imageView);
        }

        if (categoryInfo.getParagraph() != null) {

            TextView textView = new TextView(this);
            StringBuilder text = new StringBuilder();
            // text.append(textJustification)
            text.append(TAB)
                    .append(categoryInfo.getParagraph()).append("\n");
            textView.setText(text);
            textView.setTextAppearance(this, android.R.style.TextAppearance_Small);
            linearLayout.addView(textView);
        }

    }
}