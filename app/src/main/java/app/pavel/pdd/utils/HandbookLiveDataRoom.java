package app.pavel.pdd.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.TypedValue;

import app.pavel.pdd.R;

public class HandbookLiveDataRoom extends Application {

    private static String packageName;
    private static Resources resources;

    private static Context context;

    private static Typeface montserratRegular, openSans, PTSansRegular, robotoMonoLight, robotoRegular,
            rubikRegular, ubuntuRegular;

    @Override
    public void onCreate() {
        super.onCreate();

        resources = getResources();
        packageName = getPackageName();

        context = this;

        montserratRegular = Typeface.createFromAsset(context.getAssets(), "fonts/montserrat_regular.ttf");
        openSans = Typeface.createFromAsset(context.getAssets(), "fonts/open_sans.ttf");
        PTSansRegular = Typeface.createFromAsset(context.getAssets(), "fonts/pt_sans_regular.ttf");
        robotoRegular = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_regular.ttf");
        robotoMonoLight = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_mono_light.ttf");
        rubikRegular = Typeface.createFromAsset(context.getAssets(), "fonts/rubik_regular.ttf");
        ubuntuRegular = Typeface.createFromAsset(context.getAssets(), "fonts/ubuntu_regular.ttf");
    }

    public static Context getContext() {
        return context;
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

   public static float getTitleTextViewSize(String textsize) {
        float size;
        switch (textsize) {
            case "text_medium":
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        context.getResources().getDimension(R.dimen.title_text_medium),
                        context.getResources().getDisplayMetrics());
                break;
            case "text_big":
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        context.getResources().getDimension(R.dimen.title_text_big),
                        context.getResources().getDisplayMetrics());
                break;
            default:
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        context.getResources().getDimension(R.dimen.title_text_small),
                        context.getResources().getDisplayMetrics());
        }

       return size;
   }

    public static float getTextViewSize(String textsize) {
        float size;
        switch (textsize) {
            case "text_medium":
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        context.getResources().getDimension(R.dimen.text_medium),
                        context.getResources().getDisplayMetrics());
                break;
            case "text_big":
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        context.getResources().getDimension(R.dimen.text_big),
                        context.getResources().getDisplayMetrics());
                break;
            default:
                size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        context.getResources().getDimension(R.dimen.text_small),
                        context.getResources().getDisplayMetrics());
        }
        return size;
    }
}