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

import com.dassumpca.pokedanapp.Enum.PokemonColorEnum;
import com.dassumpca.pokedanapp.Enum.TypeColorEnum;
import com.dassumpca.pokedanapp.Model.Slot;
import com.dassumpca.pokedanapp.R;
import com.dassumpca.pokedanapp.Utils.Utils;

import java.util.List;

public class PokemonTypeListAdapater extends RecyclerView.Adapter<PokemonTypeListAdapater.TypeViewHolder> {

    private List<Slot> tipos;
    private Context context;
    PokemonTypeListAdapater(Context context, List<Slot> tipos){
        this.tipos = tipos;
        this.context = context;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type_list, parent, false);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        Slot selectedType = tipos.get(position);

        String tipeString = selectedType.getTipo().getNome();

        holder.pokemonTypeTV.setText(Utils.capitalize(tipeString));

        holder.pokemonTypeCV.setCardBackgroundColor(Color.parseColor(TypeColorEnum.valueOf(tipeString).getCor()));


    }

    @Override
    public int getItemCount() {
        if (tipos == null)
            return 0;
        return tipos.size();
    }

    class TypeViewHolder extends RecyclerView.ViewHolder {
        TextView pokemonTypeTV;

        CardView pokemonTypeCV;

        TypeViewHolder(View itemView) {
            super(itemView);
            pokemonTypeTV = itemView.findViewById(R.id.pokemonTypeTV);
            pokemonTypeCV = itemView.findViewById(R.id.pokemonTypeCV);

        }
    }
}
