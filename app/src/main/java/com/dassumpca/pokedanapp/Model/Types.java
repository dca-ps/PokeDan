package com.dassumpca.pokedanapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Types implements Serializable {

    @SerializedName("types")
    private List<Slot> tipos;


    public List<Slot> getTipos() {
        return tipos;
    }

    public void setTipos(List<Slot> tipos) {
        this.tipos = tipos;
    }
}
