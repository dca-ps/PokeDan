package com.dassumpca.pokedanapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseStat implements Serializable {

    @SerializedName("base_stat")
    private int baseStat;

    @SerializedName("effort")
    private int effort;

    @SerializedName("stat")
    private Stat stat;


    public int getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(int baseStat) {
        this.baseStat = baseStat;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
}
