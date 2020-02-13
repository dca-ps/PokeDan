package com.dassumpca.pokedanapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {


    @SerializedName("id")
    private int id;

    @SerializedName("order")
    private int ordem;

    @SerializedName("name")
    private String nome;

    @SerializedName("sprites")
    private Sprite imagens;

    @SerializedName("types")
    private List<Slot> tipos;




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

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
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
}
