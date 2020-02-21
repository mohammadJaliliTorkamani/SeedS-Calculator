package com.seeds.seeds_calculator.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.flurry.android.FlurryAgent;
import com.google.android.material.snackbar.Snackbar;
import com.seeds.seeds_calculator.Model.DrawerItem;
import com.seeds.seeds_calculator.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

import static android.content.Context.MODE_PRIVATE;

public class Helper {
    private static SharedPreferences preferences;

    public static List<DrawerItem> getDrawerItems() {
        List<DrawerItem> list = new LinkedList<>();
        list.add(new DrawerItem(0, "اشتراک ماشین حساب", R.drawable.ic_share_icon));
        list.add(new DrawerItem(1, "کنابخانه های منبع باز", R.drawable.license));
        list.add(new DrawerItem(2, "بیشتر", R.drawable.more));
        return list;
    }

    public static void showSnackbar(View view) {
        Snackbar.make(view, "به زودی !", Snackbar.LENGTH_LONG).show();
    }

    public static void toast(String str, Constants.ToastMode toastMode) {
        switch (toastMode) {
            case NORMAL:
                Toasty.normal(ContextHelper.retrieveContext(), str).show();

                break;
            case INFO:
                Toasty.info(ContextHelper.retrieveContext(), str).show();

                break;
            case ERROR:
                Toasty.error(ContextHelper.retrieveContext(), str).show();

                break;
            case SUCCESS:
                Toasty.success(ContextHelper.retrieveContext(), str).show();

                break;
            case WARNING:
                Toasty.warning(ContextHelper.retrieveContext(), str).show();

                break;
        }
    }

    public static void toast(@StringRes int string_resource, @NonNull Constants.ToastMode toastMode) {
        Context context = ContextHelper.retrieveContext();
        toast(context.getString(string_resource), toastMode);
    }

    public static String readAssetFile(String fileName) {
        StringBuilder toReturn = new StringBuilder();
        try {
            InputStream is = ContextHelper.retrieveContext().getAssets().open("licenses/" + fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer);
            toReturn.append(text);
        } catch (IOException e) {
            Log.d(Constants.TAG, e.getMessage());
        }
        return toReturn.toString();
    }

    public static void saveSetting(@NonNull String table, @NonNull String key, String value) {
        preferences = ContextHelper.retrieveContext().getSharedPreferences(table, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
        editor.apply();
    }

    public static String loadSetting(@NonNull String table, @NonNull String key, String defaultValue) {
        preferences = ContextHelper.retrieveContext().getSharedPreferences(table, MODE_PRIVATE);
        return preferences.getString(key, defaultValue);
    }

    public static void recordEventClick(String containerPage, String clickedViewName) {
        Map<String, String> map = new HashMap<>();
        map.put(containerPage, clickedViewName + " clicked");
    }

    public static void recordEventView(String pageName) {
        FlurryAgent.logEvent(pageName + " viewed");
    }
}
