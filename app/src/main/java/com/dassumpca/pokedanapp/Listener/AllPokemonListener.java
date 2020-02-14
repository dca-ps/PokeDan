package com.dassumpca.pokedanapp.Listener;

import com.dassumpca.pokedanapp.Model.Pokemon;

import java.util.List;

public interface AllPokemonListener {
        void onSuccessAllPokemon(List<Pokemon> pokemons);
        void onFailureAllPokemon(String errorMessage);
}
