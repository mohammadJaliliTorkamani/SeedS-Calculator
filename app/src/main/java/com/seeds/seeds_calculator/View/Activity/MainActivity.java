package com.seeds.seeds_calculator.View.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seeds.seeds_calculator.Adapter.Adapter_OpenSource;
import com.seeds.seeds_calculator.Adapter.NavigationDrawerAdapter;
import com.seeds.seeds_calculator.BuildConfig;
import com.seeds.seeds_calculator.Model.License;
import com.seeds.seeds_calculator.Model.NagScreen;
import com.seeds.seeds_calculator.Network.ClientApi;
import com.seeds.seeds_calculator.Network.ServiceGenerator;
import com.seeds.seeds_calculator.R;
import com.seeds.seeds_calculator.Utils.Constants;
import com.seeds.seeds_calculator.Utils.ContextHelper;
import com.seeds.seeds_calculator.Utils.Helper;
import com.seeds.seeds_calculator.Utils.TextViewPlus;
import com.seeds.seeds_calculator.View.Fragment.StartFragment;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import ir.tapsell.sdk.bannerads.TapsellBannerType;
import ir.tapsell.sdk.bannerads.TapsellBannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ImageView expand;
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView open_source_layout_recyclerview;
    private RecyclerView.LayoutManager open_source_layout_recyclerview_layout_manager;
    private RecyclerView.Adapter open_source_recyclerview_adapter;
    private List<License> licenses = new LinkedList<>();
    private TapsellBannerView main_banner;
    private TapsellBannerView drawer_banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Helper.recordEventView("MainActivity");
        findViews();
        initialize();
        manageListeners();
        handleNagScreen();
    }

    private void findViews() {
        expand = findViewById(R.id.main_expand);
        drawerLayout = findViewById(R.id.main_drawerlayout);
        recyclerView = findViewById(R.id.navigation_drawer_rv);
        main_banner = findViewById(R.id.main_banner);
        drawer_banner = findViewById(R.id.navigation_drawer_banner);
    }

    private void manageListeners() {
        expand.setOnClickListener(v -> {
            Helper.recordEventClick("MainActivity", "expand icon");
            if (!drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.openDrawer(GravityCompat.START);
        });
    }

    private void initialize() {
        Toasty.Config.getInstance()
                .setToastTypeface(Typeface.createFromAsset(getAssets(), "fonts/syekan.otf"))
                .setTextSize(15)
                .allowQueue(true)
                .apply();

        main_banner.loadAd(this, BuildConfig.TAPSELL_MAIN_BANNER, TapsellBannerType.BANNER_320x50);
        drawer_banner.loadAd(MainActivity.this, BuildConfig.TAPSELL_DRAWER_BANNER, TapsellBannerType.BANNER_300x250);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new NavigationDrawerAdapter(item -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.closeDrawer(GravityCompat.START);
            handleDrawerItems(item.getPosition());
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_surface, new StartFragment()).commit();
    }

    private void handleDrawerItems(int position) {
        switch (position) {
            case 0:
                Helper.recordEventClick("MainActivity", "share option");
                handleShareApp();
                break;
            case 1:
                Helper.recordEventClick("MainActivity", "open source libraries option");
                licenses.clear();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View inflateView = LayoutInflater.from(this).inflate(R.layout.layout_open_source, null, false);
                builder.setView(inflateView);
                builder.setCancelable(true);
                try {

                    String[] fileNames = getAssets().list("licenses");
                    for (String fileName : fileNames)
                        licenses.add(new License(fileName, Helper.readAssetFile(fileName)));

                } catch (IOException e) {
                    e.printStackTrace();
                }

                open_source_layout_recyclerview = inflateView.findViewById(R.id.open_source_layout_recycler);
                open_source_recyclerview_adapter = new Adapter_OpenSource(licenses);
                open_source_layout_recyclerview_layout_manager = new LinearLayoutManager(ContextHelper.retrieveContext(), RecyclerView.VERTICAL, false);
                open_source_layout_recyclerview.setLayoutManager(open_source_layout_recyclerview_layout_manager);
                open_source_layout_recyclerview.setHasFixedSize(true);
                open_source_layout_recyclerview.setAdapter(open_source_recyclerview_adapter);
                builder.create().show();
                break;
            case 2:
                startActivity(new Intent(this, MoreActivity.class));
                break;

        }
    }

    private void handleShareApp() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app_prefix) +
                    "market://details?id=" + getPackageName());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        } catch (Exception e) {
            Log.d(Constants.TAG, e.getMessage());
        }
    }

    private void handleNagScreen() {
        int counter = Integer.parseInt(Helper.loadSetting(Constants._TABLE_USER, Constants._KEY_NAG_COUNTER, "0"));
        Log.d(Constants.TAG, "Nag Counter : " + counter);
        if (counter < Constants.NAG_THRESHOLD) {
            Helper.saveSetting(Constants._TABLE_USER, Constants._KEY_NAG_COUNTER, String.valueOf(counter + 1));
        } else {
            Helper.saveSetting(Constants._TABLE_USER, Constants._KEY_NAG_COUNTER, String.valueOf(0));
            ServiceGenerator.getInstance().createService(ClientApi.class).getNagScreen().enqueue(new Callback<NagScreen>() {
                @Override
                public void onResponse(Call<NagScreen> call, Response<NagScreen> response) {
                    if (response.body() != null) {
                        showNagScreen(response.body());
                    } else
                        Log.d(Constants.TAG, "null");
                }

                @Override
                public void onFailure(Call<NagScreen> call, Throwable t) {
                    Log.d(Constants.TAG, t.getMessage());
                }
            });
        }
    }

    private void showNagScreen(NagScreen nag) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_nag_screen, null, false);
        builder.setCancelable(false);
        builder.setView(view);
        TextViewPlus title = view.findViewById(R.id.nag_title);
        TextViewPlus description = view.findViewById(R.id.nag_description);
        CircleImageView image = view.findViewById(R.id.nag_image);
        TextViewPlus showMe = view.findViewById(R.id.nag_show_em);
        TextViewPlus no = view.findViewById(R.id.nag_no);
        title.setText(nag.getTitle());
        description.setText(nag.getDescription());
        Picasso.get().load(nag.getImageURL()).into(image);
        showMe.setOnClickListener(v -> {
            Helper.recordEventClick("MainActivity", "NAG yes option");
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(nag.getYesLink()));
            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ContextHelper.retrieveContext().startActivity(browserIntent);
        });
        Dialog dialog = builder.create();
        no.setOnClickListener(v -> {
            Helper.recordEventClick("MainActivity", "NAG no option");
            dialog.dismiss();
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        Helper.toast("به امید دیدار !", Constants.ToastMode.SUCCESS);
        super.onDestroy();
    }
}