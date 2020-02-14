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
import androidx.recyclerview.widget.RecyclerView;

import com.dassumpca.pokedanapp.Enum.ColorEnum;
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
            holder.pokemonNameTV.setText(selectedPokemon.getId() + " - " + selectedPokemon.getNome());
            if(selectedPokemon.getEspecie() != null && selectedPokemon.getEspecie().getCor() != null)
                holder.cardView.setCardBackgroundColor(Color.parseColor(ColorEnum.valueOf(selectedPokemon.getEspecie().getCor().getNome()).getCor()));
            Picasso.get()
                    .load(selectedPokemon.getImagens().getFrente())
                    .placeholder(R.drawable.pokeball)
                    .into(holder.pokemonImageIV);
        }

    }

    @Override
    public int getItemCount() {
        if (pokemonList == null)
            return 0;
        return pokemonList.size();
    }

    public class PokeViewHolder extends RecyclerView.ViewHolder {
        TextView pokemonNameTV;
        ImageView pokemonImageIV;
        LinearLayout loadingLL;
        ConstraintLayout pokemonCL;
        CardView cardView;

        public PokeViewHolder(View itemView) {
            super(itemView);
            pokemonNameTV = itemView.findViewById(R.id.pokemonNameTV);
            pokemonImageIV = itemView.findViewById(R.id.pokemonImageIV);
            loadingLL = itemView.findViewById(R.id.loadingLL);
            pokemonCL = itemView.findViewById(R.id.pokemonCL);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
