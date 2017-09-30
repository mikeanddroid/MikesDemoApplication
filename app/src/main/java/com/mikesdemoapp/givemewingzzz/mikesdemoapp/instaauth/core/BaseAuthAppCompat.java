package com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.core;

import android.os.Bundle;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.appmodel.Token;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.utils.AppConstants;


/**
 * Created by GiveMeWingzz on 1/11/2016.
 */
public abstract class BaseAuthAppCompat extends CoreActivity {

    public boolean waitingForResult = false;

    @Override
    protected void onStart() {
        super.onStart();
        // Try to load token
        Token token = new Token();
        if ((!token.load(this) || !token.isValid()) && !waitingForResult) {
            // No token? No problem!
            waitingForResult = true;
            startLoginActivity();
        } else {
            // Token? let them know!
            onTokenLoaded(token);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            waitingForResult = savedInstanceState.getBoolean(AppConstants.EXTRA_WAITING_FOR_RESULT_KEY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(AppConstants.EXTRA_WAITING_FOR_RESULT_KEY, waitingForResult);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onLoginSucceeded(Token token) {
        onTokenLoaded(token);
    }

    @Override
    protected void onLoginFailed() {
        finish();
    }

    // Children can override this method to know when the token is actually loaded
    protected abstract void onTokenLoaded(Token token);

}
