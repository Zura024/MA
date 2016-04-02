package com.example.lakhs.movieapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lakhs.movieapplication.MovieSQLiteOpenHelper;
import com.example.lakhs.movieapplication.R;
import com.squareup.picasso.Picasso;

/**
 * Created by lakhs on 3/22/2016.
 */
public class SingleMovieActivity extends AppCompatActivity {
    TextView title, year, rate, desc;
    ImageView imageView;
    Button b;
    String mTitle,mYear,mRate,mDesc,mId,s;
    MovieSQLiteOpenHelper sql;
    int id;
    String arrayList[][];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sql=new MovieSQLiteOpenHelper(this);
        title = (TextView) findViewById(R.id.tv_title);
        year = (TextView) findViewById(R.id.tv_year);
        rate = (TextView) findViewById(R.id.tv_rate);
        desc = (TextView) findViewById(R.id.tv_desc);
        imageView = (ImageView) findViewById(R.id.iv_movie);
        mTitle  =  getIntent().getStringExtra("title");
        mYear  =  getIntent().getStringExtra("year");
        mRate  =  getIntent().getStringExtra("rate");
        mDesc  =  getIntent().getStringExtra("desc");
        mId  =  getIntent().getStringExtra("id");
        title.setText("Title : " + mTitle);
        year.setText("Date : " + mYear);
        rate.setText("IMDB Rate " + mRate);
        desc.setText("Description: " +mDesc);
        s = getIntent().getStringExtra("rsc");
        id = Integer.parseInt(mId);
        Picasso.with(this)
                .load(s)
                .resize(175, 250)
                .centerCrop()
                .into(imageView);

    }

    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void onClickBack(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    public void addFavorite(View v){
       boolean b = sql.insertData(id, mTitle, mYear, mRate, mDesc, s);
       if (b==true)
           Toast.makeText(this,"Successfully Edited", Toast.LENGTH_LONG).show();
    }
    public void deleteFavorite(View v){
         Integer res = sql.deleteFav(mId);
        if (res==1)
            Toast.makeText(this,"Removed", Toast.LENGTH_LONG).show();
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
        if (id == R.id.action_fav) {
            Intent intent =new Intent(this,FavoriteActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


