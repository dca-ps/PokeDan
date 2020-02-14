package com.dassumpca.pokedanapp.Presenter;

import com.dassumpca.pokedanapp.Listener.AllPokemonListener;
import com.dassumpca.pokedanapp.Listener.PokemonSpecieListener;
import com.dassumpca.pokedanapp.Model.Pokemon;
import com.dassumpca.pokedanapp.Model.Specie;
import com.dassumpca.pokedanapp.Service.PokedexService;
import com.dassumpca.pokedanapp.Service.RetrofitConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private PokedexService service = new RetrofitConfig().getPokedexService();

    public void getAllPokemon(int pageSize, int offset, AllPokemonListener listener, PokemonSpecieListener listenerSpecie) {
        final int[] completed = {0};
        ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();

        pokemonList.clear();

        for (int i = offset; i < pageSize + offset; i++) {

            Call<Pokemon> call = service.getPokemon(i);

            call.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    Pokemon pokemon = response.body();
                    if(pokemon != null){
                        getPokemonEspecie(pokemon.getEspecie().getNome(), listenerSpecie);
                        pokemonList.add(pokemon);
                    }

                    completed[0]++;

                    if (completed[0] == pageSize) {
                        listener.onSuccessAllPokemon(pokemonList);
                    }
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    completed[0]++;
                    listener.onFailureAllPokemon(t.getMessage());
                }
            });
        }
    }



    public void getPokemonEspecie(String name, PokemonSpecieListener listener){

        Call<Specie> call = service.getSpecie(name);

        call.enqueue(new Callback<Specie>() {
            @Override
            public void onResponse(Call<Specie> call, Response<Specie> response) {
                Specie specie = response.body();

                listener.onSuccessSpecie(specie);

            }

            @Override
            public void onFailure(Call<Specie> call, Throwable t) {
                listener.onFailureSpecie(t.getMessage());
            }
        });

    }
}
