package com.dassumpca.pokedanapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dassumpca.pokedanapp.Enum.PokemonColorEnum;
import com.dassumpca.pokedanapp.Listener.MainPokemonClickListener;
import com.dassumpca.pokedanapp.Model.Pokemon;
import com.dassumpca.pokedanapp.Model.Specie;
import com.dassumpca.pokedanapp.R;
import com.dassumpca.pokedanapp.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokeViewHolder> {

    private Context context;
    private List<Pokemon> pokemonList;
    private List<Specie> allSpecies;
    private MainPokemonClickListener listener;

    public PokemonListAdapter(Context context, List<Pokemon> pokemonList, List<Specie> allSpecies, MainPokemonClickListener listener){
        this.context = context;
        this.pokemonList = pokemonList;
        this.allSpecies = allSpecies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_list, parent, false);
        return new PokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokeViewHolder holder, int position) {
        Pokemon selectedPokemon = pokemonList.get(position);

        if(selectedPokemon == null){
            holder.loadingLL.setVisibility(View.VISIBLE);
            holder.pokemonCL.setVisibility(View.GONE);
            holder.cardView.setCardBackgroundColor(Color.WHITE);
        }
        else{
            holder.pokemonCL.setVisibility(View.VISIBLE);
            holder.loadingLL.setVisibility(View.GONE);

            String nomeString = selectedPokemon.getNome();

            holder.pokemonNameTV.setText(Utils.capitalize(nomeString));
            holder.pokemonNumberTV.setText("Nº " + selectedPokemon.getId());
            if(allSpecies.contains(selectedPokemon.getEspecie())){
                int specieIndex = allSpecies.indexOf(selectedPokemon.getEspecie());
                holder.cardView.setCardBackgroundColor(Color.parseColor(PokemonColorEnum.valueOf(allSpecies.get(specieIndex).getCor().getNome()).getCor()));
            }
            else{
                holder.cardView.setCardBackgroundColor(Color.WHITE);
            }

            holder.cardView.setOnClickListener(v -> {
                listener.onPokemonClick(selectedPokemon);
            });
            Picasso.get()
                    .load(selectedPokemon.getImagens().getFrente())
                    .placeholder(R.drawable.pokeball)
                    .into(holder.pokemonImageIV);

            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

            holder.pokemonTypesRV.setLayoutManager(layoutManager);
            PokemonTypeListAdapater adapater = new PokemonTypeListAdapater(context, selectedPokemon.getTipos());
            holder.pokemonTypesRV.setAdapter(adapater);

        }

    }

    @Override
    public int getItemCount() {
        if (pokemonList == null)
            return 0;
        return pokemonList.size();
    }

    class PokeViewHolder extends RecyclerView.ViewHolder {
        TextView pokemonNameTV;
        TextView pokemonNumberTV;
        ImageView pokemonImageIV;
        LinearLayout loadingLL;
        ConstraintLayout pokemonCL;
        CardView cardView;
        RecyclerView pokemonTypesRV;

        PokeViewHolder(View itemView) {
            super(itemView);
            pokemonNameTV = itemView.findViewById(R.id.pokemonNameTV);
            pokemonImageIV = itemView.findViewById(R.id.pokemonImageIV);
            loadingLL = itemView.findViewById(R.id.loadingLL);
            pokemonCL = itemView.findViewById(R.id.pokemonCL);
            cardView = itemView.findViewById(R.id.cardView);
            pokemonNumberTV = itemView.findViewById(R.id.pokemonNumberTV);
            pokemonTypesRV = itemView.findViewById(R.id.pokemonTypesRV);
        }
    }
}
