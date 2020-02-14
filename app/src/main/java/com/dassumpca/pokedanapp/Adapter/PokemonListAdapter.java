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
import com.dassumpca.pokedanapp.Model.Pokemon;
import com.dassumpca.pokedanapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokeViewHolder> {

    private Context context;
    private List<Pokemon> pokemonList;

    public PokemonListAdapter(Context context, List<Pokemon> pokemonList){
        this.context = context;
        this.pokemonList = pokemonList;
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
        }
        else{
            holder.pokemonCL.setVisibility(View.VISIBLE);
            holder.loadingLL.setVisibility(View.GONE);

            String nomeString = selectedPokemon.getNome();

            String upperString = nomeString.substring(0, 1).toUpperCase() + nomeString.substring(1).toLowerCase();


            holder.pokemonNameTV.setText(upperString);
            holder.pokemonNumberTV.setText("Nº " + selectedPokemon.getId());
            if(selectedPokemon.getEspecie() != null && selectedPokemon.getEspecie().getCor() != null)
                holder.cardView.setCardBackgroundColor(Color.parseColor(PokemonColorEnum.valueOf(selectedPokemon.getEspecie().getCor().getNome()).getCor()));
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
