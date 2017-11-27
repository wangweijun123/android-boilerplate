package uk.co.ribot.androidboilerplate.ui.ribot;

import android.os.Bundle;

import javax.inject.Inject;

import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

/**
 * Created by wangweijun on 2017/11/26.
 */

public class RibotListActivity extends BaseActivity  implements RibotMvpView{

    @Inject
    RibotPresenter ribotPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_ribot_list);

        ribotPresenter.attachView(this);
        ribotPresenter.getRankApps();
    }
}
