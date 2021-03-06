package com.dassumpca.pokedanapp.Service;

import com.dassumpca.pokedanapp.Model.Ability;
import com.dassumpca.pokedanapp.Model.Pokemon;
import com.dassumpca.pokedanapp.Model.Specie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokedexService {
    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id")int id);

    @GET("pokemon-species/{name}")
    Call<Specie> getSpecie(@Path("name")String name);

    @GET("ability/{name}")
    Call<Ability> getAbility(@Path("name")String name);
}
