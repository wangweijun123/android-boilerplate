package uk.co.ribot.androidboilerplate.ui.ribot;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Contributor;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

import static android.widget.RelativeLayout.CENTER_IN_PARENT;

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

    @BindView(R.id.root)
    RelativeLayout root;


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
    public void showLoading() {
        View loading = LayoutInflater.from(getApplicationContext()).inflate(R.layout.view_loading, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.addRule(CENTER_IN_PARENT);
        root.addView(loading, params);
    }

    @Override
    public void hideLoading() {
        root.removeView(root.findViewById(R.id.network_loading_pb));
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

    @Override
    public void showErrorUI() {
        View errorContainer = LayoutInflater.from(getApplicationContext()).inflate(R.layout.view_error, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.addRule(CENTER_IN_PARENT);
        root.addView(errorContainer, params);

        errorContainer.findViewById(R.id.view_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("wang", "refresh ...");
                ribotPresenter.refresh();
            }
        });
    }

    @Override
    public void hideErrorUI() {
        root.removeView(root.findViewById(R.id.view_container));
    }
}
