package edu.sfsu.api_cocktail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.color.DynamicColors;

import java.util.ArrayList;

import edu.sfsu.api_cocktail.asynctask.DataAsyncTask;
import edu.sfsu.api_cocktail.asynctask.DataTimer;
import edu.sfsu.api_cocktail.models.DrinkModel;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    private static final String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=r";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TextView tvMessage = findViewById(R.id.message);

        DataTimer dataTimer = new DataTimer();
        dataTimer.startTimer(tvMessage);

        DynamicColors.applyToActivityIfAvailable(this);

        ArrayList<DrinkModel> drinkModel = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        new DataAsyncTask(MainActivity.this, recyclerView, progressBar, drinkModel).execute(url);
    }
}