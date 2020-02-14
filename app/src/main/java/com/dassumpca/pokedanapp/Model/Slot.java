package com.dassumpca.pokedanapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Slot implements Serializable {

    @SerializedName("slot")
    private int slot;

    @SerializedName("type")
    private Type tipo;


    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }
}
