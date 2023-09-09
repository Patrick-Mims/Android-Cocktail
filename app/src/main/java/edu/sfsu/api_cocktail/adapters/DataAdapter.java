package edu.sfsu.api_cocktail.adapters;

import static edu.sfsu.api_cocktail.DetailActivity.EXTRA_CATEGORY;
import static edu.sfsu.api_cocktail.DetailActivity.EXTRA_DRINK;
import static edu.sfsu.api_cocktail.DetailActivity.EXTRA_IMAGE;
import static edu.sfsu.api_cocktail.DetailActivity.EXTRA_INSTRUCTIONS;
import static edu.sfsu.api_cocktail.DetailActivity.EXTRA_TYPE;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.sfsu.api_cocktail.DetailActivity;
import edu.sfsu.api_cocktail.R;
import edu.sfsu.api_cocktail.models.DrinkModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    static Context context;
    private final ArrayList<DrinkModel> model;

    public DataAdapter(ArrayList<DrinkModel> model) {
        this.model = model;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView strCategory;
        TextView strDrink;
        TextView strInstructions;
        RecyclerView recyclerView;
        public ImageView imageView;

        public ViewHolder(@NonNull View view) {
            super(view);

            this.imageView = view.findViewById(R.id.theImageView);
            // this.strCategory = view.findViewById(R.id.strCategory);
            this.strDrink = view.findViewById(R.id.strDrink);
            // this.strInstructions = view.findViewById(R.id.strInstructions);
            this.recyclerView = view.findViewById(R.id.recyclerView);

            context = view.getContext();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DrinkModel mod = model.get(position);

        holder.strDrink.setText(String.format("%s", mod.getStrDrink()));

        Picasso.get().load(Uri.parse(model.get(position).getStrDrinkThumb())).resize(400, 400).centerCrop().into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra(EXTRA_CATEGORY, mod.getStrCategory());
                intent.putExtra(EXTRA_DRINK, mod.getStrDrink());
                intent.putExtra(EXTRA_IMAGE, mod.getStrDrinkThumb());
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
}