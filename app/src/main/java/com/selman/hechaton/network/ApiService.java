package com.selman.hechaton.network;

import com.selman.hechaton.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("pedler")
    Call<List<Product>> getProducts();
}
