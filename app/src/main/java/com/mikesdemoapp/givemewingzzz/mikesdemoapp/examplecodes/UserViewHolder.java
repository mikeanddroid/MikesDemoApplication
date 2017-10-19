package com.mikesdemoapp.givemewingzzz.mikesdemoapp.examplecodes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;

/**
 * Created by GiveMeWingzzz on 10/17/2017.
 */

class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName;
    public TextView tvEmailId;

    public UserViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.loadMoreTVName);
        tvEmailId = (TextView) itemView.findViewById(R.id.loadMoreTVEmailId);
    }
}