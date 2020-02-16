package com.dassumpca.pokedanapp.Model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Ability implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("effect_entries")
    private List<Effect> effects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }
}
