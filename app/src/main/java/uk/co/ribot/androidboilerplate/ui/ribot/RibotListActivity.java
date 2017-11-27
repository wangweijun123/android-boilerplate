package uk.co.ribot.androidboilerplate.ui.ribot;

import android.os.Bundle;
import android.widget.Toast;

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
//        ribotPresenter.getRankApps();
        ribotPresenter.contributors();

//        ribotPresenter.contributorsStepByStep();
    }

    @Override
    public void showDataLoadSuccessTip() {
        Toast.makeText(getApplicationContext(), "获取数据成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDataLoadErrorTip() {
        Toast.makeText(getApplicationContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
    }
}
