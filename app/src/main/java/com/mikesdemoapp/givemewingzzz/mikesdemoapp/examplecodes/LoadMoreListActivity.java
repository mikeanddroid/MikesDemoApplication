package com.mikesdemoapp.givemewingzzz.mikesdemoapp.examplecodes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome.AppConstants;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome.DemoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static android.support.v4.app.ActivityOptionsCompat.makeSceneTransitionAnimation;

/**
 * Project - MikesDemoApp
 * In Package - ${PACKAGE_NAME}
 * Created by GiveMeWingzzz on 10/17/2017.
 */

public class LoadMoreListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<ImageDataModel> mImageDataModels = new ArrayList<>();
    private UserAdapter mUserAdapter;
    private static final String TAG = LoadMoreListActivity.class.getSimpleName();
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_base_layout);

        ButterKnife.bind(this);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);

//        Toolbar mToolbar = (Toolbar) findViewById(R.id.baseToolbar);
//        mToolbar.setTitle("LoadMoreRecycleView");

//        addDefaultList(mImageDataModels);
        addDefaultListFromUtils();

        mRecyclerView = (RecyclerView) findViewById(R.id.baseRecycleView);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mUserAdapter = new UserAdapter(this);
        mRecyclerView.setAdapter(mUserAdapter);

        mUserAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.e("haint", "Load More");
                mImageDataModels.add(null);
                mUserAdapter.notifyItemInserted(mImageDataModels.size() - 1);
                //Load more data for reyclerview
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        removeItemsAndNotify(mImageDataModels);
                        addMoreItems();
                    }
                }, 1000);

            }
        });

        mUserAdapter.setOnItemClickListener(new LoadMoreAdapterListener() {
            @Override
            public void onClick(int position, ImageDataModel imageDataModel, View view) {

//                MyDialogFragment dialogFrag = MyDialogFragment.getMyDialogFragment(imageDataModel);
//
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                dialogFrag.show(fragmentManager, MyDialogFragment.MY_DIALOG_FRAGMENT_TAG);

//                final Dialog dialogView = new Dialog(LoadMoreListActivity.this);
//                dialogView.setContentView(R.layout.dialog_content);
//
//                ImageView imageView = (ImageView) dialogView.findViewById(R.id.demoItemDetailImageView);
//                TextView imageViewName = (TextView) dialogView.findViewById(R.id.demoItemDetailImageName);
//                TextView imageViewDate = (TextView) dialogView.findViewById(R.id.demoItemDetailImageDate);
////                final ProgressBar progressBar = (ProgressBar) dialogView.findViewById(R.id.demoItemDetailProgressBar);
//
                String imageUrl = null;
                String imageName = null;
                String imageDate = null;

                imageUrl = imageDataModel.getImageUrl();
                imageName = imageDataModel.getName();
                imageDate = imageDataModel.getDate();
//
////                progressBar.setVisibility(View.VISIBLE);
//
//                GlideApp.with(LoadMoreListActivity.this).load(imageUrl).listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
////                        progressBar.setVisibility(View.GONE);
//                        return false;
//                    }
//                }).placeholder(new ColorDrawable(Color.BLACK)).fitCenter().into(imageView);
//
////                int imageWidth = imageView.getMeasuredWidth();
////                int imageHeight = imageView.getMeasuredHeight();
////
////                if (imageWidth > imageHeight) {
////
////                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(imageWidth, imageHeight);
////                    imageView.setLayoutParams(layoutParams);
////
////                } else {
////                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(imageHeight, imageWidth);
////                    imageView.setLayoutParams(layoutParams);
////                }
//
//                imageViewName.setText(imageName);
//                imageViewDate.setText(imageDate);
//
//                dialogView.show();

                Bundle b = makeSceneTransitionAnimation(LoadMoreListActivity.this, view, "demoimage").toBundle();

                Intent i = new Intent(LoadMoreListActivity.this, ImageDetailsActivity.class);
                i.putExtra(MyDialogFragment.IMAGE_URL_KEY, imageUrl);
                i.putExtra(MyDialogFragment.IMAGE_NAME_KEY, imageName);
                i.putExtra(MyDialogFragment.IMAGE_DATE_KEY, imageDate);
                i.putExtra(AppConstants.KEY_TITLE, "FADE");
                startActivity(i, b);

            }
        });

    }

    /**
     * @param i Index
     */
    private ImageDataModel addImageObj(int i) {

        Log.d(TAG, "ImageDataModel INDEX --> " + i);

        ImageDataModel imageDataModel = null;


        Log.d(TAG, "ImageDataModel IMAGE URLS SIZE --> " + DemoUtils.imageUrls().size());

        imageDataModel = new ImageDataModel();
        imageDataModel.setName("IMGUR Image " + i);
        imageDataModel.setDate(i + "/" + (i + 4) + "/" + 2017);

        Log.d(TAG, "Image URLS --> Index --> " + i + " : URLS --> " + DemoUtils.imageUrls().get(i));

        imageDataModel.setImageUrl(DemoUtils.imageUrls().get(i));

        return imageDataModel;
    }

    private void addDefaultListFromUtils() {

        for (int i = 1; i < 10; i++) {
            ImageDataModel imageDataModel = addImageObj(i);
            mImageDataModels.add(imageDataModel);
        }

    }

    private void addDefaultList(List<ImageDataModel> mImageDataModels) {

        if (mImageDataModels.isEmpty()) {

            ImageDataModel imageDataModel = new ImageDataModel();
            imageDataModel.setName("Image Title Def");
            imageDataModel.setDate("1/1/2017");
            imageDataModel.setImageUrl("https://i.imgur.com/LGcLYM1.jpg");

            ImageDataModel imageDataModel1 = new ImageDataModel();
            imageDataModel1.setName("Image Title 1");
            imageDataModel1.setDate("1/1/2017");
            imageDataModel1.setImageUrl("https://i.imgur.com/iVTyEWe.jpg");

            ImageDataModel imageDataModel2 = new ImageDataModel();
            imageDataModel2.setName("Image Title 2");
            imageDataModel2.setDate("1/1/2017");
            imageDataModel2.setImageUrl("https://i.imgur.com/knPk82Q.jpg");

            ImageDataModel imageDataModel3 = new ImageDataModel();
            imageDataModel3.setName("Image Title 3");
            imageDataModel3.setDate("1/1/2017");
            imageDataModel3.setImageUrl("https://i.imgur.com/NPyhcB1.jpg");

            ImageDataModel imageDataModel4 = new ImageDataModel();
            imageDataModel4.setName("Image Title 4");
            imageDataModel4.setDate("1/1/2017");
            imageDataModel4.setImageUrl("https://i.imgur.com/zKFnpb0.jpg");

            ImageDataModel imageDataModel5 = new ImageDataModel();
            imageDataModel.setName("Image Title 5");
            imageDataModel.setDate("1/1/2017");
            imageDataModel5.setImageUrl("https://i.imgur.com/6TcpDoY.jpg");

            ImageDataModel imageDataModel6 = new ImageDataModel();
            imageDataModel6.setName("Image Title 6");
            imageDataModel6.setDate("1/1/2017");
            imageDataModel6.setImageUrl("https://i.imgur.com/rfqcdls.jpg");

            ImageDataModel imageDataModel7 = new ImageDataModel();
            imageDataModel7.setName("Image Title 7");
            imageDataModel7.setDate("1/1/2017");
            imageDataModel7.setImageUrl("https://i.imgur.com/3fNw7qc.jpg");

            ImageDataModel imageDataModel8 = new ImageDataModel();
            imageDataModel8.setName("Image Title 8");
            imageDataModel8.setDate("1/1/2017");
            imageDataModel8.setImageUrl("https://i.imgur.com/essNDXP.jpg");

            ImageDataModel imageDataModel9 = new ImageDataModel();
            imageDataModel9.setName("Image Title 9");
            imageDataModel9.setDate("1/1/2017");
            imageDataModel9.setImageUrl("https://i.imgur.com/2P6Dunf.jpg");

            ImageDataModel imageDataModel10 = new ImageDataModel();
            imageDataModel10.setName("Image Title 10");
            imageDataModel10.setDate("1/1/2017");
            imageDataModel10.setImageUrl("https://i.imgur.com/ChzyYBQ.jpg");

            ImageDataModel imageDataModel11 = new ImageDataModel();
            imageDataModel11.setName("Image Title 11");
            imageDataModel11.setDate("1/1/2017");
            imageDataModel11.setImageUrl("https://i.imgur.com/9fQ2WMF.jpg");

            ImageDataModel imageDataModel12 = new ImageDataModel();
            imageDataModel12.setName("Image Title 12");
            imageDataModel12.setDate("1/1/2017");
            imageDataModel12.setImageUrl("https://i.imgur.com/upf7yGu.jpg");

            ImageDataModel imageDataModel13 = new ImageDataModel();
            imageDataModel13.setName("Image Title 13");
            imageDataModel13.setDate("1/1/2017");
            imageDataModel13.setImageUrl("https://i.imgur.com/1YSbRfo.jpg");

            ImageDataModel imageDataModel14 = new ImageDataModel();
            imageDataModel14.setName("Image Title 14");
            imageDataModel14.setDate("1/1/2017");
            imageDataModel14.setImageUrl("https://i.imgur.com/4ksKrsq.jpg");

            ImageDataModel imageDataModel15 = new ImageDataModel();
            imageDataModel15.setName("Image Title 15");
            imageDataModel15.setDate("1/1/2017");
            imageDataModel15.setImageUrl("https://i.imgur.com/P9oXFsw.jpg");

            ImageDataModel imageDataModel16 = new ImageDataModel();
            imageDataModel16.setName("Image Title 16");
            imageDataModel16.setDate("1/1/2017");
            imageDataModel16.setImageUrl("https://i.imgur.com/QsnAN3d.jpg");

            ImageDataModel imageDataModel17 = new ImageDataModel();
            imageDataModel17.setName("Image Title 17");
            imageDataModel17.setDate("1/1/2017");
            imageDataModel17.setImageUrl("https://i.imgur.com/wdNAlBI.jpg");

            ImageDataModel imageDataModel18 = new ImageDataModel();
            imageDataModel18.setName("Image Title 18");
            imageDataModel18.setDate("1/1/2017");
            imageDataModel18.setImageUrl("https://i.imgur.com/enyy7vl.jpg");

            ImageDataModel imageDataModel19 = new ImageDataModel();
            imageDataModel19.setName("Image Title 19");
            imageDataModel19.setDate("1/1/2017");
            imageDataModel19.setImageUrl("https://i.imgur.com/4rxdtMb.jpg");

            ImageDataModel imageDataModel20 = new ImageDataModel();
            imageDataModel20.setName("Image Title 20");
            imageDataModel20.setDate("1/1/2017");
            imageDataModel20.setImageUrl("https://i.imgur.com/JNX2q7W.jpg");

            ImageDataModel imageDataModel21 = new ImageDataModel();
            imageDataModel21.setName("Image Title 21");
            imageDataModel21.setDate("1/1/2017");
            imageDataModel21.setImageUrl("https://i.imgur.com/QAiqYAs.jpg");


            mImageDataModels.add(imageDataModel);
            mImageDataModels.add(imageDataModel1);
            mImageDataModels.add(imageDataModel2);
            mImageDataModels.add(imageDataModel3);
            mImageDataModels.add(imageDataModel4);
            mImageDataModels.add(imageDataModel5);
            mImageDataModels.add(imageDataModel6);
            mImageDataModels.add(imageDataModel7);
            mImageDataModels.add(imageDataModel8);
            mImageDataModels.add(imageDataModel9);
            mImageDataModels.add(imageDataModel10);
            mImageDataModels.add(imageDataModel11);
            mImageDataModels.add(imageDataModel12);
            mImageDataModels.add(imageDataModel13);
            mImageDataModels.add(imageDataModel14);
            mImageDataModels.add(imageDataModel15);
            mImageDataModels.add(imageDataModel16);
            mImageDataModels.add(imageDataModel17);
            mImageDataModels.add(imageDataModel18);
            mImageDataModels.add(imageDataModel19);
            mImageDataModels.add(imageDataModel20);
            mImageDataModels.add(imageDataModel21);

        }
    }

    private void addMoreItems() {

        /**/

        //Remove loading item
        mImageDataModels.remove(mImageDataModels.size() - 1);
        mUserAdapter.notifyItemRemoved(mImageDataModels.size());

        int index = mImageDataModels.size() + 1;

        int endImageIndex = DemoUtils.imageUrls().size();

        int endListIndex = 0;

        if (index + 3 < endImageIndex) {
            endListIndex = index + 3;
        } else {
            endListIndex = endImageIndex;
        }

        for (int i = index; i < endListIndex; i++) {

            ImageDataModel imageDataModel = addImageObj(i);

            if (imageDataModel != null) {
                Log.d(TAG, "ADDING MORE --> " + " Image NAME --> " + imageDataModel.getName());
                Log.d(TAG, "ADDING MORE --> " + " Image DATE --> " + imageDataModel.getDate());
                Log.d(TAG, "ADDING MORE --> " + " Image URL --> " + imageDataModel.getImageUrl());

                Log.d(TAG, "Load More 3 Items");
                mImageDataModels.add(imageDataModel);

                mUserAdapter.notifyDataSetChanged();
                mUserAdapter.setLoaded();
            }

        }

        /**/

    }

    private void removeItemsAndNotify(List<ImageDataModel> mImageDataModels) {

        //Remove loading item
        mImageDataModels.remove(mImageDataModels.size() - 1);
        mUserAdapter.notifyItemRemoved(mImageDataModels.size());
        //Load data

        for (int i = mImageDataModels.size() - 1; i >= 4; i--) {
            mImageDataModels.remove(i);
        }

        mUserAdapter.notifyDataSetChanged();
        mUserAdapter.setLoaded();

    }

    private static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvEmailId;
        ImageView imageView;
        ProgressBar progressBar;
        LinearLayout itemContainer;

        UserViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.loadMoreTVName);
            tvEmailId = itemView.findViewById(R.id.loadMoreTVEmailId);
            imageView = itemView.findViewById(R.id.demoImageView);
            progressBar = itemView.findViewById(R.id.itemProgressBar);
            itemContainer = itemView.findViewById(R.id.demoItemContainer);
        }
    }

    private static class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.loadMoreProgressBar);
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final int VIEW_TYPE_ITEM = 0;
        private final int VIEW_TYPE_LOADING = 1;
        private OnLoadMoreListener mOnLoadMoreListener;
        private LoadMoreAdapterListener loadMoreAdapterListener;
        private boolean isLoading;
        private int visibleThreshold = 3;
        private int visibleGridThreshold = 0;
        private int lastVisibleItem, totalItemCount;
        private int[] lastVisibleItems;
        private Context context;

        UserAdapter(Context context) {
            this.context = context;

            final StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) mRecyclerView.getLayoutManager();

            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    if (dy > 0) { //for vertical scrolling

                        // For Vertical Scrolling
//            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
//            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//
//                    totalItemCount = linearLayoutManager.getItemCount();
//                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
//
//                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
//                        if (mOnLoadMoreListener != null) {
//                            if (totalItemCount >= 0 && lastVisibleItem >= 4) {
//                                mOnLoadMoreListener.onLoadMore();
//                            }
//
//                        }
//                        isLoading = true;
//                    }
//                }
//            });

                    } else if (dy < 0) {

                    }

                    if (dx > 0) {//for horizontal scrolling

                        lastVisibleItems = new int[]{};

                        totalItemCount = staggeredGridLayoutManager.getItemCount();
                        lastVisibleItems = staggeredGridLayoutManager.findLastVisibleItemPositions(null);
                        lastVisibleItem = staggeredGridLayoutManager.getItemCount();

                        View visibleView = null;

                        int lastTempVal = -1;

                        if (lastVisibleItems[1] != -1) {
                            lastTempVal = lastVisibleItems[1] + 1;
                        } else {
                            lastTempVal = lastVisibleItems[0] + 1;
                        }

                        if ((totalItemCount) <= lastTempVal) {
                            visibleView = staggeredGridLayoutManager.findViewByPosition(lastTempVal - 1);
                        }

                        if (visibleView != null) {
                            if (!isLoading) {
                                if (mOnLoadMoreListener != null) {
                                    mOnLoadMoreListener.onLoadMore();
                                }
                            }
                            isLoading = true;
                        }

                    }

                }
            });

        }

        void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
            this.mOnLoadMoreListener = mOnLoadMoreListener;
        }

        void setOnItemClickListener(LoadMoreAdapterListener loadMoreAdapterListener) {
            this.loadMoreAdapterListener = loadMoreAdapterListener;
        }

        @Override
        public int getItemViewType(int position) {
            return mImageDataModels.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_ITEM) {
                View view = LayoutInflater.from(LoadMoreListActivity.this).inflate(R.layout.layout_user_item, parent, false);
                return new UserViewHolder(view);
            } else if (viewType == VIEW_TYPE_LOADING) {
                View view = LayoutInflater.from(LoadMoreListActivity.this).inflate(R.layout.layout_loading_item, parent, false);
                return new LoadingViewHolder(view);
            }
            return null;
        }

        public void scale(final View imageView) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(3.0f, 1f, 3.0f, 1f, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
            scaleAnimation.setDuration(2500); // scale to 3 times as big in 3 seconds

            imageView.startAnimation(scaleAnimation);
            scaleAnimation.setAnimationListener(new Animation.AnimationListener() {


                public void onAnimationEnd(Animation animation) {
                    Log.d("ScaleActivity", "Scale started...");
//                    ScaleAnimation scaleAnimation = new ScaleAnimation(2.0f, 1f, 2.0f, 1f, imageView.getWidth() / 2.0f, imageView.getHeight() / 2.0f);
//                    ScaleAnimation scaleAnimation = new ScaleAnimation(2.0f, 1f, 2.0f, 1f, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
//                    scaleAnimation.setDuration(2000); // scale to 3 times as big in 3 seconds
//
//                    imageView.startAnimation(scaleAnimation);
                }


                public void onAnimationRepeat(Animation animation) {

                }


                public void onAnimationStart(Animation animation) {
                    Log.d("ScaleActivity", "Scale ended...");
                }

            });
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof UserViewHolder) {
                final ImageDataModel imageDataModel = mImageDataModels.get(position);
                final UserViewHolder userViewHolder = (UserViewHolder) holder;

                userViewHolder.tvName.setText(imageDataModel.getName());
                userViewHolder.tvEmailId.setText(imageDataModel.getDate());

                userViewHolder.progressBar.setVisibility(View.VISIBLE);

                GlideApp.with(context).load(imageDataModel.getImageUrl()).listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        userViewHolder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).placeholder(new ColorDrawable(Color.BLACK)).fitCenter().into(userViewHolder.imageView);
//                transition(GenericTransitionOptions.with(R.anim.scale_anim))

                if (position > 1 && position % 3 == 0) {

                    scale(userViewHolder.imageView);

                } else {

                    if (userViewHolder.imageView.getVisibility() == View.VISIBLE) {

                        Animation out = AnimationUtils.makeInAnimation(context, true);
                        userViewHolder.imageView.startAnimation(out);
                        userViewHolder.imageView.setVisibility(View.VISIBLE);

                    } else {

                        Animation in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
                        userViewHolder.imageView.startAnimation(in);
                        userViewHolder.imageView.setVisibility(View.VISIBLE);

                    }

                }

                userViewHolder.itemContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadMoreAdapterListener.onClick(position, imageDataModel, userViewHolder.imageView);
                    }
                });

            } else if (holder instanceof LoadingViewHolder) {
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setIndeterminate(true);
            }

        }

        @Override
        public int getItemCount() {
//            int length = mImageDataModels.size();
//            length = (int) (Math.round(length * (0.50)));
//
//            return mImageDataModels == null ? 0 : length;
            return mImageDataModels == null ? 0 : mImageDataModels.size();
        }

        void setLoaded() {
            isLoading = false;
        }
    }

}
