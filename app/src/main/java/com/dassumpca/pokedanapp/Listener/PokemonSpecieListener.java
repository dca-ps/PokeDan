package com.dassumpca.pokedanapp.Listener;

import com.dassumpca.pokedanapp.Model.Pokemon;
import com.dassumpca.pokedanapp.Model.Specie;

import java.util.List;

public interface PokemonSpecieListener {
    void onSuccessSpecie(Specie specie);
    void onFailureSpecie(String errorMessage);
}
