package com.seeds.seeds_calculator.Network;

import android.content.Context;

import com.seeds.seeds_calculator.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class Interceptor_VersionCode implements Interceptor {
    private static Interceptor_VersionCode instance;
    private Context context;

    private Interceptor_VersionCode(Context context) {
        this.context = context;
    }

    public static Interceptor_VersionCode getInstance(Context context) {
        if (instance == null)
            instance = new Interceptor_VersionCode(context);
        return instance;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String versionCode = String.valueOf(BuildConfig.VERSION_CODE);
        Request request = chain.request();
        request = request.newBuilder().header("versionCode", versionCode).build();
        return chain.proceed(request);
    }
}
