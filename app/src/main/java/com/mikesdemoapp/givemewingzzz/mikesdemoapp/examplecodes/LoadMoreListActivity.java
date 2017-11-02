package com.mikesdemoapp.givemewingzzz.mikesdemoapp.examplecodes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.Toast;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome.AppConstants;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome.DemoUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    HashMap<String, List<ImageDataModel>> imageMap = new HashMap<>();

    private UserAdapter mUserAdapter;
    private static final String TAG = LoadMoreListActivity.class.getSimpleName();
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private boolean isListShowing = false;

    private void switchStaggeredView() {
        isListShowing = false;
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mUserAdapter);
    }

    private void switchLinearView() {
        isListShowing = true;
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mUserAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_base_layout);

        ButterKnife.bind(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.baseRecycleView);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        linearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mUserAdapter = new UserAdapter(this, getDefaultImageListFromUtils());
        mRecyclerView.setAdapter(mUserAdapter);

//        switchStaggeredView();

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.base_switch_fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (!isListShowing) {
//                    switchLinearView();
                }

            }
        });

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
//
                String imageUrl = null;
                String imageName = null;
                String imageDate = null;

                imageUrl = imageDataModel.getImageUrl();
                imageName = imageDataModel.getName();
                imageDate = imageDataModel.getDate();

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

        Log.d(TAG, "ImageDataModel RANDOM IMG TYPE SIZE --> " + DemoUtils.Constants.getRandomImageType().size());

        int randomPositionIndex = DemoUtils.randInt(0, DemoUtils.Constants.getRandomImageType().size() - 1);

        Log.d(TAG, "ImageDataModel Random Index --> " + randomPositionIndex);

        imageDataModel = new ImageDataModel();

        imageDataModel.setDate(i + "/" + (i + 4) + "/" + 2017);

        Log.d(TAG, "Image URLS --> Index --> " + i + " : URLS --> " + DemoUtils.imageUrls().get(i));

        imageDataModel.setImageCategory(DemoUtils.Constants.getRandomImageType().get(randomPositionIndex));

        String imageType = imageDataModel.getImageCategory();
        Log.d(TAG, "Map Image List : Image Type --> " + imageType);

        String imageName = null;
        String imageUrl = null;

        switch (imageType) {

            case DemoUtils.Constants.GAMING_TYPE:

                imageName = DemoUtils.Constants.GAMING_TYPE;
                imageUrl = DemoUtils.imageUrls().get(i);

                break;

            case DemoUtils.Constants.LANDSCAPE_TYPE:

                imageName = DemoUtils.Constants.LANDSCAPE_TYPE;
                imageUrl = DemoUtils.imageUrls().get(i);

                break;

            case DemoUtils.Constants.RANDOM_TYPE:

                imageName = DemoUtils.Constants.RANDOM_TYPE;
                imageUrl = DemoUtils.imageUrls().get(i);

                break;

            case DemoUtils.Constants.OTHERS_TYPE:

                imageName = DemoUtils.Constants.OTHERS_TYPE;
                imageUrl = DemoUtils.imageUrls().get(i);

                break;

        }

        imageDataModel.setName("IMGUR Image " + i + " ( " + imageName + " ) ");
        imageDataModel.setImageUrl(imageUrl);

        return imageDataModel;
    }

    private List<ImageDataModel> getDefaultImageListFromUtils() {

        if (mImageDataModels.isEmpty()) {

            for (int i = 0; i < 9; i++) {
                ImageDataModel imageDataModel = addImageObj(i); // Todo : maybe add logic here to add items in groups
                mImageDataModels.add(imageDataModel);

                imageMap.put(mImageDataModels.get(i).getImageCategory(), mImageDataModels);

            }

            Collections.sort(mImageDataModels, new ExampleImageComparator());
//            logImageMap(imageMap);

        }


        return mImageDataModels;
    }

    private HashMap<String, List<ImageDataModel>> addDefaultImageMap() {
        return imageMap;
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

    private void logImageMap(HashMap<String, List<ImageDataModel>> imageMap) {

        Log.d(TAG, "logImageMap Image List : Image Size --> " + imageMap.size());

        for (Map.Entry<String, List<ImageDataModel>> entry : imageMap.entrySet()) {

            System.out.printf("Image Map Key Category : %s and Image List Value: %s %n", entry.getKey(), entry.getValue());

            List<ImageDataModel> imageDataModelList = entry.getValue();

            for (int i = 0; i < imageDataModelList.size() - 1; i++) {

                Log.d(TAG, "Map Image List : Image Index " + i);

                String imageType = null;
                if (imageDataModelList.get(i) != null) {
                    imageType = imageDataModelList.get(i).getImageCategory();
                    Log.d(TAG, "Map Image List : Image Type --> " + imageType);
                } else if (imageDataModelList.get(i) == null) {

                    if (imageDataModelList.get(i + 1) != null) {
                        imageType = imageDataModelList.get(i + 1).getImageCategory();
                        Log.d(TAG, "Map Image List : Image Type --> " + imageType);
                    }

                } else {
                    Log.d(TAG, "Map Image List : Image Type --> " + "NOT VALID");
                }

                String imageName = null;
                String imageTy = null;
                int imageCategoryStartIndex = -1;
                ImageDataModel imageDataModel;

                if (imageType != null) {
                    switch (imageType) {

                        case DemoUtils.Constants.GAMING_TYPE:

                            imageCategoryStartIndex = DemoUtils.Constants.GAMING_TYPE_INDEX;
                            Log.d(TAG, "Map Image List : Image Type Switch Index --> " + imageCategoryStartIndex);

                            imageDataModel = imageDataModelList.get(i);

                            if (imageDataModel != null) {
                                imageName = imageDataModel.getName();
                                imageTy = imageDataModel.getImageCategory();

                                Log.d(TAG, "Map Image List : Image Type Switch Image Name --> " + imageName);
                                Log.d(TAG, "Map Image List : Image Type Switch Image Type --> " + imageTy);

                            } else {

                                imageDataModel = imageDataModelList.get(i);

                                if (imageDataModel != null) {
                                    imageName = imageDataModel.getName();
                                    imageTy = imageDataModel.getImageCategory();

                                    Log.d(TAG, "Map Image List : Image Type Switch Image Name --> " + imageName);
                                    Log.d(TAG, "Map Image List : Image Type Switch Image Type --> " + imageTy);

                                } else {

                                    Log.d(TAG, "Map Image List : Image Type Switch Image Name --> " + "NAME NULL");
                                    Log.d(TAG, "Map Image List : Image Type Switch Image Type --> " + "TYPE NULL");

                                }

                            }

                            break;

                        case DemoUtils.Constants.LANDSCAPE_TYPE:

                            imageCategoryStartIndex = DemoUtils.Constants.LANDSCAPE_TYPE_INDEX;
                            Log.d(TAG, "Map Image List : Image Type Switch Index --> " + imageCategoryStartIndex);

                            imageDataModel = imageDataModelList.get(i);

                            if (imageDataModel != null) {
                                imageName = imageDataModel.getName();
                                imageTy = imageDataModel.getImageCategory();

                                Log.d(TAG, "Map Image List : Image Type Switch Image Name --> " + imageName);
                                Log.d(TAG, "Map Image List : Image Type Switch Image Type --> " + imageTy);

                            } else {

                                imageDataModel = imageDataModelList.get(i);

                                if (imageDataModel != null) {
                                    imageName = imageDataModel.getName();
                                    imageTy = imageDataModel.getImageCategory();

                                    Log.d(TAG, "Map Image List : Image Type Switch Image Name --> " + imageName);
                                    Log.d(TAG, "Map Image List : Image Type Switch Image Type --> " + imageTy);

                                } else {

                                    Log.d(TAG, "Map Image List : Image Type Switch Image Name --> " + "NAME NULL");
                                    Log.d(TAG, "Map Image List : Image Type Switch Image Type --> " + "TYPE NULL");

                                }

                            }

                            break;

                        case DemoUtils.Constants.RANDOM_TYPE:

                            imageCategoryStartIndex = DemoUtils.Constants.RANDOM_TYPE_INDEX;
                            Log.d(TAG, "Map Image List : Image Type Switch Index --> " + imageCategoryStartIndex);

                            imageDataModel = imageDataModelList.get(i);

                            if (imageDataModel != null) {
                                imageName = imageDataModel.getName();
                                imageTy = imageDataModel.getImageCategory();

                                Log.d(TAG, "Map Image List : Image Type Switch Image Name --> " + imageName);
                                Log.d(TAG, "Map Image List : Image Type Switch Image Type --> " + imageTy);

                            } else {

                                imageDataModel = imageDataModelList.get(i);

                                if (imageDataModel != null) {
                                    imageName = imageDataModel.getName();
                                    imageTy = imageDataModel.getImageCategory();

                                    Log.d(TAG, "Map Image List : Image Type Switch Image Name --> " + imageName);
                                    Log.d(TAG, "Map Image List : Image Type Switch Image Type --> " + imageTy);

                                } else {

                                    Log.d(TAG, "Map Image List : Image Type Switch Image Name --> " + "NAME NULL");
                                    Log.d(TAG, "Map Image List : Image Type Switch Image Type --> " + "TYPE NULL");

                                }

                            }

                            break;

                        case DemoUtils.Constants.OTHERS_TYPE:

                            imageCategoryStartIndex = DemoUtils.Constants.OTHERS_TYPE_INDEX;
                            Log.d(TAG, "Map Image List : Image Type Switch Index --> " + imageCategoryStartIndex);

                            imageDataModel = imageDataModelList.get(i);

                            if (imageDataModel != null) {
                                imageName = imageDataModel.getName();
                                imageTy = imageDataModel.getImageCategory();

                                Log.d(TAG, "Map Image List : Image Type Switch Image Name --> " + imageName);
                                Log.d(TAG, "Map Image List : Image Type Switch Image Type --> " + imageTy);

                            } else {

                                imageDataModel = imageDataModelList.get(i);

                                if (imageDataModel != null) {
                                    imageName = imageDataModel.getName();
                                    imageTy = imageDataModel.getImageCategory();

                                    Log.d(TAG, "Map Image List : Image Type Switch Image Name --> " + imageName);
                                    Log.d(TAG, "Map Image List : Image Type Switch Image Type --> " + imageTy);

                                } else {

                                    Log.d(TAG, "Map Image List : Image Type Switch Image Name --> " + "NAME NULL");
                                    Log.d(TAG, "Map Image List : Image Type Switch Image Type --> " + "TYPE NULL");

                                }

                            }

                            break;

                    }
                }

                Log.d(TAG, "Map Image List : Image Name --> " + imageName + " : Image Index --> " + i + " : Image Category Index --> " + imageCategoryStartIndex + " : Image Type --> " + imageTy);
            }

        }

    }

    /**
     * @param i Index of the list
     */
    private void addMoreItemsMap(int i, List<ImageDataModel> mImageDataModels) {

        if (mImageDataModels.get(i) != null) {

            imageMap.put(mImageDataModels.get(i).getImageCategory(), mImageDataModels);

            mUserAdapter.notifyDataSetChanged();
            mUserAdapter.setLoaded();

//            logImageMap(imageMap);

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
            endListIndex = index + 9;
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

//    private void addMoreItems() {
//
//        /**/
//
////        Remove loading item
//        mImageDataModels.remove(mImageDataModels.size() - 1);
//        mUserAdapter.notifyItemRemoved(mImageDataModels.size());
//
//        // Todo : at this point, add the categoried model in the beginning of the list
//
//        int index = mImageDataModels.size() - 1;
//
//        Log.d(TAG, "ImageDataModel Current Index --> " + (index));
//
//        int endImageIndex = DemoUtils.imageUrls().size() - 1;
//
//        int end = DemoUtils.imageUrls().size() - (endImageIndex); // End Img index - 88
//
//        Log.d(TAG, "ImageDataModel End Img Index --> " + (end));
//
//        int endListIndex = 0;
//
//        int randomPositionIndex = DemoUtils.randInt(0, 3);
//
//        Log.d(TAG, "ImageDataModel Random Index --> " + randomPositionIndex);
//
//        if (index + 3 < end) {
//            endListIndex = index + 3;
//        } else {
//            endListIndex = end;
//        }
//
//        Log.d(TAG, "ImageDataModel End List Index --> " + (endListIndex));
//        ImageDataModel imageDataModel;
//
//        for (int i = index; i <= endListIndex; i++) {
//
//            Log.d(TAG, "ImageDataModel Current Index In FOR --> " + (i));
//
//            imageDataModel = addImageObj(i);
//
//            if (imageDataModel != null) {
//                Log.d(TAG, "ADDING MORE --> " + " Image NAME --> " + imageDataModel.getName());
//                Log.d(TAG, "ADDING MORE -->ADDING " + " Image DATE --> " + imageDataModel.getDate());
//                Log.d(TAG, "ADDING MORE --> " + " Image URL --> " + imageDataModel.getImageUrl());
//                Log.d(TAG, "ADDING MORE --> " + " Image CATEGORY --> " + imageDataModel.getImageCategory());
//
//                Log.d(TAG, "ADDING MORE -->ADDING " + "Load More 3 Items");
//
//                // Todo : check for the type again
//                mImageDataModels.add(imageDataModel);
//
//                mUserAdapter.notifyDataSetChanged();
//                mUserAdapter.setLoaded();
//
////                addMoreItemsMap(i, mImageDataModels);
//
//            } else {
//
//                imageDataModel = addImageObj(i);
//
//                Log.d(TAG, "ADDING MORE -->ADDING INDEX + 1 " + "Load More 3 Items");
//
//                if (imageDataModel != null) {
//                    Log.d(TAG, "ADDING MORE --> ADDING INDEX + 1 " + " Image NAME --> " + imageDataModel.getName());
//                    Log.d(TAG, "ADDING MORE --> ADDING INDEX + 1 " + " Image DATE --> " + imageDataModel.getDate());
//                    Log.d(TAG, "ADDING MORE --> ADDING INDEX + 1 " + " Image URL --> " + imageDataModel.getImageUrl());
//                    Log.d(TAG, "ADDING MORE --> ADDING INDEX + 1 " + " Image CATEGORY --> " + imageDataModel.getImageCategory());
//
//                    Log.d(TAG, "Load More 3 Items");
//
//                    // Todo : check for the type again
//                    mImageDataModels.add(imageDataModel);
//
////                    addMoreItemsMap(i, mImageDataModels);
//                    mUserAdapter.notifyDataSetChanged();
//                    mUserAdapter.setLoaded();
//
//
//                }
//                else {
////                    addMoreItemsMap(i, mImageDataModels);
////                    mUserAdapter.notifyDataSetChanged();
////                    mUserAdapter.setLoaded();
//                }
//
//            }
//
//        }
//
//        /**/
//
//    }

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
        TextView tvDateId;
        TextView tvCategoryId;
        ImageView imageView;
        ProgressBar progressBar;
        LinearLayout itemContainer;

        UserViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.loadMoreTVName);
            tvDateId = itemView.findViewById(R.id.loadMoreTVDateId);
            tvCategoryId = itemView.findViewById(R.id.loadMoreTVImageCategory);
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

    private static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView categoryType;
        TextView categoryNumber;

        CategoryViewHolder(View itemView) {
            super(itemView);
            categoryType = itemView.findViewById(R.id.imageCategoryName);
            categoryNumber = itemView.findViewById(R.id.imageCategoryNumber);
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final int VIEW_TYPE_ITEM = 0;
        private final int VIEW_TYPE_LOADING = 1;
        private final int VIEW_TYPE_IMAGE_CATEGORY = 2;
        private OnLoadMoreListener mOnLoadMoreListener;
        private LoadMoreAdapterListener loadMoreAdapterListener;
        private boolean isLoading;
        private int visibleThreshold = 3;
        private int visibleGridThreshold = 0;
        private int lastVisibleItem, totalItemCount;
        private int[] lastVisibleItems;
        private Context context;
        private List<ImageDataModel> mImageDataModels;
        private List<ImageDataModel> mGamingTypeImageList;
        private List<ImageDataModel> mLandscapeImageList;
        private List<ImageDataModel> mOtherImageList;
        private List<ImageDataModel> mRandomImageList;

        public UserAdapter(Context context, List<ImageDataModel> mImageDataModels) {
            this.context = context;
            this.mImageDataModels = mImageDataModels;

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
//                        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
//                        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                            @Override
//                            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                                super.onScrollStateChanged(recyclerView, newState);
//                            }
//
//                            @Override
//                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                                super.onScrolled(recyclerView, dx, dy);
//
//                                totalItemCount = linearLayoutManager.getItemCount();
//                                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
//
//                                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
//                                    if (mOnLoadMoreListener != null) {
//                                        if (totalItemCount >= 0 && lastVisibleItem >= 4) {
//                                            mOnLoadMoreListener.onLoadMore();
//                                        }
//
//                                    }
//                                    isLoading = true;
//                                }
//                            }
//                        });

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

            int viewType;

            if (mImageDataModels.get(position) == null) {

                viewType = DemoUtils.Constants.VIEW_TYPE_LOADING_INDEX;

            } else {

                String imageCat = mImageDataModels.get(position).getImageCategory();

                if (DemoUtils.Constants.GAMING_TYPE.equals(imageCat)) {

                    viewType = DemoUtils.Constants.GAMING_TYPE_INDEX;

                } else if (DemoUtils.Constants.LANDSCAPE_TYPE.equals(imageCat)) {

                    viewType = DemoUtils.Constants.LANDSCAPE_TYPE_INDEX;

                } else if (DemoUtils.Constants.OTHERS_TYPE.equals(imageCat)) {

                    viewType = DemoUtils.Constants.OTHERS_TYPE_INDEX;

                } else if (DemoUtils.Constants.RANDOM_TYPE.equals(imageCat)) {

                    viewType = DemoUtils.Constants.RANDOM_TYPE_INDEX;

                } else {

                    viewType = DemoUtils.Constants.NULL_TYPE_INDEX;

                }

            }

            return viewType != -1 ? viewType : -1;
        }

        //        @Override
//        public int getItemViewType(int position) {
//
//            if (mImageDataModels.get(position) == null) {
//
//                Log.d(TAG, "getItemViewType POSITION (+1)-->" + position + " : View TYPE -->" + DemoUtils.Constants.VIEW_TYPE_LOADING);
//                return DemoUtils.Constants.VIEW_TYPE_LOADING_INDEX;
//
//            } else { // Todo : Maybe add logic here to check the position and then add category view
//
//                String viewType;
//
//                if (mImageDataModels.get(position) == null) {
//
//                    viewType = mImageDataModels.get(position - 1).getImageCategory();
//                    Log.d(TAG, "getItemViewType POSITION (-1) -->" + position + " : View TYPE -->" + viewType);
//
//                } else {
//
//                    viewType = mImageDataModels.get(position).getImageCategory();
//                    Log.d(TAG, "getItemViewType POSITION -->" + position + " : View TYPE -->" + viewType);
//
//                    switch (viewType) {
//
//                        case DemoUtils.Constants.GAMING_TYPE:
//
//                            int gamingIndex = DemoUtils.Constants.GAMING_TYPE_INDEX;
//
//                            String nextImageCategory = mImageDataModels.get(gamingIndex + 1).getImageCategory();
//                            String currentImageCategory = mImageDataModels.get(gamingIndex).getImageCategory();
//
////                        int categoryTypeIndex = DemoUtils.Constants.CATEGORY_TYPE_INDEX;
////
////                        if (nextImageCategory.equals(currentImageCategory)) {
////                            return categoryTypeIndex;
////                        }
//
//                            return DemoUtils.Constants.GAMING_TYPE_INDEX;
//
//                        case DemoUtils.Constants.LANDSCAPE_TYPE:
//
//                            int landScapeIndex = DemoUtils.Constants.LANDSCAPE_TYPE_INDEX;
//
//                            String nextLandscapeCategory = mImageDataModels.get(landScapeIndex + 1).getImageCategory();
//                            String currentLandscapeCategory = mImageDataModels.get(landScapeIndex).getImageCategory();
//
////                        int LandscapeCategoryTypeIndex = DemoUtils.Constants.CATEGORY_TYPE_INDEX;
////
////                        if (nextLandscapeCategory.equals(currentLandscapeCategory)) {
////                            return LandscapeCategoryTypeIndex;
////                        }
//
//                            return DemoUtils.Constants.LANDSCAPE_TYPE_INDEX;
//
//                        case DemoUtils.Constants.OTHERS_TYPE:
//
//                            int othersIndex = DemoUtils.Constants.OTHERS_TYPE_INDEX;
//
//                            String nextothersImageCategory = mImageDataModels.get(othersIndex + 1).getImageCategory();
//                            String currentothersImageCategory = mImageDataModels.get(othersIndex).getImageCategory();
//
////                        int othersCategoryTypeIndex = DemoUtils.Constants.CATEGORY_TYPE_INDEX;
////
////                        if (nextothersImageCategory.equals(currentothersImageCategory)) {
////                            return othersCategoryTypeIndex;
////                        }
//
//                            return DemoUtils.Constants.OTHERS_TYPE_INDEX;
//
//                        case DemoUtils.Constants.RANDOM_TYPE:
//
//                            int randomImageIndex = DemoUtils.Constants.RANDOM_TYPE_INDEX;
//
//                            String nextrandomImageCategory = mImageDataModels.get(randomImageIndex + 1).getImageCategory();
//                            String currentrandomImageCategory = mImageDataModels.get(randomImageIndex).getImageCategory();
//
////                        int randomCategoryTypeIndex = DemoUtils.Constants.CATEGORY_TYPE_INDEX;
////
////                        if (nextrandomImageCategory.equals(currentrandomImageCategory)) {
////                            return randomCategoryTypeIndex;
////                        }
//
//                            return DemoUtils.Constants.RANDOM_TYPE_INDEX;
//
//                        default:
//                            return -1;
//                    }
//
//                }
//                return -1;
//            }
//
//        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            int layoutID = 0;
            View view;

            int gamingListCountCounter = -1;
            int landscapeListCountCounter = -1;
            int othersListCountCounter = -1;
            int randomListCountCounter = -1;

            switch (viewType) {

                case DemoUtils.Constants.GAMING_TYPE_INDEX:

                    layoutID = R.layout.layout_user_item;
                    view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
                    return new UserViewHolder(view);

                case DemoUtils.Constants.LANDSCAPE_TYPE_INDEX:

                    layoutID = R.layout.layout_user_item;
                    view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
                    return new UserViewHolder(view);

                case DemoUtils.Constants.OTHERS_TYPE_INDEX:

                    layoutID = R.layout.layout_user_item;
                    view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
                    return new UserViewHolder(view);

                case DemoUtils.Constants.RANDOM_TYPE_INDEX:

                    layoutID = R.layout.layout_user_item;
                    view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
                    return new UserViewHolder(view);

                case DemoUtils.Constants.VIEW_TYPE_LOADING_INDEX:

                    layoutID = R.layout.layout_loading_item;
                    view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);

                    return new LoadingViewHolder(view);

            }

//            switch (viewType) {
//
//                case DemoUtils.Constants.GAMING_TYPE_INDEX:
//
//                    gamingListCountCounter++;
//
//                    mGamingTypeImageList = new ArrayList<>();
//                    mGamingTypeImageList.add(mImageDataModels.get(DemoUtils.Constants.GAMING_TYPE_INDEX));
//
//                    layoutID = R.layout.layout_user_item;
//                    view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
//
//                    return new UserViewHolder(view);
//
//                case DemoUtils.Constants.LANDSCAPE_TYPE_INDEX:
//
//                    mLandscapeImageList = new ArrayList<>();
//                    mLandscapeImageList.add(mImageDataModels.get(DemoUtils.Constants.LANDSCAPE_TYPE_INDEX));
//
//                    layoutID = R.layout.layout_user_item;
//                    view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
//
//                    return new UserViewHolder(view);
//
//                case DemoUtils.Constants.OTHERS_TYPE_INDEX:
//
//                    mOtherImageList = new ArrayList<>();
//                    mOtherImageList.add(mImageDataModels.get(DemoUtils.Constants.OTHERS_TYPE_INDEX));
//
//                    layoutID = R.layout.layout_user_item;
//                    view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
//
//                    return new UserViewHolder(view);
//
//                case DemoUtils.Constants.RANDOM_TYPE_INDEX:
//
//                    mRandomImageList = new ArrayList<>();
//                    mRandomImageList.add(mImageDataModels.get(DemoUtils.Constants.RANDOM_TYPE_INDEX));
//
//                    layoutID = R.layout.layout_user_item;
//                    view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
//
//                    return new UserViewHolder(view);
//
//                case DemoUtils.Constants.VIEW_TYPE_LOADING_INDEX:
//
//                    layoutID = R.layout.layout_loading_item;
//                    view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
//
//                    return new LoadingViewHolder(view);
//
//                case DemoUtils.Constants.CATEGORY_TYPE_INDEX:
//
//                    layoutID = R.layout.image_category_item;
//                    view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
//
//                    return new CategoryViewHolder(view);
//
//            }

            return null;

        }

        ImageDataModel imageDataModel = null;

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            int viewType = getItemViewType(position);

            Log.d(TAG, "Adapter View Type --> " + viewType);


            CategoryViewHolder categoryViewHolder;
            final UserViewHolder userViewHolder;

            if (holder instanceof UserViewHolder) {

                userViewHolder = (UserViewHolder) holder;
                imageDataModel = mImageDataModels.get(position);
                String imageTypeCategory = null;
                String imageDate = null;
                String imageName = null;
                String imageUrl = null;

                // Todo : Add logic here to add views

                /**/

                switch (viewType) {

                    case DemoUtils.Constants.GAMING_TYPE_INDEX:

                        imageName = imageDataModel.getName();
                        imageUrl = imageDataModel.getImageUrl();
                        imageDate = imageDataModel.getDate();
                        imageTypeCategory = DemoUtils.Constants.GAMING_TYPE;

                        break;

                    case DemoUtils.Constants.LANDSCAPE_TYPE_INDEX:

                        imageName = imageDataModel.getName();
                        imageUrl = imageDataModel.getImageUrl();
                        imageDate = imageDataModel.getDate();
                        imageTypeCategory = DemoUtils.Constants.LANDSCAPE_TYPE;

                        break;

                    case DemoUtils.Constants.OTHERS_TYPE_INDEX:

                        imageName = imageDataModel.getName();
                        imageUrl = imageDataModel.getImageUrl();
                        imageDate = imageDataModel.getDate();
                        imageTypeCategory = DemoUtils.Constants.OTHERS_TYPE;

                        break;

                    case DemoUtils.Constants.RANDOM_TYPE_INDEX:


                        imageName = imageDataModel.getName();
                        imageUrl = imageDataModel.getImageUrl();
                        imageDate = imageDataModel.getDate();
                        imageTypeCategory = DemoUtils.Constants.GAMING_TYPE;

                        break;

                }

                Log.d(TAG, "onBindViewHolder Adapter View Position --> " + position + "\n");
                Log.d(TAG, "onBindViewHolder Image Name " + imageName);
                Log.d(TAG, "onBindViewHolder Image Url " + imageUrl);
                Log.d(TAG, "onBindViewHolder Image Date " + imageDate);
                Log.d(TAG, "onBindViewHolder Image Category " + imageTypeCategory);

                if (imageName == null) {
                    userViewHolder.tvName.setText("NAME NULL");
                } else {
                    userViewHolder.tvName.setText(imageName);
                }

                if (imageDate == null) {
                    userViewHolder.tvDateId.setText("DATE NULL");
                } else {
                    userViewHolder.tvDateId.setText(imageDate);
                }

                if (imageTypeCategory == null) {
                    userViewHolder.tvName.setText("CATEGORY NULL");
                } else {
                    userViewHolder.tvCategoryId.setText(imageTypeCategory);
                }

                userViewHolder.progressBar.setVisibility(View.VISIBLE);

                GlideApp.with(context).load(imageUrl).listener(new RequestListener<Drawable>() {
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
//                        transition(GenericTransitionOptions.with(R.anim.scale_anim))

                userViewHolder.itemContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadMoreAdapterListener.onClick(position, imageDataModel, userViewHolder.imageView);
                    }
                });

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

                /**/

            } else if (holder instanceof LoadingViewHolder) {

                Toast.makeText(context, "VIEW_TYPE_LOADING_INDEX", Toast.LENGTH_SHORT).show();
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setIndeterminate(true);

            }

//            else if (holder instanceof CategoryViewHolder) {
//
//                mGamingTypeImageList = new ArrayList<>();
//                mGamingTypeImageList.add(mImageDataModels.get(position));
//
//                Toast.makeText(context, "VIEW_TYPE_GAMING_INDEX", Toast.LENGTH_SHORT).show();
//                categoryViewHolder = (CategoryViewHolder) holder;
//                categoryViewHolder.categoryType.setText(mImageDataModels.get(mGamingTypeImageList.size() - 1).getImageCategory());
//
//            }

//            else if (holder instanceof CategoryViewHolder) {
//                Toast.makeText(context, "VIEW_TYPE_LOADING_INDEX", Toast.LENGTH_SHORT).show();
//            }

//            switch (viewType) {
//
//                case DemoUtils.Constants.GAMING_TYPE_INDEX:
//
//                    Toast.makeText(context, "GAMING_TYPE_INDEX", Toast.LENGTH_SHORT).show();
//
////                    int gamingIndex = DemoUtils.Constants.GAMING_TYPE_INDEX;
////
////                    categoryViewHolder = (CategoryViewHolder) holder;
////                    imageDataModel = mImageDataModels.get(gamingIndex);
////
////                    int oneLessGamingPosition = gamingIndex - 1; // For inserting one view of category type and rest of items
////
////                    categoryViewHolder.categoryType.setText(imageDataModel.getImageCategory());
////                    categoryViewHolder.categoryNumber.setText("9");
//
//                    break;
//
//                case DemoUtils.Constants.LANDSCAPE_TYPE_INDEX:
//
//                    Toast.makeText(context, "LANDSCAPE_TYPE_INDEX", Toast.LENGTH_SHORT).show();
////                    categoryViewHolder = (CategoryViewHolder) holder;
//
//                    break;
//
//                case DemoUtils.Constants.OTHERS_TYPE_INDEX:
//
//                    Toast.makeText(context, "OTHERS_TYPE_INDEX", Toast.LENGTH_SHORT).show();
////                    categoryViewHolder = (CategoryViewHolder) holder;
//
//                    break;
//
//                case DemoUtils.Constants.RANDOM_TYPE_INDEX:
//
//                    Toast.makeText(context, "RANDOM_TYPE_INDEX", Toast.LENGTH_SHORT).show();
////                    imageDataModel = mImageDataModels.get(position);
////                    categoryViewHolder = (CategoryViewHolder) holder;
//
//                    break;
//
//                case DemoUtils.Constants.VIEW_TYPE_LOADING_INDEX:
//
//                    Toast.makeText(context, "VIEW_TYPE_LOADING_INDEX", Toast.LENGTH_SHORT).show();
//                    LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
//                    loadingViewHolder.progressBar.setIndeterminate(true);
//
//                    break;
//
//                case DemoUtils.Constants.VIEW_TYPE_ITEM_INDEX:
//
//                    Toast.makeText(context, "VIEW_TYPE_ITEM_INDEX", Toast.LENGTH_SHORT).show();
//                    imageDataModel = mImageDataModels.get(position);
//                    userViewHolder = (UserViewHolder) holder;
//
//                    userViewHolder.tvName.setText(imageDataModel.getName());
//                    userViewHolder.tvDateId.setText(imageDataModel.getDate());
//
//                    userViewHolder.progressBar.setVisibility(View.VISIBLE);
//
//                    GlideApp.with(context).load(imageDataModel.getImageUrl()).listener(new RequestListener<Drawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            userViewHolder.progressBar.setVisibility(View.GONE);
//                            return false;
//                        }
//                    }).placeholder(new ColorDrawable(Color.BLACK)).fitCenter().into(userViewHolder.imageView);
////                transition(GenericTransitionOptions.with(R.anim.scale_anim))
//
//                    if (position > 1 && position % 3 == 0) {
//
//                        scale(userViewHolder.imageView);
//
//                    } else {
//
//                        if (userViewHolder.imageView.getVisibility() == View.VISIBLE) {
//
//                            Animation out = AnimationUtils.makeInAnimation(context, true);
//                            userViewHolder.imageView.startAnimation(out);
//                            userViewHolder.imageView.setVisibility(View.VISIBLE);
//
//                        } else {
//
//                            Animation in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
//                            userViewHolder.imageView.startAnimation(in);
//                            userViewHolder.imageView.setVisibility(View.VISIBLE);
//
//                        }
//
//                    }
//
//                    userViewHolder.itemContainer.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            loadMoreAdapterListener.onClick(position, imageDataModel, userViewHolder.imageView);
//                        }
//                    });
//
//                    break;
//
//            }

//            if (holder instanceof UserViewHolder) {
//                final ImageDataModel imageDataModel = mImageDataModels.get(position);
//                final UserViewHolder userViewHolder = (UserViewHolder) holder;
//
//                userViewHolder.tvName.setText(imageDataModel.getName());
//                userViewHolder.tvDateId.setText(imageDataModel.getDate());
//
//                userViewHolder.progressBar.setVisibility(View.VISIBLE);
//
//                GlideApp.with(context).load(imageDataModel.getImageUrl()).listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        userViewHolder.progressBar.setVisibility(View.GONE);
//                        return false;
//                    }
//                }).placeholder(new ColorDrawable(Color.BLACK)).fitCenter().into(userViewHolder.imageView);
////                transition(GenericTransitionOptions.with(R.anim.scale_anim))
//
//                if (position > 1 && position % 3 == 0) {
//
//                    scale(userViewHolder.imageView);
//
//                } else {
//
//                    if (userViewHolder.imageView.getVisibility() == View.VISIBLE) {
//
//                        Animation out = AnimationUtils.makeInAnimation(context, true);
//                        userViewHolder.imageView.startAnimation(out);
//                        userViewHolder.imageView.setVisibility(View.VISIBLE);
//
//                    } else {
//
//                        Animation in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
//                        userViewHolder.imageView.startAnimation(in);
//                        userViewHolder.imageView.setVisibility(View.VISIBLE);
//
//                    }
//
//                }
//
//                userViewHolder.itemContainer.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        loadMoreAdapterListener.onClick(position, imageDataModel, userViewHolder.imageView);
//                    }
//                });
//
//            } else if (holder instanceof LoadingViewHolder) {
//                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
//                loadingViewHolder.progressBar.setIndeterminate(true);
//            }

        }

        @Override
        public int getItemCount() {
//            int length = mImageDataModels.size();
//            length = (int) (Math.round(length * (0.50)));
//
//            return mImageDataModels == null ? 0 : length;
            return mImageDataModels == null ? 0 : mImageDataModels.size();
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

        void setLoaded() {
            isLoading = false;
        }
    }

}
