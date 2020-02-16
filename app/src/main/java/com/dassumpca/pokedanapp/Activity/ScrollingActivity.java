package com.dassumpca.pokedanapp.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.dassumpca.pokedanapp.Adapter.PokemonAbilitiesAdapter;
import com.dassumpca.pokedanapp.Adapter.PokemonTypeListAdapater;
import com.dassumpca.pokedanapp.Enum.PokemonColorEnum;
import com.dassumpca.pokedanapp.Listener.PokemonAbilityListener;
import com.dassumpca.pokedanapp.Listener.PokemonSpecieListener;
import com.dassumpca.pokedanapp.Model.Ability;
import com.dassumpca.pokedanapp.Model.BaseAbility;
import com.dassumpca.pokedanapp.Model.BaseStat;
import com.dassumpca.pokedanapp.Model.Pokemon;
import com.dassumpca.pokedanapp.Model.Specie;
import com.dassumpca.pokedanapp.Model.Sprite;
import com.dassumpca.pokedanapp.Presenter.MainPresenter;
import com.dassumpca.pokedanapp.R;
import com.dassumpca.pokedanapp.Utils.Utils;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollingActivity extends AppCompatActivity implements PokemonSpecieListener, PokemonAbilityListener {

    final static String POKEMON_EXTRA_KEY = "POKEMON_EXTRA_KEY";
    final static String SPECIE_EXTRA_KEY = "SPECIE_EXTRA_KEY";

    public static void start(Activity activity, Pokemon pokemon, Specie specie) {
        Intent intent = new Intent(activity, ScrollingActivity.class);
        intent.putExtra(POKEMON_EXTRA_KEY, pokemon);
        intent.putExtra(SPECIE_EXTRA_KEY, specie);
        activity.startActivity(intent);
    }

    Pokemon pokemon;
    Specie specie;
    MainPresenter presenter;
    List<Ability> allAbilities;

    PokemonAbilitiesAdapter abilitiesAdapter;
    PokemonTypeListAdapater typesAdapter;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_slider)
    ImageSlider imageSlider;

    @BindView(R.id.hpValueTV)
    TextView hpValueTV;
    @BindView(R.id.attackValueTV)
    TextView attackValueTV;
    @BindView(R.id.defenseValueTV)
    TextView defenseValueTV;
    @BindView(R.id.specialAttackValueTV)
    TextView specialAttackValueTV;
    @BindView(R.id.specialDefenseValueTV)
    TextView specialDefenseValueTV;
    @BindView(R.id.speedValueTV)
    TextView speedValueTV;

    @BindView(R.id.abilitiesRV)
    RecyclerView abilitiesRV;

    @BindView(R.id.typesRV)
    RecyclerView typesRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        pokemon = (Pokemon) getIntent().getSerializableExtra(POKEMON_EXTRA_KEY);
        specie = (Specie) getIntent().getSerializableExtra(SPECIE_EXTRA_KEY);
        presenter = new MainPresenter();
        allAbilities = new ArrayList<>();
        abilitiesAdapter = new PokemonAbilitiesAdapter(this, allAbilities);
        typesAdapter = new PokemonTypeListAdapater(this, pokemon.getTipos());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Utils.capitalize(pokemon.getNome()));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (specie != null) {
            colorScreen();
        } else {
            presenter.getPokemonEspecie(pokemon.getEspecie().getNome(), this);
        }

        ArrayList<SlideModel> imageList = new ArrayList<SlideModel>();
        for (String image : pokemon.getImagens().getSpriteList()) {
            if (image != null) {
                imageList.add(new SlideModel(image));
            }
        }
        imageSlider.setImageList(imageList, true);


        for(BaseStat stat : pokemon.getStats()){
            if(stat.getStat().getName().equalsIgnoreCase("hp")){
                hpValueTV.setText(String.valueOf(stat.getBaseStat()));
            }
            else if (stat.getStat().getName().equalsIgnoreCase("special-defense")){
                specialDefenseValueTV.setText(String.valueOf(stat.getBaseStat()));
            }
            else if (stat.getStat().getName().equalsIgnoreCase("speed")){
                speedValueTV.setText(String.valueOf(stat.getBaseStat()));
            }
            else if (stat.getStat().getName().equalsIgnoreCase("special-attack")){
                specialAttackValueTV.setText(String.valueOf(stat.getBaseStat()));
            }
            else if (stat.getStat().getName().equalsIgnoreCase("defense")){
                defenseValueTV.setText(String.valueOf(stat.getBaseStat()));
            }
            else if (stat.getStat().getName().equalsIgnoreCase("attack")){
                attackValueTV.setText(String.valueOf(stat.getBaseStat()));
            }
        }


        for(BaseAbility ability : pokemon.getAbilities()){
            presenter.getPokemonAbility(ability.getAbility().getName(), this);
        }


        abilitiesRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        abilitiesRV.setAdapter(abilitiesAdapter);

        typesRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        typesRV.setAdapter(typesAdapter);
    }


    void colorScreen() {
        PokemonColorEnum pokemonColorEnum = PokemonColorEnum.valueOf(specie.getCor().getNome());
        int pokemonColor = Color.parseColor(pokemonColorEnum.getCor());
        toolbar.setBackgroundColor(pokemonColor);

    }

    @Override
    public void onSuccessSpecie(Specie specie) {
        this.specie = specie;
        colorScreen();
    }

    @Override
    public void onFailureSpecie(String errorMessage) {
        //TODO Implementar tratamento de erro
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onSuccessAbility(Ability ability) {
        allAbilities.add(ability);
        abilitiesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailureAbility(String errorMessage) {
        //TODO Implementar tratamento de erro
    }
}
