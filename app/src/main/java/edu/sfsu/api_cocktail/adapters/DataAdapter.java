package edu.sfsu.api_cocktail.adapters;

import static edu.sfsu.api_cocktail.DetailActivity.EXTRA_CATEGORY;
import static edu.sfsu.api_cocktail.DetailActivity.EXTRA_DRINK;
import static edu.sfsu.api_cocktail.DetailActivity.EXTRA_INSTRUCTIONS;
import static edu.sfsu.api_cocktail.DetailActivity.EXTRA_TYPE;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.sfsu.api_cocktail.DetailActivity;
import edu.sfsu.api_cocktail.R;
import edu.sfsu.api_cocktail.models.DrinkModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private final ArrayList<DrinkModel> model;

    public DataAdapter(ArrayList<DrinkModel> model) {
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }
    static Context context;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.v("LOG", "* * onBindViewHolder");

        DrinkModel mod = model.get(position);
//        Uri img = Uri.parse(model.get(position).getStrDrinkThumb());

        holder.strDrink.setText(String.format("%s", mod.getStrDrink()));

        holder.strDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(EXTRA_CATEGORY, mod.getStrCategory());
                intent.putExtra(EXTRA_DRINK, mod.getStrDrink());
                intent.putExtra(EXTRA_INSTRUCTIONS, mod.getStrInstructions());
                intent.putExtra(EXTRA_TYPE, mod.getStrIBA());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //       public final ImageView strDrinkThumb;
        public final TextView strCategory;
        public final TextView strDrink;
        public final TextView strInstructions;
        public final RecyclerView recyclerView;
        public ViewHolder(@NonNull View view) {
            super(view);

            strCategory = view.findViewById(R.id.strCategory);
            strDrink = view.findViewById(R.id.strDrink);
            strInstructions = view.findViewById(R.id.strInstructions);
            recyclerView = view.findViewById(R.id.recyclerView);

            context = view.getContext();
        }
    }
}