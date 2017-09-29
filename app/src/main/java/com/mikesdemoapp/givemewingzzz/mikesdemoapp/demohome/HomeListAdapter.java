package com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by GiveMeWingzzz on 9/27/2017.
 */

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.MainViewHolder> {

    private ArrayList<GitRepo> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private Animation animation;

    // data is passed into the constructor
    public HomeListAdapter(Context context, ArrayList<GitRepo> mData) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    // inflates the cell layout from xml when needed
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.home_list_recycler_item, parent, false);
        return new MainViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(MainViewHolder viewHolder, final int position) {
        final GitRepo gitRepo = mData.get(position);

        final MainViewHolder holder = (MainViewHolder) viewHolder;
        boolean isRepoActive = gitRepo.isActive();

        holder.repoName.setText(gitRepo.getRepoName());
        holder.repoDesc.setText(gitRepo.getRepoDesc());
        Picasso.with(context).load(gitRepo.getRepoImage()).into(holder.gitRepoImage);

        holder.detailsContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClickListener != null) {
                    mClickListener.onItemClick(holder.repoName, holder.repoDesc, holder.gitRepoImage, gitRepo, position);
                }
            }
        });

        animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        holder.repoName.startAnimation(animation);
        holder.gitRepoImage.startAnimation(animation);

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView repoName;
        TextView repoDesc;
        ImageView gitRepoImage;
        LinearLayout detailsContainer;

        MainViewHolder(View itemView) {
            super(itemView);
            repoName = (TextView) itemView.findViewById(R.id.git_repo_name);
            repoDesc = (TextView) itemView.findViewById(R.id.git_repo_desc);
            gitRepoImage = (ImageView) itemView.findViewById(R.id.git_repo_icon);
            detailsContainer = (LinearLayout) itemView.findViewById(R.id.details_list_item_container);
        }

    }

    // convenience method for getting data at click position
    public String getRepoName(int id) {
        return mData.get(id).getRepoName();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View repoName, View repoDesc, View gitRepoImage, GitRepo gitRepo, int position);
    }
}