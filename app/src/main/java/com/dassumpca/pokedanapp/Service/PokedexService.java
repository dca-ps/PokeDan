package com.dassumpca.pokedanapp.Service;

import com.dassumpca.pokedanapp.Model.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokedexService {
    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id")int id);
    @GET("pokemon-species/{id}")
    Call<Pokemon> getSpecies(@Path("id")int id);
}
