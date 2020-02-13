package com.dassumpca.pokedanapp.Listener;

import com.dassumpca.pokedanapp.Model.Pokemon;

import java.util.List;

public interface AllPokemonListener {
        void onSuccess(List<Pokemon> pokemons);
        void onFailure(String errorMessage);
}
