package com.example.mvr2;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mvr2.adapter.MovieAdapter;
import com.example.mvr2.model.Movie;
import com.example.mvr2.ui.MovieViewActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<Movie> movieList;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView img = (ImageView) findViewById(R.id.test);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        movieList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rec_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new MovieAdapter(movieList, this);
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(this);

        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/moviereviewer-34f20.appspot.com/o/image%2Fdoctor_strange_cover.jpg?alt=media&token=8178412c-60d3-4a15-b716-c8580a5d8611").into(img);
        FirebaseDatabase.getInstance().getReference("movie-list").orderByChild("date").endAt("2017").startAt("2015").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot test : dataSnapshot.getChildren()) {
                    Log.i(TAG, "movie data get");
                    Movie movie = test.getValue(Movie.class);
                    Log.i(TAG, movie.getPicture());
                    movieList.add(movie);
                    adapter.notifyItemInserted(movieList.size() - 1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage());
            }
        });

    }


    @Override
    public void onClick(View view, int position) {
        final Movie movie = movieList.get(position);
        Intent i = new Intent(this, MovieViewActivity.class);
        i.putExtra("movieId", movie.getId());
        Log.i("Hello", movie.getName());
        startActivity(i);
    }
}