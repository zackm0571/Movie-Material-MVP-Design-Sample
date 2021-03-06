package com.zackmatthews.mvp_sample.views;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zackmatthews.mvp_sample.R;
import com.zackmatthews.mvp_sample.models.GenericItem;
import com.zackmatthews.mvp_sample.models.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsActivity extends AppCompatActivity {
    private static Movie item;
    public @BindView(R.id.tv_details_title) TextView title;
    public @BindView(R.id.details_circleImg) ImageView img;
    public @BindView(R.id.tv_movieDetails)TextView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        if(item != null){
            refreshLayout();
            populateMovieDetails();
        }
    }

    public void populateMovieDetails(){
        StringBuilder outputString = new StringBuilder();

        outputString.append(Movie.DIRECTOR_KEY);
        outputString.append(": ");
        outputString.append(((Movie)item).getDirector());
        outputString.append("\n");

        outputString.append(Movie.ACTORS_KEY);
        outputString.append(": ");
        outputString.append(((Movie)item).getActors());
        outputString.append("\n");

        outputString.append(Movie.RATED_KEY);
        outputString.append(": ");
        outputString.append(((Movie)item).getRated());
        outputString.append("\n");

        outputString.append(Movie.GENRE_KEY);
        outputString.append(": ");
        outputString.append(((Movie)item).getGenre());
        outputString.append("\n");

        outputString.append(Movie.PLOT_KEY);
        outputString.append(": ");
        outputString.append(((Movie)item).getPlot());
        outputString.append("\n");

        details.setText(outputString);
    }

    public static void setItem(Movie _item){
        item = _item;
    }

    protected void refreshLayout(){
        title.setText(item.getTitle());

        if(item.getImg_url() != null){
            Picasso.get().load(item.getImg_url()).into(img);
        }
        else if(GenericItem.useDefaultImg){
            img.setImageResource(GenericItem.defaultImgRes);
        }
    }

}
