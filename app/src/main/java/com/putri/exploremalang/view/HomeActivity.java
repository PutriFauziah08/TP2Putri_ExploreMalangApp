package com.putri.exploremalang.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.putri.exploremalang.R;
import com.putri.exploremalang.adapter.WisataAdapter;
import com.putri.exploremalang.model.Wisata;
import com.putri.exploremalang.model.WisataResponse;
import com.putri.exploremalang.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://mock.apidog.com/m1/607382-573761-default/";
    private List<Wisata> wisataList;
    private WisataAdapter adapter;
    private RecyclerView recyclerView;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        recyclerView = findViewById(R.id.recyclerView);
        searchBar = findViewById(R.id.searchBar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<WisataResponse> call = apiService.getWisataList();
        call.enqueue(new Callback<WisataResponse>() {
            @Override
            public void onResponse(Call<WisataResponse> call, Response<WisataResponse> response) {
                if (response.isSuccessful()) {
                    WisataResponse wisataResponse = response.body();
                    if (wisataResponse != null) {
                        wisataList = wisataResponse.getWisata();
                        setupRecyclerView();
                    }
                }
            }

            @Override
            public void onFailure(Call<WisataResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter != null) {
                    adapter.filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        findViewById(R.id.categoryFilter).setOnClickListener(v -> {
            showCategoryPopup(v);
        });
    }

    private void setupRecyclerView() {
        adapter = new WisataAdapter(wisataList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void showCategoryPopup(View anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);
        popupMenu.getMenuInflater().inflate(R.menu.category_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (adapter != null) {
                    String category = "";
                    switch (item.getItemId()) {
                        case R.id.kategori_all:
                            category = "";
                            break;
                        case R.id.kategori_alam:
                            category = "Alam";
                            break;
                        case R.id.kategori_sejarah:
                            category = "Sejarah";
                            break;
                        case R.id.kategori_kuliner:
                            category = "Kuliner";
                            break;
                    }
                    adapter.filterByCategory(category);
                }
                return true;
            }
        });
        popupMenu.show();
    }
}
