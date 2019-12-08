package app.pavel.pdd.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.TypedValue;

import app.pavel.pdd.R;

public class HandbookLiveDataRoom extends Application {

    private static String packageName;
    private static Resources resources;

    private static Typeface montserratRegular, openSans, PTSansRegular, robotoMonoLight,
            robotoRegular, rubikRegular, ubuntuRegular;

    private static Application sApplication;

    private static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        resources = getResources();
        packageName = getPackageName();

        sApplication = this;

        montserratRegular = Typeface.createFromAsset(
                getContext().getAssets(), "fonts/montserrat_regular.ttf");
        openSans = Typeface.createFromAsset(
                getContext().getAssets(), "fonts/open_sans.ttf");
        PTSansRegular = Typeface.createFromAsset(
                getContext().getAssets(), "fonts/pt_sans_regular.ttf");
        robotoRegular = Typeface.createFromAsset(
                getContext().getAssets(), "fonts/roboto_regular.ttf");
        robotoMonoLight = Typeface.createFromAsset(
                getContext().getAssets(), "fonts/roboto_mono_light.ttf");
        rubikRegular = Typeface.createFromAsset(
                getContext().getAssets(), "fonts/rubik_regular.ttf");
        ubuntuRegular = Typeface.createFromAsset(
                getContext().getAssets(), "fonts/ubuntu_regular.ttf");
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
                        getContext().getResources().getDimension(R.dimen.title_text_medium),
                        getContext().getResources().getDisplayMetrics());
                break;
            case "text_big":
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        getContext().getResources().getDimension(R.dimen.title_text_big),
                        getContext().getResources().getDisplayMetrics());
                break;
            default:
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        getContext().getResources().getDimension(R.dimen.title_text_small),
                        getContext().getResources().getDisplayMetrics());
        }

       return size;
   }

    public static float getTextViewSize(String textSize) {
        float size;
        switch (textSize) {
            case "text_medium":
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        getContext().getResources().getDimension(R.dimen.text_medium),
                        getContext().getResources().getDisplayMetrics());
                break;
            case "text_big":
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        getContext().getResources().getDimension(R.dimen.text_big),
                        getContext().getResources().getDisplayMetrics());
                break;
            default:
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        getContext().getResources().getDimension(R.dimen.text_small),
                        getContext().getResources().getDisplayMetrics());
        }
        return size;
    }

}