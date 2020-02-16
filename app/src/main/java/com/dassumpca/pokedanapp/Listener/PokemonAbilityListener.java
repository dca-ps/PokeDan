package com.dassumpca.pokedanapp.Listener;

import com.dassumpca.pokedanapp.Model.Ability;
import com.dassumpca.pokedanapp.Model.Specie;

public interface PokemonAbilityListener {
        void onSuccessAbility(Ability ability);
        void onFailureAbility(String errorMessage);
}
