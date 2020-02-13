package com.dassumpca.pokedanapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sprite implements Serializable {

    @SerializedName("back_default")
    private String tras;

    @SerializedName("front_default")
    private String frente;


    public String getTras() {
        return tras;
    }

    public void setTras(String tras) {
        this.tras = tras;
    }

    public String getFrente() {
        return frente;
    }

    public void setFrente(String frente) {
        this.frente = frente;
    }


}
