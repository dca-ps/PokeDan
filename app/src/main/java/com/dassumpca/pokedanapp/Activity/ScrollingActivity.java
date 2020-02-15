package com.dassumpca.pokedanapp.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.dassumpca.pokedanapp.Enum.PokemonColorEnum;
import com.dassumpca.pokedanapp.Model.Pokemon;
import com.dassumpca.pokedanapp.Model.Specie;
import com.dassumpca.pokedanapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollingActivity extends AppCompatActivity {

    final static String POKEMON_EXTRA_KEY = "POKEMON_EXTRA_KEY";
    final static String SPECIE_EXTRA_KEY = "SPECIE_EXTRA_KEY";



    public static void start(Activity activity, Pokemon pokemon, Specie specie){
        Intent intent = new Intent(activity, ScrollingActivity.class);
        intent.putExtra(POKEMON_EXTRA_KEY, pokemon);
        intent.putExtra(SPECIE_EXTRA_KEY, specie);
        activity.startActivity(intent);
    }



    Pokemon pokemon;
    Specie specie;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);


        pokemon = (Pokemon) getIntent().getSerializableExtra(POKEMON_EXTRA_KEY);
        specie = (Specie) getIntent().getSerializableExtra(SPECIE_EXTRA_KEY);


        int pokemonColor = Color.parseColor(PokemonColorEnum.valueOf(specie.getCor().getNome()).getCor());
        toolbar.setTitle(pokemon.getNome());
        toolbar.setBackgroundColor(pokemonColor);
        collapsingToolbarLayout.setBackgroundColor(pokemonColor);
        collapsingToolbarLayout.setContentScrimColor(pokemonColor);
        collapsingToolbarLayout.setStatusBarScrimColor(pokemonColor);
    }
}
