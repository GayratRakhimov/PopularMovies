package com.gayratrakhimov.popularmovies.screen.movies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.gayratrakhimov.popularmovies.R;
import com.gayratrakhimov.popularmovies.data.repository.RepositoryProvider;
import com.gayratrakhimov.popularmovies.domain.model.Movie;
import com.gayratrakhimov.popularmovies.domain.usecase.MoviesUseCase;
import com.gayratrakhimov.popularmovies.screen.details.MovieDetailsActivity;
import com.gayratrakhimov.popularmovies.screen.general.LoadingDialog;
import com.gayratrakhimov.popularmovies.screen.general.LoadingView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.arturvasilov.rxloader.RxUtils;


public class MoviesActivity extends AppCompatActivity implements MoviesView, MoviesAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView mMoviesRecycler;

    @BindView(R.id.empty)
    View mEmptyView;

    private MoviesAdapter mAdapter;

    private LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        int columns = getResources().getInteger(R.integer.columns_count);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), columns);
        mMoviesRecycler.setLayoutManager(layoutManager);
        mAdapter = createAdapter();
        mMoviesRecycler.setAdapter(mAdapter);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        MoviesUseCase moviesUseCase = new MoviesUseCase(RepositoryProvider.getMoviesRepository(), RxUtils.async());
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        MoviesPresenter presenter = new MoviesPresenter(this, moviesUseCase, lifecycleHandler);
        presenter.init();
    }

    @Override
    public void showMovies(@NonNull List<Movie> movies) {
        mAdapter.changeDataSet(movies);
        mMoviesRecycler.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingIndicator() {
        mLoadingView.showLoadingIndicator();
    }

    @Override
    public void hideLoadingIndicator() {
        mLoadingView.hideLoadingIndicator();
    }

    @Override
    public void showError() {
        mMoviesRecycler.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(@NonNull View view, @NonNull Movie movie) {
        ImageView imageView = ButterKnife.findById(view, R.id.image);
        MovieDetailsActivity.navigate(this, imageView, movie);
    }

    @NonNull
    private MoviesAdapter createAdapter() {
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.rows_count, typedValue, true);
        float rowsCount = typedValue.getFloat();
        int actionBarHeight = getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
                ? TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics())
                : 0;
        int imageHeight = (int) ((getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rowsCount);

        int columns = getResources().getInteger(R.integer.columns_count);
        int imageWidth = getResources().getDisplayMetrics().widthPixels / columns;

        return new MoviesAdapter(imageHeight, imageWidth, this);
    }
}
