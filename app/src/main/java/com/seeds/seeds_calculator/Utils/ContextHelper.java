package com.seeds.seeds_calculator.Utils;

import android.app.Application;
import android.content.Context;

import com.flurry.android.FlurryAgent;
import com.pushpole.sdk.PushPole;
import com.seeds.seeds_calculator.BuildConfig;

import ir.tapsell.sdk.Tapsell;

public class ContextHelper extends Application {
    private static Context context;

    /**
     * retrieves context
     *
     * @return context
     */
    public static Context retrieveContext() {
        return context;
    }

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        PushPole.initialize(this, true);
        Tapsell.initialize(this, BuildConfig.TAPSELL_KEY);
        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, BuildConfig.FLURRY_KEY);
        FlurryAgent.setUserId(PushPole.getId(this));
    }
}
