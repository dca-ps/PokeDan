package com.dassumpca.pokedanapp.Presenter;

import com.dassumpca.pokedanapp.Listener.AllPokemonListener;
import com.dassumpca.pokedanapp.Model.Pokemon;
import com.dassumpca.pokedanapp.Service.RetrofitConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    public void getAllPokemon(int pageSize, int offset, AllPokemonListener listener) {
        final int[] completed = {0};
        ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();

        pokemonList.clear();

        for (int i = offset; i < pageSize + offset; i++) {

            Call<Pokemon> call = new RetrofitConfig().getPokedexService().getPokemon(i);

            call.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    Pokemon pokemon = response.body();
                    pokemonList.add(pokemon);
                    completed[0]++;

                    if (completed[0] == pageSize) {
                        listener.onSuccess(pokemonList);
                    }
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    completed[0]++;
                    listener.onFailure(t.getMessage());
                }
            });
        }
    }
}
