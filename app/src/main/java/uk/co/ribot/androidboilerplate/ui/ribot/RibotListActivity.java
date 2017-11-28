package uk.co.ribot.androidboilerplate.ui.ribot;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Contributor;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

/**
 * Created by wangweijun on 2017/11/26.
 */

public class RibotListActivity extends BaseActivity  implements RibotMvpView{

    @Inject
    RibotPresenter ribotPresenter;

    @Inject
    MyAdapter adapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_ribot_list);
        ButterKnife.bind(this);

        ribotPresenter.attachView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

//        ribotPresenter.getRankApps();

        ribotPresenter.contributors();
//        ribotPresenter.contributorsStepByStep();
    }

    @Override
    public void showDataLoadSuccessTip(List<Contributor> list) {
        adapter.addContributors(list);
        Toast.makeText(getApplicationContext(), "获取数据成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showDataLoadErrorTip() {
        Toast.makeText(getApplicationContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
    }
}
