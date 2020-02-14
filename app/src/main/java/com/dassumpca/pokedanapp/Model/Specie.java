package com.dassumpca.pokedanapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Specie implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String nome;

    @SerializedName("evolution_chain")
    private EvolutionChain cadeiaEvolucao;

    @SerializedName("color")
    private Color cor;


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

    public EvolutionChain getCadeiaEvolucao() {
        return cadeiaEvolucao;
    }

    public void setCadeiaEvolucao(EvolutionChain cadeiaEvolucao) {
        this.cadeiaEvolucao = cadeiaEvolucao;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }
}
