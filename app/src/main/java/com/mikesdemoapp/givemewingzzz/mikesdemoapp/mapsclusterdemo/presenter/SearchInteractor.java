package com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.presenter;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.data.BaseModel;

public interface SearchInteractor {

    interface OnSearchFinished {
        void onFinished(BaseModel items);

        void onQueryLimit();
    }

    void findRequest(OnSearchFinished listener);

}
