package edu.sfsu.api_cocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_CATEGORY = "CATEGORY";
    public static final String EXTRA_DRINK = "DRINK";
    public static final String EXTRA_IMAGE = "IMAGE";
    public static final String EXTRA_INSTRUCTIONS = "INSTRUCTIONS";
    public static final String EXTRA_TYPE = "TYPE";

    public static Intent newIntent(Context packageContext, String EXTRA_CATEGORY, String EXTRA_DRINK, String EXTRA_INSTRUCTIONS, String EXTRA_TYPE, String EXTRA_IMAGE) {

        Intent intent = new Intent(packageContext, DetailActivity.class);

        intent.putExtra(EXTRA_CATEGORY, "CATEGORY");
        intent.putExtra(EXTRA_DRINK, "DRINK");
        intent.putExtra(EXTRA_INSTRUCTIONS, "INSTRUCTIONS");
        intent.putExtra(EXTRA_TYPE, "TYPE");
        intent.putExtra(EXTRA_TYPE, "IMAGE");

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String image = getIntent().getStringExtra(EXTRA_IMAGE);
        String category = getIntent().getStringExtra(EXTRA_CATEGORY);
        String drink = getIntent().getStringExtra(EXTRA_DRINK);
        String instructions = getIntent().getStringExtra(EXTRA_INSTRUCTIONS);
        String type = getIntent().getStringExtra(EXTRA_TYPE);

        ImageView imageView = findViewById(R.id.imgView);
        TextView tvCategory = findViewById(R.id.category);
        TextView tvDrinks = findViewById(R.id.drink);
        TextView tvInstructions = findViewById(R.id.instructions);
        // TextView tvType = findViewById(R.id.type);

        Picasso.get().load(Uri.parse(image)).into(imageView);

        tvCategory.setText(category);
        tvDrinks.setText(drink);
        tvInstructions.setText(instructions);
        // tvType.setText(type);
    }
}