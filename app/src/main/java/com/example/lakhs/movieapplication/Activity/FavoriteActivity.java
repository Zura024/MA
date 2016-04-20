package com.example.lakhs.movieapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lakhs.movieapplication.R;
import com.example.lakhs.movieapplication.models.FavMovie;
import com.example.lakhs.movieapplication.adapters.FavoriteAdapter;
import com.example.lakhs.movieapplication.db.MovieSQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by lakhs on 3/28/2016.
 */
public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    public String[][] array = new String[6][20];
    public ArrayList<FavMovie> arrayList=new ArrayList<FavMovie>();
    MovieSQLiteOpenHelper sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.rv_favmovie);
        sql = new MovieSQLiteOpenHelper(this);
        array = sql.getFavmovie();

        int j = sql.i;
        for (int i = 0; i < j; i++) {
            FavMovie y = new FavMovie();
            y.setId(array[0][i]);
            y.setTitle(array[1][i]);
            y.setYear(array[2][i]);
            y.setRate(array[3][i]);
            y.setDesc(array[4][i]);
            y.setRsc(array[5][i]);

            arrayList.add(y);
        }

        adapter = new FavoriteAdapter(arrayList, this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager( new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

    }

    public void onClickBack(View v) {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
