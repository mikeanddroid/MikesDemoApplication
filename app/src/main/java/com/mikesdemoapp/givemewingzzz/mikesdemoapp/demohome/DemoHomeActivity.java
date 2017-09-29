package com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DemoHomeActivity extends AppCompatActivity implements HomeListAdapter.ItemClickListener {

    public static final String TAG = DemoHomeActivity.class.getSimpleName();

    @BindView(R.id.demo_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.expandedImage)
    ImageView expandedImageView;

    GridLayoutManager layoutManager;

    HomeListAdapter homeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_demo_home);

        ButterKnife.bind(this);

        Picasso.with(this).load(R.drawable.android_banner).into(expandedImageView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        layoutManager = new GridLayoutManager(this, 1);

        for (GitRepo gitRepo : AppConstants.getDefaultList(this)) {

            Log.i(TAG, "Demo Repo Name : " + gitRepo.getRepoName());

        }

        recyclerView.setLayoutManager(layoutManager);
        homeListAdapter = new HomeListAdapter(this, AppConstants.getDefaultList(this));
        homeListAdapter.setClickListener(this);

        recyclerView.setAdapter(homeListAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Fade enterTransition = new Fade();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        getWindow().setEnterTransition(enterTransition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo_home, menu);
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
    public void onItemClick(View repoName, View repoDesc, View gitRepoImage, GitRepo gitRepo, int position) {

        Intent detailsIntent = new Intent(this, DemoDetails.class);
        detailsIntent.putExtra(AppConstants.GIT_REPO_NAME_KEY, gitRepo.getRepoName());
        detailsIntent.putExtra(AppConstants.GIT_REPO_DESC_KEY, gitRepo.getRepoDesc());
        detailsIntent.putExtra(AppConstants.GIT_REPO_POSITION_KEY, position);
        detailsIntent.putExtra(AppConstants.KEY_ANIM_TYPE, AppConstants.TransitionType.Fade);
        detailsIntent.putExtra(AppConstants.KEY_TITLE, "FADE"); // Todo : Use for toolbar later

        Pair[] pairs = new Pair[3];
        pairs[0] = new Pair<View, String>(repoName, "reponame");
        pairs[1] = new Pair<View, String>(repoDesc, "repodesc");
        pairs[2] = new Pair<View, String>(gitRepoImage, "repoimage");

        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs).toBundle();

        startActivity(detailsIntent, bundle);

    }
}
