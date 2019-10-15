package app.pavel.handbooklivedataroom.utils;

import android.app.Application;
import android.content.res.Resources;
import android.util.Log;
import android.widget.ImageView;

public class HandbookLiveDataRoom extends Application {

    private static String packageName;
    private static Resources resources;

    @Override
    public void onCreate() {
        super.onCreate();

        resources = getResources();
        packageName = getPackageName();
    }

    public static Resources getHandbookLiveDataRoomResources() {
        return resources;
    }

    public static int setImageInImageView(ImageView imageView, String imageName) {
        int resId = resources.getIdentifier(packageName + ":drawable/" + imageName,
                null,null);
        // Log.i("setImageInImageView", String.valueOf(resId));
        return resId;
    }

}
