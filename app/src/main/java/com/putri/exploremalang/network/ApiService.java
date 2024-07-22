package com.putri.exploremalang.network;

import com.putri.exploremalang.model.Wisata;
import com.putri.exploremalang.model.WisataResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("wisata")
    Call<WisataResponse> getWisataList();
}