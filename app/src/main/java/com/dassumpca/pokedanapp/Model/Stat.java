package com.dassumpca.pokedanapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Stat implements Serializable {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
