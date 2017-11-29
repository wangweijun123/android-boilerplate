package uk.co.ribot.androidboilerplate.ui.ribot;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.Contributor;
import uk.co.ribot.androidboilerplate.data.model.MyResp;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

/**
 * Created by wangweijun on 2017/11/26.
 */

public class RibotPresenter extends BasePresenter<RibotMvpView> {

    private final DataManager mDataManager;// single

    @Inject
    public RibotPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void syncRibots() {
        mDataManager.syncRibots().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Ribot>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("wang", "onSubscribe ");
                    }

                    @Override
                    public void onNext(@NonNull Ribot ribots) {
//                        if (ribots.isEmpty()) {
//                            getMvpView().showRibotsEmpty();
//                        } else {
//                            getMvpView().showRibots(ribots);
//                        }
                        Log.i("wang", "onNext ribots: "+ribots);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("wang", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("wang", "onComplete");
                    }
                });
    }

    public void getRankApps() {
        mDataManager.getRankApps().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<MyResp>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("wang", "onSubscribe ");
                    }

                    @Override
                    public void onNext(@NonNull MyResp ribots) {
                        Log.i("wang", "onNext ribots.status: "+ribots.status);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("wang", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("wang", "onComplete");
                    }
                });
    }


    public void contributors() {
        Log.i("wang", "contributors mDataManager:"+mDataManager);
        mDataManager.contributors().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Contributor>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("wang", "onSubscribe ");
                        getMvpView().showLoading();
                    }

                    @Override
                    public void onNext(@NonNull List<Contributor> list) {
                        Log.i("wang", "onNext size:"+list.size() + ",tid:"+Thread.currentThread().getId());
                        for (Contributor contributor: list) {
                            Log.i("wang", "contributor: "+contributor);
                        }
                        getMvpView().showDataLoadSuccessTip(list);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("wang", "onError");
                        getMvpView().hideLoading();
                        getMvpView().showDataLoadErrorTip();

                        getMvpView().showErrorUI();
                    }

                    @Override
                    public void onComplete() {
                        Log.i("wang", "onComplete");
                        getMvpView().hideLoading();
                    }
                });
    }

    public void refresh() {
        getMvpView().hideErrorUI();
        contributors();
    }

    public void contributorsStepByStep() {
        mDataManager.contributorsStepByStep().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Contributor>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("wang", "onSubscribe ");
                    }

                    @Override
                    public void onNext(@NonNull Contributor contributor) {
                            Log.i("wang", "onNext tid:"+Thread.currentThread().getId()+", contributor: "+contributor);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("wang", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("wang", "onComplete");
                    }
                });
    }
}
