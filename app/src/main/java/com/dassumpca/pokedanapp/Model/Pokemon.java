package com.dassumpca.pokedanapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {


    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String nome;

    @SerializedName("sprites")
    private Sprite imagens;

    @SerializedName("types")
    private List<Slot> tipos;

    @SerializedName("species")
    private Specie especie;

    @SerializedName("stats")
    private List<BaseStat> stats;

    @SerializedName("abilities")
    private List<BaseAbility> abilities;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sprite getImagens() {
        return imagens;
    }

    public void setImagens(Sprite imagens) {
        this.imagens = imagens;
    }

    public List<Slot> getTipos() {
        return tipos;
    }

    public void setTipos(List<Slot> tipos) {
        this.tipos = tipos;
    }

    public Specie getEspecie() {
        return especie;
    }

    public void setEspecie(Specie especie) {
        this.especie = especie;
    }

    public List<BaseStat> getStats() {
        return stats;
    }

    public void setStats(List<BaseStat> stats) {
        this.stats = stats;
    }

    public List<BaseAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<BaseAbility> abilities) {
        this.abilities = abilities;
    }
}
