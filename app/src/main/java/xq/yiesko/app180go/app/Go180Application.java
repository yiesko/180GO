package xq.yiesko.app180go.app;

import android.app.Application;

import com.google.android.material.color.DynamicColors;

public class Go180Application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}