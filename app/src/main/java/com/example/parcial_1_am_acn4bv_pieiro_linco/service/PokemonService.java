package com.example.parcial_1_am_acn4bv_pieiro_linco.service;

import com.example.parcial_1_am_acn4bv_pieiro_linco.model.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonService {
    @GET("{dexNumOrName}/")
    Call<Pokemon> getPokemonByDexNumOrName(@Path("dexNumOrName") String dexNumOrName);
}