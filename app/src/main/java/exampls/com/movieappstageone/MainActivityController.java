package exampls.com.movieappstageone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * class that control MainActivity view
 */

public class MainActivityController {
    private Context context;
    private final String TAG = "mainactivitycontroller";
    private OnAdapterFinishIntializing onAdapterFinishIntializing = null;

    public void setOnAdapterFinishIntializing(OnAdapterFinishIntializing onAdapterFinishIntializing) {
        this.onAdapterFinishIntializing = onAdapterFinishIntializing;
    }

    public MainActivityController(Context context) {
        this.context = context;
    }

    public void getOnlineMovies(String category) {

        NetworkCalls networkCalls = new NetworkCalls(context);

        OnCallFinishCallback onCallFinishCallback = new OnCallFinishCallback() {
            @Override
            public void onSuccess(String response) {
                initializeAdapter(response);
            }
        };
        networkCalls.getMovies(category, onCallFinishCallback);

    }

    private List<Movie> cuttingJSON(String response) {

        final ArrayList<Movie> movies = new ArrayList<>();
        try {

            JSONObject object = new JSONObject(response);
            JSONArray jsonArray = object.getJSONArray("results");

            JSONObject obj;

            for (int i = 0; i < jsonArray.length(); i++) {

                obj = jsonArray.getJSONObject(i);

                movies.add(new Movie(obj.getInt("id"), obj.getString("poster_path"),
                        obj.getString("title"), obj.getString("release_date"),
                        obj.getDouble("vote_average"), obj.getString("overview")));

            }

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return movies;

    }

    private void initializeAdapter(String response) {

        List<Movie> list = cuttingJSON(response);


        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(context, list);

        adapter.setOnImageViewClickListener(new OnImageViewClickListener() {
            @Override
            public void onClick(Movie movie) {
                Intent intent = new Intent(context, DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putParcelable("movie", movie);
                intent.putExtras(bundle);

                context.startActivity(intent);

            }
        });

        onAdapterFinishIntializing.passAdapter(adapter);

    }



}
