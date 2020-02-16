package com.dassumpca.pokedanapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sprite implements Serializable {

   @SerializedName("back_default")
    private String tras;

    @SerializedName("front_default")
    private String frente;


    @SerializedName("back_female")
    private String trasFemea;

    @SerializedName("front_female")
    private String frenteFemea;


    @SerializedName("back_shiny")
    private String trasShiny;

    @SerializedName("front_shiny")
    private String frenteShiny;


    @SerializedName("back_shiny_female")
    private String trasFemeaShiny;

    @SerializedName("front_shiny_female")
    private String frenteFemeaShiny;


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

    public String getTrasFemea() {
        return trasFemea;
    }

    public void setTrasFemea(String trasFemea) {
        this.trasFemea = trasFemea;
    }

    public String getFrenteFemea() {
        return frenteFemea;
    }

    public void setFrenteFemea(String frenteFemea) {
        this.frenteFemea = frenteFemea;
    }

    public String getTrasShiny() {
        return trasShiny;
    }

    public void setTrasShiny(String trasShiny) {
        this.trasShiny = trasShiny;
    }

    public String getFrenteShiny() {
        return frenteShiny;
    }

    public void setFrenteShiny(String frenteShiny) {
        this.frenteShiny = frenteShiny;
    }

    public String getTrasFemeaShiny() {
        return trasFemeaShiny;
    }

    public void setTrasFemeaShiny(String trasFemeaShiny) {
        this.trasFemeaShiny = trasFemeaShiny;
    }

    public String getFrenteFemeaShiny() {
        return frenteFemeaShiny;
    }

    public void setFrenteFemeaShiny(String frenteFemeaShiny) {
        this.frenteFemeaShiny = frenteFemeaShiny;
    }

    public List<String> getSpriteList(){
        List<String> spriteList = new ArrayList<>();
        spriteList.add(frente);
        spriteList.add(tras);
        spriteList.add(frenteFemea);
        spriteList.add(trasFemea);

        spriteList.add(frenteShiny);
        spriteList.add(trasShiny);
        spriteList.add(frenteFemeaShiny);
        spriteList.add(trasFemeaShiny);

        return spriteList;
    }
}
