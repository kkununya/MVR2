package com.example.mvr2.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.example.mvr2.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by kununya1996 on 11/5/2016.
 */

public class MovieViewActivity extends Activity {

    private static String TAG = "MovieViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_view);

        TextView title = (TextView) findViewById(R.id.title);
        ImageView cover = (ImageView)findViewById(R.id.cover);
        final TextView synopsis = (TextView)findViewById(R.id.synopsis);
        TextView trailer = (TextView)findViewById(R.id.trailer);

        /*COVER*/

        /*Rating Star Color Set*/
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(0).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        /*Font*/
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/yfiles.ttf");
        title.setTypeface(myTypeface);

        System.out.print("Hellooooooooooooooooooooooooooooooooooooooooooooo");
        Log.i(TAG, "movie data get");
        Bundle bundle = getIntent().getExtras();
        final String movieId = (String) bundle.get("movieId");

        System.out.print("Hellooooooooooooooooooooooooooooooooooooooooooooo");

        FirebaseDatabase.getInstance().getReference("movie-list/").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        title.setText(movieId);
    }
}
