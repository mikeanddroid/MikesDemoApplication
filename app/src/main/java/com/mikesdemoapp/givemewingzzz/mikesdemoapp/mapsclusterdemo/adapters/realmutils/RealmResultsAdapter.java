package com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.adapters.realmutils;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.data.Results;

import io.realm.RealmResults;

public class RealmResultsAdapter extends RealmModelAdapter<Results> {

    public RealmResultsAdapter(RealmResults<Results> resultsRealmResults) {
        super(resultsRealmResults);
    }

}
