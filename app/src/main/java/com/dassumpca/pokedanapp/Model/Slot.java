package com.dassumpca.pokedanapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Slot implements Serializable {

    @SerializedName("slot")
    private int slot;

    @SerializedName("type")
    private Type tipo;
}
