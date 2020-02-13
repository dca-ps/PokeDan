package com.dassumpca.pokedanapp.Activity;

import android.os.Bundle;

import com.dassumpca.pokedanapp.Listener.AllPokemonListener;
import com.dassumpca.pokedanapp.Model.Pokemon;
import com.dassumpca.pokedanapp.Presenter.MainPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.dassumpca.pokedanapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AllPokemonListener {

    final int pageSize = 30;
    int offSet = 1;

    MainPresenter presenter = new MainPresenter();
    ArrayList<Pokemon> allPokemon = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        allPokemon.clear();
        presenter.getAllPokemon(pageSize, offSet, this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offSet += pageSize;
                presenter.getAllPokemon(pageSize, offSet, MainActivity.this);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onSuccess(List<Pokemon> pokemons) {
        allPokemon.addAll(pokemons);


    }

    @Override
    public void onFailure(String errorMessage) {

    }
}
