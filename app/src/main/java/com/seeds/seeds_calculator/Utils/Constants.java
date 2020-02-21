package com.seeds.seeds_calculator.Utils;

public class Constants {
    public static final String TAG = "SeedS_Calculator_TAG";
    public static final int NAG_THRESHOLD = 5;
    public static final String _TABLE_USER = "User_Table";
    public static final String DEFAULT_FARSI_FONT_ASSET_ADDRESS = "fonts/syekan.otf";
    public static final String BASE_URL = "https://aban.dev/seeds_calculator/api/";
    public static final long CONNECT_TIME_OUT = 8;//unit : second
    public static final long WRITE_TIME_OUT = 8;//unit : s
    public static final long READ_TIME_OUT = 8;//unit : s
    public static final int CACHE_SIZE = 8 * 1024 * 1024; //unit : MB
    public static final String _KEY_NAG_COUNTER = "_key_nag_counter";
    public static final String _EVENT_KEY_CLICK = "event key click";

    public enum ToastMode {
        SUCCESS, INFO, WARNING, ERROR, NORMAL
    }
}
