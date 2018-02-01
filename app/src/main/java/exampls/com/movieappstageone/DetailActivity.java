package exampls.com.movieappstageone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity  {

    private static final String TAG = "detailactivity";
    ListView trailersLv, reviewLv;
    Button trailersBtn, reviewsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();

        try {

            final Movie movie = bundle.getParcelable("movie");

            String posterImage = movie.getPosterImage();
            ((TextView) findViewById(R.id.movie_title_tv)).setText(movie.getTitle());
            Picasso.with(this).load("http://image.tmdb.org/t/p/w185/" + posterImage)
                    .into(((ImageView) findViewById(R.id.poster_movie)), new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            Log.e(TAG, "picasso finished");

                            TextView textView = (TextView) findViewById(R.id.movie_overview_tv);
                            textView.setText(movie.getOverview());
                            textView.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onError() {

                        }
                    });
            ((TextView) findViewById(R.id.movie_date_tv)).setText(movie.getRealeseDate());
            ((TextView) findViewById(R.id.movie_vote_avg_tv)).setText(movie.getVoteAverage() + " /10");


        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

       /* trailersBtn = (Button) findViewById(R.id.trailer_button);
        trailersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrailerDialog();
            }
        });
        reviewsBtn = (Button) findViewById(R.id.review_button);
        reviewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReviewsDialog();
            }
        });
*/

    }



}
