package com.dassumpca.pokedanapp.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.dassumpca.pokedanapp.Adapter.PokemonListAdapter;
import com.dassumpca.pokedanapp.Listener.AllPokemonListener;
import com.dassumpca.pokedanapp.Listener.MainPokemonClickListener;
import com.dassumpca.pokedanapp.Listener.PokemonSpecieListener;
import com.dassumpca.pokedanapp.Model.Pokemon;
import com.dassumpca.pokedanapp.Model.Specie;
import com.dassumpca.pokedanapp.Presenter.MainPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.dassumpca.pokedanapp.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements AllPokemonListener, PokemonSpecieListener, MainPokemonClickListener {

    final int pageSize = 20;
    int offSet = 1;

    boolean isLoading = true;

    @BindView(R.id.mainRV)
    RecyclerView mainRV;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    MainPresenter presenter;
    ArrayList<Pokemon> allPokemon;
    ArrayList<Specie> allSpecies;


    PokemonListAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        allPokemon = new ArrayList<>();
        allSpecies = new ArrayList<>();
        presenter = new MainPresenter();

        pokemonAdapter = new PokemonListAdapter(this, allPokemon, allSpecies, this);

        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getApplicationContext());

        mainRV.setLayoutManager(layoutManager);
        mainRV.setAdapter(pokemonAdapter);

        mainRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == allPokemon.size() - 1) {
                        loadItens();
                        isLoading = true;
                    }
                }
            }
        });



        loadItens();
    }
    public void loadItens(){
        allPokemon.add(null);
        pokemonAdapter.notifyItemInserted(allPokemon.size() - 1);
        mainRV.scrollToPosition(allPokemon.size() - 1);
        presenter.getAllPokemon(pageSize, offSet, this, this);
        offSet += pageSize;

    }

    @Override
    public void onSuccessAllPokemon(List<Pokemon> pokemons) {
        if (allPokemon.size() > 0) {
            allPokemon.remove(allPokemon.size() - 1);
            int scrollPosition = allPokemon.size();
            pokemonAdapter.notifyItemRemoved(scrollPosition);
        }
        for(Pokemon pokemon : pokemons){
            if(!allPokemon.contains(pokemon)){
                allPokemon.addAll(pokemons);
            }
        }
        allPokemon.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));

        pokemonAdapter.notifyDataSetChanged();
        isLoading = false;

    }

    @Override
    public void onFailureAllPokemon(String errorMessage) {
        //TODO Implementar tratamento de erro
    }



    @Override
    public void onSuccessSpecie(Specie specie) {
        if(!allSpecies.contains(specie)){
            allSpecies.add(specie);
            pokemonAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailureSpecie(String errorMessage) {
       //TODO Implementar tratamento de erro
    }

    @Override
    public void onPokemonClick(Pokemon pokemon) {
        Specie specie = null;
        if(allSpecies.contains(pokemon.getEspecie())){
            int specieIndex = allSpecies.indexOf(pokemon.getEspecie());
            specie = allSpecies.get(specieIndex);
        }
        ScrollingActivity.start(this, pokemon, specie);
    }
}
