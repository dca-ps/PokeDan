package com.dassumpca.pokedanapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dassumpca.pokedanapp.Enum.PokemonColorEnum;
import com.dassumpca.pokedanapp.Enum.TypeColorEnum;
import com.dassumpca.pokedanapp.Model.Ability;
import com.dassumpca.pokedanapp.Model.Slot;
import com.dassumpca.pokedanapp.R;
import com.dassumpca.pokedanapp.Utils.Utils;

import java.util.List;

public class PokemonAbilitiesAdapter extends RecyclerView.Adapter<PokemonAbilitiesAdapter.AbilitiesViewHolder> {


    private List<Ability> abilities;
    private Context context;

    public PokemonAbilitiesAdapter(Context context, List<Ability> abilities){
        this.abilities = abilities;
        this.context = context;
    }

    @NonNull
    @Override
    public AbilitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type_list, parent, false);
        return new PokemonAbilitiesAdapter.AbilitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbilitiesViewHolder holder, int position) {
        Ability selectedAbility = abilities.get(position);

        holder.pokemonTypeTV.setText(Utils.capitalize(selectedAbility.getName()));
        holder.pokemonTypeCV.setCardBackgroundColor(Color.parseColor(PokemonColorEnum.gray.getCor()));
    }

    @Override
    public int getItemCount() {
        if (abilities == null)
            return 0;
        return abilities.size();
    }

    class AbilitiesViewHolder extends RecyclerView.ViewHolder {
        TextView pokemonTypeTV;

        CardView pokemonTypeCV;

        AbilitiesViewHolder(View itemView) {
            super(itemView);
            pokemonTypeTV = itemView.findViewById(R.id.pokemonTypeTV);
            pokemonTypeCV = itemView.findViewById(R.id.pokemonTypeCV);

        }
    }
}
