package com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.dagger;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.rx.RxSchedulers;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.HeroesListActivity;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.mvp.HeroesModel;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.mvp.HeroesPresenter;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.mvp.HeroesView;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.wsapi.HeroApi;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.justmvploginflow.dagger2.mavenheroswithdagger2.utils.views.heroesview.dagger
 * Created by GiveMeWingzzz on 11/6/2017.
 */

@Module
public class HeroesModule {

    HeroesListActivity heroesListContext;

    public HeroesModule(HeroesListActivity context) {
        this.heroesListContext = context;
    }

    @HeroesScope
    @Provides
    HeroesView provideView() {
        return new HeroesView(heroesListContext);
    }

    @HeroesScope
    @Provides
    HeroesPresenter providePresenter(RxSchedulers schedulers, HeroesView view, HeroesModel model) {
        CompositeSubscription subscriptions = new CompositeSubscription();
        return new HeroesPresenter(schedulers, model, view, subscriptions);
    }

    @HeroesScope
    @Provides
    HeroesListActivity provideContext() {
        return heroesListContext;
    }

    @HeroesScope
    @Provides
    HeroesModel provideModel(HeroApi api) {
        return new HeroesModel(heroesListContext, api);
    }

}
