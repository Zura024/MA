package com.example.lakhs.movieapplication.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.lakhs.movieapplication.models.Movie;
import com.example.lakhs.movieapplication.adapters.MovieAdapter;
import com.example.lakhs.movieapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public List<Movie> data;

    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.rv_movie);
        new DownloadMovieTask().execute("http://api.themoviedb.org/3/discover/movie?api_key=90a4ab4c39eb93f4ccf5a84fe60ea39d");

        data = new ArrayList<Movie>();
        adapter = new MovieAdapter((ArrayList<Movie>) data, this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager( new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    public class DownloadMovieTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(String... urls) {
            InputStream inputStream;
            ArrayList<Movie> result = null;
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                int response = conn.getResponseCode();
                Log.d("networking", "The response is: " + response);
                inputStream = conn.getInputStream();
                result = readIt(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> s) {
            adapter.notifyDataSetChanged();
        }

        public ArrayList<Movie> readIt(InputStream inputStream) throws IOException, JSONException {

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            return jsonParse(buffer.toString());
        }

        public ArrayList<Movie> jsonParse(String s) throws JSONException, IOException {

            JSONObject jsonObject = new JSONObject(s);
            JSONArray array = jsonObject.getJSONArray("results");

            for (int i = 0; i < array.length(); i++) {
                JSONObject movie = array.getJSONObject(i);
                Movie y = new Movie();
                y.setTitle(movie.getString("title"));
                y.setRate(movie.getString("vote_average"));
                y.setYear(movie.getString("release_date"));
                y.setDesc(movie.getString("overview"));
                y.setRsc("https://image.tmdb.org/t/p/original"+ movie.getString("poster_path"));
                y.setId(movie.getString("id"));
                data.add(y);
            }

            return (ArrayList<Movie>) data;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_fav) {
            Intent intent =new Intent(this,FavoriteActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onClickBack(View v) {
        super.onBackPressed();
    }

}
