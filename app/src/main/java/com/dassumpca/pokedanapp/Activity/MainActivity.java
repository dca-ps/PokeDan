package com.dassumpca.pokedanapp.Activity;

import android.os.Bundle;

import com.dassumpca.pokedanapp.Adapter.PokemonListAdapter;
import com.dassumpca.pokedanapp.Listener.AllPokemonListener;
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

public class MainActivity extends AppCompatActivity implements AllPokemonListener, PokemonSpecieListener {

    final int pageSize = 20;
    int offSet = 1;

    boolean isLoading = true;

    @BindView(R.id.mainRV)
    RecyclerView mainRV;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    MainPresenter presenter;
    ArrayList<Pokemon> allPokemon;

    PokemonListAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);


        allPokemon = new ArrayList<>();
        presenter = new MainPresenter();

        pokemonAdapter = new PokemonListAdapter(this, allPokemon);

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
        allPokemon.addAll(pokemons);
        allPokemon.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));

        pokemonAdapter.notifyDataSetChanged();
        isLoading = false;

    }

    @Override
    public void onFailureAllPokemon(String errorMessage) {

    }



    @Override
    public void onSuccessSpecie(Specie specie) {
        allPokemon.forEach(( pokemon) -> {
            if(pokemon != null && pokemon.getEspecie().getNome().equalsIgnoreCase(specie.getNome())){
                pokemon.setEspecie(specie);
                pokemonAdapter.notifyItemChanged(allPokemon.indexOf(pokemon));
            }
        });

    }

    @Override
    public void onFailureSpecie(String errorMessage) {

    }
}
