package app.pavel.handbooklivedataroom.ui;

import android.content.Intent;
import android.os.Bundle;
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
        String EN_DASH = "–";

        if (categoryInfo.getImageName() != null && !categoryInfo.getImageName().equals("no") ) {

            ImageView imageView = new ImageView(this);
            String imageName = categoryInfo.getImageName();
            int resID = HandbookLiveDataRoom.setImageInImageView(imageView, imageName);
            imageView.setImageResource(resID);

            linearLayout.addView(imageView);
        }

        if (categoryInfo.getParagraph() != null) {

            TextView textView = new TextView(this);
            StringBuilder text = new StringBuilder();

            char[] listItem;
            listItem = categoryInfo.getParagraph().substring(0, 1).toCharArray();

            if (!Character.isDigit(listItem[0])){
                text.append(TAB);
            }

            if (Character.isLowerCase(listItem[0])) {
                text.append(TAB).append(TAB).append(EN_DASH).append(TAB);
            }

            text.append(categoryInfo.getParagraph());
            text.append("\n");
            textView.setText(text);

            linearLayout.addView(textView);
        }

    }
}