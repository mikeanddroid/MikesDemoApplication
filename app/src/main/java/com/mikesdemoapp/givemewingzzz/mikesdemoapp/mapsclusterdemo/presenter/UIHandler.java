package com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.presenter;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.data.BaseModel;

public interface UIHandler {

    void showProgress();

    void hideProgress();

    void setItems(BaseModel items);

    void onDataComplete();

    void onQueryExceeded();

    void showMessage(String message);

}
