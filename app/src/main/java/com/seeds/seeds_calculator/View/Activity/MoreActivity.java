package com.seeds.seeds_calculator.View.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seeds.seeds_calculator.Adapter.MoreAppsAdapter;
import com.seeds.seeds_calculator.Model.MoreApp;
import com.seeds.seeds_calculator.Network.ClientApi;
import com.seeds.seeds_calculator.Network.ServiceGenerator;
import com.seeds.seeds_calculator.R;
import com.seeds.seeds_calculator.Utils.Constants;
import com.seeds.seeds_calculator.Utils.Helper;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<MoreApp> list = new LinkedList<>();
    private ProgressBar progressBar;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        findViews();
        initialize();
    }

    private void initialize() {
        Helper.recordEventView("MoreActivity");
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MoreAppsAdapter(list);
        recyclerView.setAdapter(adapter);

        ServiceGenerator.getInstance().createService(ClientApi.class).getMoreAppLiost().enqueue(new Callback<List<MoreApp>>() {
            @Override
            public void onResponse(Call<List<MoreApp>> call, Response<List<MoreApp>> response) {
                if (response.body() != null) {
                    constraintLayout.setBackgroundColor(Color.parseColor(response.body().get(0).getContainerColor()));
                    list.clear();
                    list.addAll(response.body());
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.d(Constants.TAG, "null");
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<MoreApp>> call, Throwable t) {
                Log.d(Constants.TAG, t.getMessage());
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void findViews() {
        recyclerView = findViewById(R.id.more_apps_rv);
        progressBar = findViewById(R.id.more_apps_pb);
        constraintLayout = findViewById(R.id.more_apps_view);
    }
}
