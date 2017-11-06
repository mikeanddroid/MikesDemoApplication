package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.mvp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.models.Hero;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.HeroesListActivity;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.list.HeroesAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.mvp
 * Created by GiveMeWingzzz on 11/6/2017.
 */

public class HeroesView {

    @BindView(R.id.activity_heroes_list_recycleview)
    RecyclerView list;

    View view;

    HeroesAdapter adapter;

    public HeroesView(HeroesListActivity context) {

        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_heroes_list, parent, true);
        ButterKnife.bind(this, view);

        adapter = new HeroesAdapter();

        list.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        list.setLayoutManager(mLayoutManager);

    }

    public Observable<Integer> itemClicks() {
        return adapter.observeClicks();
    }

    public View view() {
        return view;
    }

    public void swapAdapter(ArrayList<Hero> heroes) {
        adapter.swapAdapter(heroes);
    }

}
