package app.pavel.pdd.utils;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import app.pavel.pdd.R;

public class HandbookLiveDataRoom extends Application {

    private static String packageName;
    private static Resources resources;

    private static Typeface montserratRegular, openSans, PTSansRegular, robotoMonoLight,
            robotoRegular, rubikRegular, ubuntuRegular;

    private static float textTitleSmall, textTitleMedium, textTitleBig, textSmall, textMedium,
        textBig;

    private static DisplayMetrics metrics;

    @Override
    public void onCreate() {
        super.onCreate();

        resources = getResources();
        packageName = getPackageName();

        montserratRegular = Typeface.createFromAsset(
                getApplicationContext().getAssets(), "fonts/montserrat_regular.ttf");
        openSans = Typeface.createFromAsset(
                getApplicationContext().getAssets(), "fonts/open_sans.ttf");
        PTSansRegular = Typeface.createFromAsset(
                getApplicationContext().getAssets(), "fonts/pt_sans_regular.ttf");
        robotoRegular = Typeface.createFromAsset(
                getApplicationContext().getAssets(), "fonts/roboto_regular.ttf");
        robotoMonoLight = Typeface.createFromAsset(
                getApplicationContext().getAssets(), "fonts/roboto_mono_light.ttf");
        rubikRegular = Typeface.createFromAsset(
                getApplicationContext().getAssets(), "fonts/rubik_regular.ttf");
        ubuntuRegular = Typeface.createFromAsset(
                getApplicationContext().getAssets(), "fonts/ubuntu_regular.ttf");

        textTitleBig = getApplicationContext().getResources()
                .getDimension(R.dimen.title_text_big);
        textTitleMedium = getApplicationContext().getResources()
                .getDimension(R.dimen.title_text_medium);
        textTitleSmall = getApplicationContext().getResources()
                .getDimension(R.dimen.title_text_small);
        textBig = getApplicationContext().getResources()
                .getDimension(R.dimen.text_big);
        textMedium = getApplicationContext().getResources()
                .getDimension(R.dimen.text_medium);
        textSmall = getApplicationContext().getResources()
                .getDimension(R.dimen.text_small);

        metrics = getApplicationContext().getResources().getDisplayMetrics();
    }

    public static Resources getHandbookLiveDataRoomResources() {
        return resources;
    }

    public static String getThisPackageName() {
        return packageName;
    }

    public static Typeface getTypefaceByTitle(String typefaceTitle) {

        Typeface typeface;

        switch (typefaceTitle) {
            case "montserrat_regular":
                typeface = montserratRegular;
                break;
            case "pt_sans_regular":
                typeface = PTSansRegular;
                break;
            case "roboto_regular":
                typeface = robotoRegular;
                break;
            case "roboto_mono_light":
                typeface = robotoMonoLight;
                break;
            case "rubik_regular":
                typeface = rubikRegular;
                break;
            case "ubuntu_regular":
                typeface = ubuntuRegular;
                break;
            default:
                typeface = openSans;
                break;
        }

        return typeface;
    }

   public static float getTitleTextViewSize(String textSize) {
        float size;
        switch (textSize) {
            case "text_medium":
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        textTitleMedium, metrics);
                break;
            case "text_big":
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        textTitleBig, metrics);
                break;
            default:
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        textTitleSmall, metrics);
        }

       return size;
   }

    public static float getTextViewSize(String textSize) {
        float size;
        switch (textSize) {
            case "text_medium":
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        textMedium, metrics);
                break;
            case "text_big":
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        textBig, metrics);
                break;
            default:
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        textSmall, metrics);
        }
        return size;
    }

}