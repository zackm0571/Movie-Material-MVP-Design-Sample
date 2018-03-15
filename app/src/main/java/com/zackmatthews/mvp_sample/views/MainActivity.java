package com.zackmatthews.mvp_sample.views;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.zackmatthews.mvp_sample.R;
import com.zackmatthews.mvp_sample.models.Movie;
import com.zackmatthews.mvp_sample.presenters.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainContract{

    public @BindView(R.id.mainRecycler) RecyclerView recyclerView;
    private MVPRecyclerAdapter adapter = new MVPRecyclerAdapter();
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mockItems(adapter);
        setupViews();
        recyclerView.setAdapter(adapter);
        presenter = new MainPresenter(this);
        presenter.refreshData();
    }

    private void setupViews(){
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(20, LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(20, LinearLayoutManager.HORIZONTAL));

    }

    private void mockItems(MVPRecyclerAdapter adapter){
        Movie movie = new Movie();
        movie.setTitle("Lambs");
        movie.setDirector("Lector");
        movie.setYear("1999");
        adapter.add(movie);

        movie = new Movie();
        movie.setTitle("Star wars");
        movie.setDirector("solo");
        movie.setYear("1989");
        adapter.add(movie);

        movie = new Movie();
        movie.setTitle("Potter");
        movie.setDirector("Harry");
        movie.setYear("1987");
        adapter.add(movie);
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

    @Override
    public void onDataLoaded(List<Movie> data) {

    }

    @Override
    public Context getContext() {
        return MainActivity.this;
    }
}
