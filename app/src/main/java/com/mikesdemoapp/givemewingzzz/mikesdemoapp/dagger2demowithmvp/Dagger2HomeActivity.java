package com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.data.DataManager;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.components.ActivityComponent;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.components.DaggerActivityComponent;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp.di.modules.ActivityModule;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome.DemoUtils;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome.MikesDemoApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp
 * Created by GiveMeWingzzz on 11/3/2017.
 */

public class Dagger2HomeActivity extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener, PopupMenu.OnMenuItemClickListener {

    @Inject
    DataManager dataManager;

    private ActivityComponent activityComponent;

    @BindView(R.id.tv_user_info)
    TextView mTvUserInfo;

    @BindView(R.id.tv_access_token)
    TextView mTvAccessToken;

    @BindView(R.id.generateUsersBtn)
    Button generateUsersButton;

    @BindView(R.id.searchUsersView)
    MaterialSearchBar materialSearchBar;

    private final int defaultDaggerUserListSize = 25;
    List<DaggerUser> daggerUserList = new ArrayList<>();

    /*
     * Random Users Gen Vars
     *
     */
    // class variable
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    final java.util.Random rand = new java.util.Random();

    // consider using a Map<String,Boolean> to say whether the identifier is being used or not
    final Set<String> identifiers = new HashSet<String>();

    /*............*/

    public ActivityComponent getActivityComponent() {

        if (activityComponent == null) {

            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(MikesDemoApplication.get(this).getComponent())
                    .build();

        }

        return activityComponent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dagger_mvp_home_layout);

        ButterKnife.bind(this);

        getActivityComponent().inject(this);

        setupMaterialSearch();

        generateUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRandomDaggerUser();
            }
        });

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        createUser();
        createDaggerUsers();
        getUser(1L);
        dataManager.saveAccessToken(generateFakeDaggerUserModifier());

        String token = dataManager.getAccessToken();
        if (token != null) {
            mTvAccessToken.setText(token);
        }

    }

    private void setupMaterialSearch() {

        materialSearchBar.setHint("Search Users with ID e.g. 2");
        materialSearchBar.setSpeechMode(true);

        //enable searchbar callbacks
        materialSearchBar.setOnSearchActionListener(this);
        //Inflate menu and setup OnMenuItemClickListener
        materialSearchBar.inflateMenu(R.menu.menu);
        materialSearchBar.getMenu().setOnMenuItemClickListener(this);

        materialSearchBar.setCardViewElevation(10);

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() >= 1 && charSequence.length() <= 3) {

                    Long userID = Long.valueOf(materialSearchBar.getText());

                    getUser(userID);

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private String generateFakeDaggerUserModifier() {

        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(10) + 10;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();

    }

    // Todo : Create more users in DB
    private void createRandomDaggerUser() {

        int randomPositionIndex = DemoUtils.randInt(0, defaultDaggerUserListSize - 1);
        String fakeModifier = generateFakeDaggerUserModifier();

        String randomName = "RandomUser_" + randomPositionIndex + "_" + fakeModifier;

        try {
            dataManager.createUser(new DaggerUser(randomName, randomName + ", " + randomPositionIndex + randomPositionIndex + 4 + " Random City, " + " Random State, " + " Random Country"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        getUser((long) randomPositionIndex);
        mTvAccessToken.setText(fakeModifier);

    }

    private void createDaggerUsers() {

        for (int i = 0; i < defaultDaggerUserListSize - 1; i++) {

            String randomName = "RandomUser_" + i + "_" + generateFakeDaggerUserModifier();

            daggerUserList.add(new DaggerUser(randomName, randomName + ", " + i + i + 4 + " Random City, " + " Random State, " + " Random Country"));
        }

        try {
            dataManager.createDaggerUsers(daggerUserList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Todo Have a UI component which will fetch user upon query
    private void getUser(Long ID) {
        try {
            DaggerUser daggerUser = dataManager.getUser(ID);
            mTvUserInfo.setText(daggerUser.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
