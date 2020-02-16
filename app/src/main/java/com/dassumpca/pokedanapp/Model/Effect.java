package com.dassumpca.pokedanapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Effect implements Serializable {
    @SerializedName("effect")
    private String effect;

    @SerializedName("short_effect")
    private String short_effect;

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getShort_effect() {
        return short_effect;
    }

    public void setShort_effect(String short_effect) {
        this.short_effect = short_effect;
    }
}
