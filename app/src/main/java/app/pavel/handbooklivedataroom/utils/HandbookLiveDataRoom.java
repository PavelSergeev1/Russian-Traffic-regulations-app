package app.pavel.handbooklivedataroom.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

public class HandbookLiveDataRoom extends Application {

    private static String packageName;
    private static Resources resources;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        resources = getResources();
        packageName = getPackageName();

        context = this;
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

    public static int getResourceId(String imageName) {
        //return resources.getIdentifier(packageName + ":drawable/" + imageName, null,null);

        Log.e("---RESOURCE---", String.valueOf(resources.getIdentifier( imageName, "drawable", packageName)));
        return resources.getIdentifier( imageName, "drawable", packageName);
    }

    public static Drawable getDrawableFromResource(int resID) {
        try {
            return resources.getDrawable(resID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
