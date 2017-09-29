package com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.presenter;

import android.util.Log;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome.MikesDemoApplication;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.data.BaseModel;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.service.ApiCall.FetchBBVAData;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.service.RealmController;

import io.realm.Realm;

public class SearchInteractorImplementation implements SearchInteractor, FetchBBVAData.OnResultsComplete {

    protected Realm realm;
    private static final String TAG = SearchInteractorImplementation.class.getSimpleName();

    @Override
    public void findRequest(final OnSearchFinished listener) {

        realm = RealmController.with(MikesDemoApplication.getInstance()).getRealm();

        FetchBBVAData fetchBBVAData = new FetchBBVAData();
        fetchBBVAData.call(this);

    }

    @Override
    public void onResultsFetched(OnSearchFinished listener, BaseModel model) {
        Log.d(TAG, " SearchInteractorImplementation Results : Size : " + model.getStatus());
        listener.onFinished(model);
    }

    @Override
    public void onResultsQueryLimit(String errorMessage) {

    }
}
