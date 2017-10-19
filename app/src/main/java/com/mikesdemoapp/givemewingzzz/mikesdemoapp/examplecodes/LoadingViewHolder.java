package com.mikesdemoapp.givemewingzzz.mikesdemoapp.examplecodes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;

/**
 * Created by GiveMeWingzzz on 10/17/2017.
 */

class LoadingViewHolder extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar) itemView.findViewById(R.id.loadMoreProgressBar);
    }
}