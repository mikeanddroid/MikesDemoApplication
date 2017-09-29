package com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.service.ApiCall;

import android.util.Log;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome.AppConstants;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.data.BaseModel;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.data.Photos;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.data.Results;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.presenter.SearchInteractor;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.service.BaseClient;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.service.OttoHelper;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.mapsclusterdemo.service.RetrofitInterface;

import java.util.List;

import io.realm.Realm;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class FetchSearchedLocations {

    private static final String TAG = FetchSearchedLocations.class.getSimpleName();
    private static final String QUERY_LIMIT = "OVER_QUERY_LIMIT";

    static int failureCounter = 0;

    public static void call(String searchQuery, final OnResultsComplete onResultsComplete) {

        RetrofitInterface retrofitInterface = BaseClient.getBBSIClient();

        Call<BaseModel> call = retrofitInterface.getSearchedLocations(searchQuery, AppConstants.API_KEYS.MAP_API_KEY_8);

        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(final Response<BaseModel> response, Retrofit retrofit) {

                // Todo : If query over limit display dialog on that screen
                final String responseStatus = response.body().getStatus();

                if (QUERY_LIMIT.equals(responseStatus) || QUERY_LIMIT.contains(responseStatus)) {
                    onResultsComplete.onResultsQueryLimit(responseStatus);
                    failureCounter++;

                    if (failureCounter <= 8) {
                        // Todo : Implement swaping api keys
                        // Retry with other api keys for success result
                    }

                }

                Log.d(TAG, " Response : Size : " + response.body().getResults().size() + " : Response Code : " + response.code());

                List<Results> resultsList = response.body().getResults();

                final int resultSize = resultsList.size();

                Log.d(TAG, " Response Results : Size : " + resultSize);

                for (int i = 0; i < resultSize; i++) {
                    Log.d(TAG, "Fetch FetchSearchedLocations Data Results : Formatted Address :" + resultsList.get(i).getFormattedAddress());
                }

                List<Photos> photos = null;

                try {

                    Realm realm = Realm.getDefaultInstance();

                    final BaseModel baseModel = response.body();

                    for (int i = 0; i < resultsList.size(); i++) {
                        photos = resultsList.get(i).getPhotos();
                    }

                    realm.beginTransaction();

                    if (baseModel != null) {

                        realm.delete(BaseModel.class);
                        realm.delete(Results.class);
                        realm.delete(Photos.class);

                        realm.copyToRealm(baseModel);
                        realm.copyToRealmOrUpdate(resultsList);

                        if (photos != null) {
                            realm.copyToRealm(photos);
                        }

                    }

                    realm.commitTransaction();

                    onResultsComplete.onResultsFetched(new SearchInteractor.OnSearchFinished() {
                        @Override
                        public void onFinished(BaseModel items) {
                            OttoHelper.post(new SeachedSuccessEvent(items, response));
                        }

                        @Override
                        public void onQueryLimit() {
                            OttoHelper.post(new SearchedFailureEvent(responseStatus));
                        }
                    }, baseModel);

                } catch (NullPointerException npe) {
                    Log.e(TAG + ":: Error :: ", "Missing element somewhere in location response", npe);
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    public interface OnResultsComplete {
        void onResultsFetched(SearchInteractor.OnSearchFinished listener, BaseModel baseModel);

        void onResultsQueryLimit(String errorMessage);
    }

    public static class SeachedSuccessEvent {
        private BaseModel baseModel;
        private Response response;

        private SeachedSuccessEvent(BaseModel baseModel, Response response) {
            this.baseModel = baseModel;
            this.response = response;
        }

        public BaseModel getBaseModel() {
            return baseModel;
        }

        public Response getResponse() {
            return response;
        }
    }

    public static class SearchedFailureEvent {

        private String errorMessage;

        private SearchedFailureEvent(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

}
