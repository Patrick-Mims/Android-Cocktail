package edu.sfsu.api_cocktail.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import edu.sfsu.api_cocktail.MainActivity;
import edu.sfsu.api_cocktail.adapters.DataAdapter;
import edu.sfsu.api_cocktail.models.DrinkModel;

public class DataAsyncTask extends AsyncTask<String, Integer, String> {
    private final Context context;
    RecyclerView recyclerView;
    ArrayList<DrinkModel> model;
    ProgressBar progressBar;

    public DataAsyncTask(Context context, RecyclerView recyclerView, ProgressBar progressBar, ArrayList<DrinkModel> model) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
        this.model = model;
    }

    ProgressDialog progressDialog;
    int prog = 0;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    /*
        progressDialog = new ProgressDialog(context);
        prog = progressDialog.getMax();
        progressDialog.setMessage("Loading..." + prog);
        progressDialog.setCancelable(false);
        progressDialog.show();
    */
    }

    @Override
    protected String doInBackground(String... params) {
        BufferedReader bufferedReader;
        HttpURLConnection urlConnection = null;
        String line;
        String results = null;
        StringBuilder builder;
        URL url;

        try {
            url = new URL(params[0]);

            urlConnection = (HttpURLConnection) url.openConnection();

            int code = urlConnection.getResponseCode();

            if(code != 200) {
                throw new IOException("Invalid response from the server: " + code);
            }

            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            builder = new StringBuilder();

            while((line = bufferedReader.readLine()) != null)  {
                builder.append(line).append("\n");
            }

            if(builder.length() == 0) {
                return null;
            }

            results = builder.toString();

            return results;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject root = new JSONObject(result);
            JSONArray list = root.getJSONArray("drinks");

            for(int i = 0; i < list.length(); i++) {
                model.add(new DrinkModel(
                        list.getJSONObject(i).getString("idDrink"),
                        list.getJSONObject(i).getString("strDrink"),
                        list.getJSONObject(i).getString("strDrinkAlternate"),
                        list.getJSONObject(i).getString("strTags"),
                        list.getJSONObject(i).getString("strVideo"),
                        list.getJSONObject(i).getString("strCategory"),
                        list.getJSONObject(i).getString("strIBA"),
                        list.getJSONObject(i).getString("strAlcoholic"),
                        list.getJSONObject(i).getString("strGlass"),
                        list.getJSONObject(i).getString("strInstructions"),
                        list.getJSONObject(i).getString("strInstructionsES"),
                        list.getJSONObject(i).getString("strInstructionsDE"),
                        list.getJSONObject(i).getString("strInstructionsFR"),
                        list.getJSONObject(i).getString("strInstructionsIT"),
                        list.getJSONObject(i).getString("strInstructionsZH-HANS"),
                        list.getJSONObject(i).getString("strInstructionsZH-HANT"),
                        list.getJSONObject(i).getString("strDrinkThumb"),
                        list.getJSONObject(i).getString("strIngredient1"),
                        list.getJSONObject(i).getString("strIngredient2"),
                        list.getJSONObject(i).getString("strIngredient3"),
                        list.getJSONObject(i).getString("strIngredient4"),
                        list.getJSONObject(i).getString("strIngredient5"),
                        list.getJSONObject(i).getString("strIngredient6"),
                        list.getJSONObject(i).getString("strIngredient7"),
                        list.getJSONObject(i).getString("strIngredient8"),
                        list.getJSONObject(i).getString("strIngredient9"),
                        list.getJSONObject(i).getString("strIngredient10"),
                        list.getJSONObject(i).getString("strIngredient11"),
                        list.getJSONObject(i).getString("strIngredient12"),
                        list.getJSONObject(i).getString("strIngredient13"),
                        list.getJSONObject(i).getString("strIngredient14"),
                        list.getJSONObject(i).getString("strIngredient15"),
                        list.getJSONObject(i).getString("strMeasure1"),
                        list.getJSONObject(i).getString("strMeasure2"),
                        list.getJSONObject(i).getString("strMeasure3"),
                        list.getJSONObject(i).getString("strMeasure4"),
                        list.getJSONObject(i).getString("strMeasure5"),
                        list.getJSONObject(i).getString("strMeasure6"),
                        list.getJSONObject(i).getString("strMeasure7"),
                        list.getJSONObject(i).getString("strMeasure8"),
                        list.getJSONObject(i).getString("strMeasure9"),
                        list.getJSONObject(i).getString("strMeasure10"),
                        list.getJSONObject(i).getString("strMeasure11"),
                        list.getJSONObject(i).getString("strMeasure12"),
                        list.getJSONObject(i).getString("strMeasure13"),
                        list.getJSONObject(i).getString("strMeasure14"),
                        list.getJSONObject(i).getString("strMeasure15"),
                        list.getJSONObject(i).getString("strImageSource"),
                        list.getJSONObject(i).getString("strImageAttribution"),
                        list.getJSONObject(i).getString("strCreativeCommonsConfirmed"),
                        list.getJSONObject(i).getString("dateModified")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.recyclerView.setAdapter(new DataAdapter(model));
    }
}